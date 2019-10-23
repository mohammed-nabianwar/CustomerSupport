package com.CustomerSupport.Test;

import com.CustomerSupport.framework.Configuration;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetByQuery {
  private Configuration configuration = new Configuration();

  /*
  This is to verify status code.
   */
  @Test(priority = 0)
  public void getByQuery() {
    Map<String, String> input = new HashMap<String, String>();
    input.put("fromemail", "knutmt@gmail.com");
    Response response = configuration.getCustomerSupportByQuery(input);
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  /*
  This method is to check that whether in the response on this List issues by query api call only 
  the list of with respect to that field comes. Ex. If queried by fromemail = knutmt@gmail.com then 
  the result should only contain the records with this as the fromemail.
   */
  @Test
  public void getByQueryOnlySpecificField() {
    Map<String, String> input = new HashMap<String, String>();
    input.put("fromemail", "knutmt@gmail.com");
    Response response = configuration.getCustomerSupportByQuery(input);
    for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
      Map<Object, Object> obj = (Map<Object, Object>) response.jsonPath().getList("$").get(i);
      if (obj.get("fromemail").toString().equals("knutmt@gmail.com")) {
        Assert.assertTrue(false);
      }
    }
    Assert.assertTrue(true);
  }

  /*
  This method is to check that whether in the response on this List issues by query api call only
  the list of with respect to that field comes. Ex. If queried by _id = 5888abddf54b5f5900000409 then
  the result should only contain the records with this as the _id.
   */
  @Test
  public void getByQueryOnlySpecificFieldById() {
    Map<String, String> input = new HashMap<String, String>();
    input.put("_id", "5888abddf54b5f5900000409");
    Response response = configuration.getCustomerSupportByQuery(input);
    for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
      Map<Object, Object> obj = (Map<Object, Object>) response.jsonPath().getList("$").get(i);
      if (obj.get("_id").toString().equals("5888abddf54b5f5900000409")) {
        Assert.assertTrue(false);
      }
    }
    Assert.assertTrue(true);
  }

  /*
  Similar checks can be added on other fields upon reading the documentation of the api.
   */
}
