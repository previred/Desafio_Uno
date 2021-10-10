# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import desafiocuatro.periodos.swagger.codegen.*;
import desafiocuatro.periodos.swagger.codegen.auth.*;
import desafiocuatro.periodos.swagger.codegen.model.*;
import io.swagger.client.api.PeriodosApi;

import java.io.File;
import java.util.*;

public class PeriodosApiExample {

    public static void main(String[] args) {
        
        PeriodosApi apiInstance = new PeriodosApi();
        try {
            Periodo result = apiInstance.periodos();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PeriodosApi#periodos");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://127.0.0.1:8070/periodos*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*PeriodosApi* | [**periodos**](docs/PeriodosApi.md#periodos) | **GET** /api | Lista de periodos a procesar


## Documentation for Models

 - [Periodo](docs/Periodo.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

mgonzalez@previred.com

