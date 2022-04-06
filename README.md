
# DNAAnalyzer PoC

###### Software stack
- Framework Micronaut 1.2.3
- Spring Reactor 3.2.10
- Junit jupiter 5.5.0
- Mockito 5.5.0

###### Development Enviroment
- Java 1.8.0_221 
- Gradle 5.6.2
- IntelliJ IDE 2019.2.4

###### Infrastructure stack
- AWS API Gateway (Rest)
- AWS Lambda (Java 8 on Amazon Linux 1)
- AWS Open Search (ElasticSearch 6.8)
- AWS SNS
- AWS SQS

## 1. Setting development environment

- ### Step 1: Set enviroment variables

| Variable | Value |
| ------ | ------ |
| APPNAME |dna-analyzer |
| APPPORT | 8080 |
| ENDPOINTELASTIC|private secret was mailed|
USERELASTIC|private secret was mailed|
|PASSWORDELASTIC|private secret was mailed|
|TOPIC|private secret was mailed|
|ACCESSKEY|private secret was mailed|
|SECRETACCESSKEY|private secret was mailed|

- ### Step 2: Create Enviroment configurations in IntelliJ IDE or other

> Every task require all enviroment variables configured, MiconautTest uploads the context.

| Task | Command |
| ------ | ------ |
| Run | gradle clean run |
| Test | gradle clean test |
| Build| gradle clean build |

## 2. API
> I will add swagger or openAPI documentation in other version
- ###  IsMutant API
```sh
Method: POST
URL: https://483b4yzwp1.execute-api.us-east-1.amazonaws.com/1/dna-analyzer/dna/mutant
Payload:
 {
   "dna":["ATGGGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
 }
Header: Content-Type:application/json
Response Case isMutant:
    httpCode 200 OK
    body: true
Response Case (isNoMutant):
    httpCode 403 Forbidden
    body: {"type": "BusinessValidation","message": "DNA_NO_MUTANT"}
Response Case inValidDna long or character:
    httpCode 400 Bad Request
    body: {"type": "BusinessValidation","message": "INVALID_DNA"}
```

- ###  getsStats API
```sh
Method: GET
URL: https://483b4yzwp1.execute-api.us-east-1.amazonaws.com/1/dna-analyzer/dna/stats
Header: Content-Type:application/json
Response:
    httpCode 200 OK
    body: 
    {
        "count_mutant_dna": 33,
        "count_human_dna": 1249,
        "ratio": 0.026421137
    }
Response case (count_human_dna==0):
    httpCode 200 OK
    body: 
    {
        "count_mutant_dna": 33,
        "count_human_dna": 0,
        "ratio": -1
    }
```

## 3. Architecture - physical view 

![physical view](https://dnaanalyzerresources.s3.amazonaws.com/infrastructure.png)
[DNAPush Lambda Repository : https://github.com/faxdam1/DNAPush](https://github.com/faxdam1/DNAPush)

## 4. Coverage Test 
![physical view](https://dnaanalyzerresources.s3.amazonaws.com/coverage.png)

## 5.  Automatics Test
![physical view](https://dnaanalyzerresources.s3.amazonaws.com/test.png)

#### Contact information
- By: David Alfonso Arango Montoya
- Email: faxdam@hotmail.com
- Phone: +57 3008018060
- Software Architect
- [https://linkedin.com/davidarango369](https://www.linkedin.com/in/davidarango369/)

