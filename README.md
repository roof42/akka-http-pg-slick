# Basic Akka HTTP with Slick and Postgres
Vary simple project that use for explain how to put Akka Http and Slick together.
0. Clone source from repo
> get clone git@gitlab.com:ruvlab/basic-slick.git
1. Start Postgres
> docker run --name some-postgres -e POSTGRES_PASSWORD=my_secret -p 5432:5432 -d postgres 
2. Create database and initial data
> sbt initData
4. Run Api
> sbt run
5. Test get all records
> curl http://localhost:8080/message
6. Test create new record
> curl -H "Content-Type: application/json" -X POST -d '{"sender":"ruv", "content":"new content", "id":0}' http://localhost:8080/message
