cd database
docker build -t mysql-budget .
#cd ../backend
#docker build -t backend-budget .
cd ../proxy
docker build -t proxy-budget .
cd ../frontend
docker build -t frontend-budget .
