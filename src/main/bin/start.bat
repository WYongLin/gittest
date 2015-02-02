@echo off
cls

set PROJECT_DIR=%cd%

java -Djava.library.path=%PROJECT_DIR% -jar lib\gittest-0.0.1-RELEASE.jar

pause