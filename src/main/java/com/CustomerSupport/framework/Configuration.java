package com.CustomerSupport.framework;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;

/*
Status code can be checked at the time of api call itself by doing .then().statusCode(200) but for
now I have added the expected status test in the test cases itself.
 */

public class Configuration {
  private ObjectMapper objectMapper = new ObjectMapper();

  /*
  Method to call List All Issues api.
   */
  public Response getCustomerSupportListAll() {
    return given()
        .header("x-apikey", "5da6fb5d3cbe87164d4bb35d")
        .when()
        .get("https://flobizhiring-57e6.restdb.io/rest/issues")
        .then()
        .extract()
        .response();
  }

  /*
  Method to call List Issue by Id api.
   */
  public Response getCustomerSupportListById(String id) {
    return given()
        .header("x-apikey", "5da6fb5d3cbe87164d4bb35d")
        .when()
        .get("https://flobizhiring-57e6.restdb.io/rest/issues/" + id)
        .then()
        .extract()
        .response();
  }

  /*
  Method to call List Issue by Query api.
   */
  public Response getCustomerSupportByQuery(Map<String, String> input) {
    return given()
        .header("x-apikey", "5da6fb5d3cbe87164d4bb35d")
        .queryParams(input)
        .when()
        .get("https://flobizhiring-57e6.restdb.io/rest/issues")
        .then()
        .extract()
        .response();
  }

  /*
  Method to call Create Issue api.
   */
  public Response postCustomerSupportCreateIssue(Object inputParameters)
      throws JsonProcessingException {
    return given()
        .header("x-apikey", "5da6fb5d3cbe87164d4bb35d")
        .contentType(ContentType.JSON.toString())
        .body(objectMapper.writeValueAsString(inputParameters))
        .when()
        .post("https://flobizhiring-57e6.restdb.io/rest/issues")
        .then()
        .extract()
        .response();
  }

  /*
  Method to call Modify Issue api.
   */
  public Response putCustomerSupportModifyIssue(Object inputParameters, String id)
      throws JsonProcessingException {
    return given()
        .header("x-apikey", "5da6fb5d3cbe87164d4bb35d")
        .contentType(ContentType.JSON.toString())
        .body(objectMapper.writeValueAsString(inputParameters))
        .when()
        .put("https://flobizhiring-57e6.restdb.io/rest/issues/" + id)
        .then()
        .extract()
        .response();
  }
}
