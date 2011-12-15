set currentdir=%cd%

cd %OX_HOME%\workspace\OpenXavaTemplate
call ant -f CreateNewProject.xml -Dproject=conference

copy %MP_HOME%\target\mp-openxava\*.jar %OX_HOME%\workspace\conference\web\WEB-INF\lib
cd %currentdir%
xcopy * %OX_HOME%\workspace\conference /y /s

copy other\tomcat\snippet\context.xml %OX_HOME%\tomcat\conf

xcopy %MP_HOME%\application\lib\extra\*.jar %OX_HOME%\tomcat\lib\*.* /y /s

cd %OX_HOME%
call start-tomcat.bat

cd %OX_HOME%\workspace\conference
call ant -f mp4openxava-build.xml
call ant

cd %OX_HOME%\workspace\conference
call ant generatePortlets

call explorer http://localhost:8080/conference/xava/homeMenu.jsp

cd %currentdir%
