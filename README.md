# Solution:

* Application is expecting receving a file full of sale records
* There's a three types for each reacord in the file
    * Single product sale: follows pattern: `^(\\w+)\\sat\\s(\\d+)\\w+$`
    * Multiple sale with the same product: follows pattern: `^(\d+)\ssales\sof\s(\w+)s\sat\s(\d+)\w+\seach$`
    * Adjustment on previous sales base don product and it follows opattern: `^(Add|Addition|Sub|Subtract|Mul|Multiply)\s(\d+)\w+\s(\w+)s$`
    * Otherwise we consider this record as wrong message.
* Application deals all products names
* Application consists of multiple modules:
    * Processor: The main applicaiton which handles application start/end and injecting objects
    * Reader: Reads recods from file and sends to parse
    * Parser: Parses each record individually and sends it to persist
    * Sales Repository: Presist the state for all sales for each product, Extract reports adjustments, product logs
    
    
# How to use the application 

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