FUSE=jboss-fuse-6.1.0.redhat-379

#function - waitFor (textToFind, FileToLookIn, timeOutInSeconds, messageToDisplay) - a function that will wait for a maximum of TIMEOUT looking for specific text in a file
function waitFor () {

	TEXT_TO_FIND=$1
	FILE_TO_LOOK=$2
	TIMEOUT=$3
	MESSAGE_TO_DISPLAY=$4
	
	#outputLog "Waiting to find \"${TEXT_TO_FIND}\" in $FILE_TO_LOOK with a max timeout of $TIMEOUT"
	#outputLog "$MESSAGE_TO_DISPLAY" "2" "n" "y"
	
	#Doubling because the sleep time is halved to ensure reading the log fast enough
	TIMEOUT=$(( TIMEOUT * 2 ))	
	WAIT_FOR_RESULT=""

	COUNT=0
	FOUND=
	while [[ "$FOUND" == "" ]]; do		

		if [[ -f "$FILE_TO_LOOK" ]]; then
			FOUND=`tail -15 $FILE_TO_LOOK | grep "${TEXT_TO_FIND}"`
		else
			FOUND=
		fi
		
		if [[ "$FOUND" == "" ]]; then
			sleep 0.5
		else
	#		outputLog ".   Done waiting after $(( COUNT / 2 ))s"  "2" "n" "n"
			WAIT_FOR_RESULT="completed"
			break
		fi
		 
		if [ $COUNT -lt $TIMEOUT ]; then
			COUNT=$(( $COUNT + 1 ))
		else 
			FOUND=".   Timeout of $(( TIMEOUT / 2 )) seconds reached"
			WAIT_FOR_RESULT="timedout"
	#		outputLog "$FOUND" "2" "n" "n"
			break
		fi
		
	done
	
	#outputLog "." "2" "y" "n"
}


su - vagrant

echo "disabling the firewall"
sudo service iptables save
sudo service iptables stop
sudo chkconfig iptables off

echo "modify hosts file"
sudo echo "192.168.33.10 localhost" >> /etc/hosts

echo "install Java 1.7"
yum -y install java-1.7.0-openjdk-devel

export JAVA_HOME=/usr/lib/jvm/jre-1.7.0-openjdk.x86_64
export PATH=$JAVA_HOME/bin:$PATH

echo "install unzip"
yum -y install unzip

if [ -d /usr/local/maven ];
then
	echo "maven already installed"
else
	echo "install maven"
	wget http://mirrors.gigenet.com/apache/maven/maven-3/3.2.1/binaries/apache-maven-3.2.1-bin.tar.gz
	su -c "tar -zxvf apache-maven-3.2.1-bin.tar.gz -C /usr/local"
	cd /usr/local
	sudo ln -s apache-maven-3.2.1 maven 

	echo "add maven to the path"
	export M2_HOME=/usr/local/maven
	export PATH=$M2_HOME/bin:$PATH
	echo "M2_HOME=/usr/local/maven" >> ~/.bashrc
	echo "PATH=$M2_HOME/bin:$PATH" >> ~/.bashrc
fi

echo "Change to the vagrant shared folder"
cd /vagrant

if [ -d /opt/rh/fuse ];
then
	echo "kill any remaining java processes"
	pkill -f 'java -jar'
	sleep 30s
	echo "Clearing out existing files"
	rm -Rf /opt/rh/fuse
	rm -Rf /opt/rh/jboss-eap-6.1
fi

echo "Unzip the Product distributions"
unzip -o -q dist/jboss-fuse-full-6.1.0.redhat-379.zip -d /opt/rh
unzip -o -q dist/jboss-eap-6.1.1.zip -d /opt/rh
unzip -o -q dist/jboss-bpms-6.0.2.GA-redhat-5-deployable-eap6.x.zip -d /opt/rh

echo "rename Fuse install directory"
mv /opt/rh/$FUSE /opt/rh/fuse

echo "Copy the user.properties into Fuse"
cd /vagrant
cp integration/users.properties /opt/rh/fuse/etc

echo "Launch Fuse"
cd /opt/rh/fuse/bin
sudo ./start&
sleep 1m


