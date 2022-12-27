# Playground

This is a Spring Boot application for testing and storing code snippets for future reference.



## Upload controller
Code sample to upload a file. [UploadController](src/main/java/org/juanjo/playground/controller/DocumentController.java)

To test upload controller:
```sh
curl --location --request POST "localhost:8080/api/document" \
--form "param1=\"1234\"" \
--form "param2=\"5678\"" \
--form "file=@\"/tmp/document.pdf\""
```