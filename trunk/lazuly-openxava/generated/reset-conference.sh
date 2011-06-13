#!/bin/sh

GENDIR=$(cd `dirname $0`;pwd)

cd $OX_HOME
./stop-tomcat.bat

cd $OX_HOME/workspace
rm -r conference 

cd $OX_HOME/tomcat/webapps 
rm -r conference 

cd $GENDIR
