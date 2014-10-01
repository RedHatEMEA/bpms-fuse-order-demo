bpms-fuse-order-demo
====================

A demo illustrating interaction between BPMS and Fuse for an order fulfilment work-flow process.


1. Download and install Vagrant - https://www.vagrantup.com/downloads - note on some operating systems you will additionally need to install VirtualBox [https://www.virtualbox.org/](https://www.virtualbox.org/)

1. Download this repository and unzip it

1. Download jboss-fuse-full-6.1.0.redhat-379.zip and place it in the dist directory

1. Download jboss-bpms-6.0.2.GA-redhat-5-deployable-eap6.x.zip and place it in the dist directory

1. Download jboss-eap-6.1.1.zip and place it in the dist directory

1. Execute the command vagrant up

1. Browse to [http://192.168.33.10:8181/hawtio](http://192.168.33.10:8181/hawtio) to access the Fuse Management Console

1. Browse to [http://192.168.33.10:8080/business-central](http://192.168.33.10:8080/business-central) to access BPMS

1. Browse to [http://192.168.33.10:8282](http://192.168.33.10:8282) to access the web application

1. Build the model project (integration/bpmintegration) and add it to BPMS - Authoring --> Artefact Repository --> Upload

1. In Project Authoring --> Tools --> Project Editor hit the Build & deploy Button.

1. In the Deploy -> Deployments view remove the current deployment and create a new deployment unit. We want to select the Request strategy.

Group Id : com.redhat.emea.example.bfod <br/>
Artifact ID : bfod-bpm<br/>
Version : 1.0.0

===================

If you stop and start the vagrant VM Fuse and the Web Interface are not started. There is a script to start these (in /vagrant after ssh in)
