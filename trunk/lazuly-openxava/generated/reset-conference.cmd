set currentdir=%cd%

cd %OX_HOME%
call stop-tomcat.bat

cd %OX_HOME%\workspace
del conference /s /q

cd %OX_HOME%\tomcat\webapps 
del conference /s /q

cd %currentdir%
