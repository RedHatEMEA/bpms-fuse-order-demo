#!/bin/bash

#
# This script is to start Fuse and the Web application after stoping the vagrant VM
#
# BPMS Server is set to run as a service so does not require starting.
#
#


echo "Launch Fuse"
cd /opt/rh/fuse/bin
sudo ./start&
sleep 1m

echo "Deploy web front-end"
cd /vagrant/webapp
mvn clean install
mvn cargo:run&

