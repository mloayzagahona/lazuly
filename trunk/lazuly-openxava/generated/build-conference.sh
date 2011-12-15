#!/bin/sh

GENDIR=$(cd `dirname $0`;pwd)

cd $OX_HOME/workspace/OpenXavaTemplate
ant -f CreateNewProject.xml -Dproject=conference

cp $MP_HOME/target/mp-openxava/*.jar $OX_HOME/workspace/conference/web/WEB-INF/lib
cd $GENDIR
cp -R * ${OX_HOME}/workspace/conference 

cp other/tomcat/snippet/context.xml $OX_HOME/tomcat/conf

cp $MP_HOME/application/lib/extra/*.jar $OX_HOME/tomcat/lib

cd $OX_HOME
./start-tomcat.sh

cd $OX_HOME/workspace/conference
ant -f mp4openxava-build.xml
ant

cd $OX_HOME/workspace/conference
ant generatePortlets

firefox http://localhost:8080/conference/xava/homeMenu.jsp

cd $GENDIR
