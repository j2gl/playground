# Playground

This is a Spring Boot application for testing and storing code snippets for future reference.

## Playground Controller

Get properties from [application.properties](src/main/resources/application.properties) file as a `java.util.List` or 
`java.util.set`. See [PlaygroundRestController](src/main/java/org/juanjo/playground/controller/PlaygroundRestController.java)

## Greeting Controller

### /api/greeting
Code sample to test a Rest Endpoint with greeting. [GreetingController](src/main/java/org/juanjo/playground/controller/GreetingController.java)

To use greeting endpoint
```shell
curl --location --request GET "localhost:8080/api/greeting?name=Mr.%20Snow"
```

To call greeting endpoint through webclient test.
```shell
curl --location --request GET "localhost:8080/api/test-greeting"
```

### Room Controller

Shows hot to use maps in `application.properties` file. [RoomController](src/main/java/org/juanjo/playground/controller/RoomController.java).
Check the properties file [application.properties](src/main/resources/application.properties).
```shell
curl --location --request GET "localhost:8080/api/rooms" | jq
curl --location --request GET "localhost:8080/api/rooms/101" | jq
curl --location --request GET "localhost:8080/api/rooms/202" | jq
```

### Wiremock Example
See also the Integration Test with wiremock example: [GreetingWebClientIT](src/test/java/org/juanjo/playground/client/GreetingWebClientIT.java). 


## Upload controller
Code sample to upload a file. [UploadController](src/main/java/org/juanjo/playground/controller/DocumentController.java)

To test upload controller:
```shell
curl --location --request POST "localhost:8080/api/document" \
--form "param1=\"1234\"" \
--form "param2=\"5678\"" \
--form "file=@\"/tmp/document.pdf\""
```

## Room Controller
The objective of this controller is to test the use of filling maps with properties from a file.

See
* [application.properties](src/main/resources/application.properties).
* [PropertyService](src/main/java/org/juanjo/playground/props/PropertyService.java)
* [RoomController](src/main/java/org/juanjo/playground/controller/RoomController.java) 
                    

```shell
curl --location --request GET "localhost:8080/api/rooms" | jq
curl --location --request GET "localhost:8080/api/rooms/101" | jq
curl --location --request GET "localhost:8080/api/rooms/202" | jq
```

## Actuator
Go to [actuator](http://localhost:8080/actuator) to check it.
Also if you hit `curl --location --request GET "localhost:8080/api/greeting?name=Snow"` then you can go to [here](http://localhost:8080/actuator/metrics/http.server.requests).

### Tracing

1) Run Zipkin in a docker container
```shell
docker run -d -p 9411:9411 openzipkin/zipkin
```

2) Do some requests like:
```shell
curl --location --request GET "localhost:8080/api/test-greeting"
```

3) Go to Zipkin UI: http://localhost:9411 
4) Search `serviceName: playground` and press `RUN QUERY`  