echo "create fabric"
#./client -u admin -p admin -f /vagrant/integration/init-fuse.script
sudo ./client "fabric:create"

echo "Launch BPMS"

ret=false
getent passwd jboss >/dev/null 2>&1 && ret=true

if $ret; then
    echo "jboss user already exists"
else
    sudo adduser jboss
fi

#sudo chown -fR jboss.jboss /opt/rh/jboss-eap-6.1
sudo chown -fR vagrant /opt/rh/jboss-eap-6.1

# cd jboss-eap-6.1/bin
# su - jboss
# sudo nohup ./standalone.sh -Djboss.bind.address=192.168.33.10 -Djboss.bind.address.management=192.168.33.10 &

echo "Configure EAP as a service and start"
sudo mkdir -p /etc/jboss-as
sudo mkdir -p /opt/rh/jboss-eap-6.1/git
sudo cp -r /vagrant/bpm/.niogit /opt/rh/jboss-eap-6.1/git
sudo mkdir -p /opt/rh/jboss-eap-6.1/index
sudo mkdir -p /opt/rh/jboss-eap-6.1/bpms-repo


cd /opt/rh
sudo chmod -R 777 jboss-eap-6.1
sudo cp /vagrant/bpm/jboss-as.conf /etc/jboss-as/
sudo cp /vagrant/bpm/standalone.conf /opt/rh/jboss-eap-6.1/bin
sudo cp /vagrant/bpm/standalone.xml /opt/rh/jboss-eap-6.1/standalone/configuration
sudo cp /opt/rh/jboss-eap-6.1/bin/init.d/jboss-as-standalone.sh /etc/init.d
sudo chkconfig --add jboss-as-standalone.sh
sudo chkconfig jboss-as-standalone.sh on
echo "Configure BPM Suite user"
# This is the preferred way but there seems to be an issue running this that -s should solve. https://access.redhat.com/solutions/462043
#/opt/rh/jboss-eap-6.1/bin/add-user.sh -s -u bpmadmin -p bpmsuite1! -a --realm ApplicationRealm --role admin,analyst,manager
sudo cp /vagrant/bpm/application-users.properties /opt/rh/jboss-eap-6.1/standalone/configuration
sudo cp /vagrant/bpm/application-roles.properties /opt/rh/jboss-eap-6.1/standalone/configuration

# Copy a settings.xml into maven so bpms can authenticate with its own internal repo
sudo cp /vagrant/bpm/settings.xml /usr/local/maven/conf/
sudo cp /vagrant/bpm/url_filter.yaml /opt/rh/jboss-eap-6.1/standalone/deployments/business-central.war/WEB-INF/classes/

sudo service jboss-as-standalone.sh start 


echo "Waiting for BPMS to deploy"
waitFor "started in" "/opt/rh/jboss-eap-6.1/standalone/log/server.log" "300" "Awaiting EAP server start up"
	if [[ "$WAIT_FOR_RESULT" == "completed" ]]; then
		echo "BPMS now deployed. Browse to http://192.168.33.10:8080/business-central"
		echo "BPMS User: bpmadmin Password: bpmsuite1!"
	else
		echo "Server start up did not complete after a long wait"
		
	fi

echo "Deploy the domain model"
cd /vagrant/domain/bfod-model
mvn clean install

echo "Deploy web front-end"
cd /vagrant/webapp
mvn clean install
mvn cargo:run&

echo "The web front-end is available at http://192.168.33.10:8282"

echo "Deploy Camel routes"
cd /vagrant/integration/cxfservices
mvn fabric8:deploy -DskipTests -Dfabric8.jolokiaUrl=http://192.168.33.10:8181/jolokia
cd /vagrant/integration/bpmintegration
mvn fabric8:deploy -DskipTests -Dfabric8.jolokiaUrl=http://192.168.33.10:8181/jolokia


echo "If everything went smoothly then the following should be running"
echo "BPMS : http://192.168.33.10:8080/business-central"
echo "BPMS User: bpmadmin  | Password: bpmsuite1!"

echo "Fuse Console : http://192.168.33.10:8181"
echo "Fuse User : admmin | Password: admin"

echo "Webapp : http://192.168.33.10:8282/"
