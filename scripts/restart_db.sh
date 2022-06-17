docker kill mongoContainer
docker rm mongoContainer
docker run -p 27017:27017 --name mongoContainer -d mongo:latest