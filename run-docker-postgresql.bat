@echo off

echo Start PostgreSQL with docker 
echo  container's name is postgres-java
echo --------------------------------------------------------
echo -
docker compose up -d


echo --------------------------------------------------------
echo Connecting bridge to postgres-java
docker network connect bridge postgres-java

pause