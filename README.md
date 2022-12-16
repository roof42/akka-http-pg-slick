# Using Akka Http with Slick (Postgres) 
Vary simple project that use for explain how to put Akka Http and Slick together.
# Using docker compose
0. Clone source from repo
> git clone git@gitlab.com:ruvlab/basic-slick.git
1. Build docker image
> sbt docker:publishLocal
2. Start compose
> docker compose up -d
3. Create database and initial data
> sbt initialDatabase
4. Test get all records
> curl http://localhost:8080/message
5. Test create new record
> curl -H "Content-Type: application/json" -X POST -d '{"sender":"ruv", "content":"new content", "id":0}' http://localhost:8080/message

# Basic Akka HTTP with Slick and Postgres
0. Clone source from repo
> git clone git@gitlab.com:ruvlab/basic-slick.git
1. Start Postgres
> docker run --name some-postgres -e POSTGRES_PASSWORD=my_secret -p 5432:5432 -d postgres 
2. Create database and initial data
> sbt initialDatabase
4. Run Api (Locally)
> sbt runLocal
5. Test get all records
> curl http://localhost:8080/message
6. Test create new record
> curl -H "Content-Type: application/json" -X POST -d '{"sender":"ruv", "content":"new content", "id":0}' http://localhost:8080/message

# Build and run using Docker
0. Build docker image
> sbt docker:publishLocal
1. Run image locally
> docker run -p 8080:8080 part2:0.1.0-SNAPSHOT
