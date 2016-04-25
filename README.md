# springcouchbase-helloworld
Just a basic helloworld sample to illustrate how to deal with a couchbase database with spring data couchbase module

## Requirements

This demo requires a local couchbase database.
So you must install a local couchbase server. 

### Download and install it

To do so, just follow couhbase [instruction](http://www.couchbase.com/get-started-developing-nosql#Download_Couchbase_Server).

### Run it as a docker container

`docker run -d -p 8091-8093:8091-8093 -p 11210:11210 couchbase/server`

## Run the example

### Get the source code
`git clone https://github.com/pcavezzan/springcouchbase-helloworld.git`

### Run it 
#### if you have already maven installed
`mvn spring-boot:run`
### without any maven
`mvnw spring-boot:run`