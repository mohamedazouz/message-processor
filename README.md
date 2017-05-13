Build and Deploy
----------------------

Make sure you have all necessary tools installed
    
    Java 8
    
Build the application:

    mvn clean install
     
Run locally
----------------------

        java -jar target/message-processor.jar  inputFileName
        example: java -jar target/message-processor.jar  ~/message-processor/src/main/resources/salesRecords