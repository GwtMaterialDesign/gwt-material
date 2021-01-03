@echo off
SET DEVELOPMENT_HOME= "${DEV_DIRECTORY}"

cd %DEVELOPMENT_HOME%\gwt-material-jquery\
call mvn clean install -Dmaven.test.skip=true

cd %DEVELOPMENT_HOME%\gwt-material\
call mvn clean install -Dmaven.test.skip=true

cd %DEVELOPMENT_HOME%\gwt-material-themes\
call mvn clean install -Dmaven.test.skip=true

cd %DEVELOPMENT_HOME%\gwt-material-table\
call mvn clean install -Dmaven.test.skip=true

cd %DEVELOPMENT_HOME%\gwt-material-addins\
call mvn clean install -Dmaven.test.skip=true

cd %DEVELOPMENT_HOME%\gwt-material-table\
call mvn clean install -Dmaven.test.skip=true

cd %DEVELOPMENT_HOME%\gwt-material-amcharts4\
call mvn clean install -Dmaven.test.skip=true