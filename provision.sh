FUSE=jboss-fuse-6.1.0.redhat-379

su - vagrant

echo "disabling the firewall"
sudo service iptables save
sudo service iptables stop
sudo chkconfig iptables off

echo "modify hosts file"
sudo echo "192.168.33.10 localhost" >> /etc/hosts

echo "install Java 1.7"
yum -y install java-1.7.0-openjdk-devel

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
fi

echo "Change to the vagrant shared folder"
cd /vagrant

#if [ -d runtime/$FUSE ];
if [ -d /opt/rh/fuse ];
then
	echo "kill any remaining java processes"
	pkill -f 'java -jar'

	echo "Clearing out existing files"
	#rm -Rf runtime/$FUSE
	rm -Rf /opt/rh/fuse
fi

echo "Unzip the Product distributions"
#unzip -o -q dist/*.zip -d runtime/
unzip -o -q dist/*.zip -d /opt/rh
echo "rename Fuse install directory"
mv /opt/rh/$FUSE /opt/rh/fuse

echo "Copy the user.properties into Fuse"
cd /vagrant
#cp integration/users.properties runtime/$FUSE/etc
cp integration/users.properties /opt/rh/fuse/etc

echo "Launch Fuse"
#cd runtime/$FUSE/bin
cd /opt/rh/fuse/bin
sudo ./start&
sleep 1m


echo "execute karaf script"
#./client -u admin -p admin -f /vagrant/integration/init-fuse.script
sudo ./client "fabric:create"
#sudo ./client "fabric:create --clean --resolver manualip --manual-ip 192.168.33.10 --wait-for-provisioning --profile fabric"