# PeriodosApi

All URIs are relative to *http://127.0.0.1:8070/periodos*

Method | HTTP request | Description
------------- | ------------- | -------------
[**periodos**](PeriodosApi.md#periodos) | **GET** /api | Lista de periodos a procesar


<a name="periodos"></a>
# **periodos**
> Periodo periodos()

Lista de periodos a procesar

### Example
```java
// Import classes:
//import desafiocuatro.periodos.swagger.codegen.ApiException;
//import io.swagger.client.api.PeriodosApi;


PeriodosApi apiInstance = new PeriodosApi();
try {
    Periodo result = apiInstance.periodos();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PeriodosApi#periodos");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Periodo**](Periodo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

