NETWORK=hab-net

docker network create $NETWORK

# Build images
cd database
docker build -t mysql-budget .

cd ../proxy
docker build -t proxy-budget .

cd ../frontend
docker build -t frontend-budget .

# Created before the rest to pass tests
docker run --rm -d --network $NETWORK --name mysql mysql-budget

cd ../backend
docker build --network $NETWORK -t backend-budget .

# Run containers (backend and proxy ports exported, although not necessary)
docker run --rm -d --network $NETWORK -p 8081:8081 --name backend backend-budget
docker run --rm -d --network $NETWORK -p 8082:8082 --name proxy proxy-budget 
docker run --rm -d --network $NETWORK -p 8080:8080 --name frontend frontend-budget
