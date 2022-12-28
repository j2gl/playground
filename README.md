# Playground

This is a Spring Boot application for testing and storing code snippets for future reference.

## Playground Controller

Get properties from [application.properties](src/main/resources/application.properties) file as a `java.util.List` or 
`java.util.set`. See [PlaygroundRestController](src/main/java/org/juanjo/playground/controller/PlaygroundRestController.java)

## Greeting Controller

### /api/greeting
Code sample to test a Rest Endpoint with greeting. [PlaygroundRestController](src/main/java/org/juanjo/playground/controller/GreetingController.java)

To use greeting endpoint
```shell
curl --location --request GET "localhost:8080/api/greeting?name=Mr.%20Snow"
```

To call greeting endpoint through webclient test.
```shell
curl --location --request GET "localhost:8080/api/test-greeting"
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