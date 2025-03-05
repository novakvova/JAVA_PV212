@echo off

tasklist /FI "IMAGENAME eq Docker Desktop.exe" | find /I "Docker Desktop.exe" >nul
if errorlevel 1 (
    echo Docker Desktop starting ...
    start "" "C:\Program Files\Docker\Docker\Docker Desktop.exe"
    :waitForDocker
    tasklist /FI "IMAGENAME eq Docker Desktop.exe" | find /I "Docker Desktop.exe" >nul
    if errorlevel 1 (
        timeout /t 10 /nobreak >nul
        goto waitForDocker
    )
    timeout /t 2 /nobreak >nul
    echo Docker Desktop successfuly started ...
) 

echo Changing directory client...
cd "my-react-app"

echo Building Docker image client...
docker build -t pv212_client .

echo Docker login...
docker login

echo Tagging Docker image client...
docker tag pv212_client:latest novakvova/pv212_client:latest

echo Pushing Docker image client to repository...
docker push novakvova/pv212_client:latest

echo Done ---client---!

echo Changing directory api...
cd ".."
cd "JavaApi"

echo Building Docker image api...
docker build -t java-pv212-api . 

echo Tagging Docker image api...
docker tag java-pv212-api:latest novakvova/java-pv212-api:latest

echo Pushing Docker image api to repository...
docker push novakvova/java-pv212-api:latest

echo Done ---api---!
pause

