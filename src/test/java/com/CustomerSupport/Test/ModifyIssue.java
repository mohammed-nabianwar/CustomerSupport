package com.CustomerSupport.Test;

import com.CustomerSupport.framework.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ModifyIssue {
  private Configuration configuration = new Configuration();

  /*
  This is to test if the updated issue is coming in the response or not.
   */
  @Test(description = "Check wheather the issue is updated in the response")
  public void modifiedIssueInResponse() throws JsonProcessingException {
    Map<String, String> inputParameters = new HashMap<String, String>();
    inputParameters.put("title", "New Issue 71");
    inputParameters.put("description", "Everything is jammed.");
    String id = "5888abddf54b5f5900000409";
    Response response = configuration.putCustomerSupportModifyIssue(inputParameters, id);
    Assert.assertEquals("New Issue 71", response.getBody().path("title"));
    Assert.assertEquals("Everything is jammed.", response.getBody().path("description"));
  }

  /*
  This is to test if the data is properly inserted into database and is reflected if the a get call is got after the modification.
   */

  @Test(description = "Check wheather the entry updated is inserted into the database when a get call is done")
  public void modifiedIssueInDatabase() throws JsonProcessingException {
    String id = "5888abddf54b5f5900000409";
    Map<String, String> input = new HashMap<String, String>();
    input.put("title", "New Issue71");
    input.put("description", "Everything is working.");
    input.put("status", "Working");
    Response response1 = configuration.putCustomerSupportModifyIssue(input, id);
    Assert.assertEquals(HttpStatus.SC_OK, response1.getStatusCode());
    Response response2 = configuration.getCustomerSupportListById(id);
    Assert.assertEquals(HttpStatus.SC_OK, response2.getStatusCode());
    Assert.assertEquals("Everything is working.", response2.getBody().path("description"));
    Assert.assertEquals("Working", response2.getBody().path("status"));
  }

  /*
  This is to test is the modify call works without the title or not.
   */

  @Test(description = "Check wheather the entry updated is inserted into the database when a get call is done")
  public void modifiedIssueWithNoTitle() throws JsonProcessingException {
    String id = "5888abddf54b5f5900000409";
    Map<String, String> input = new HashMap<String, String>();
    input.put("description", "Everything is working.");
    input.put("status", "Working");
    Response response = configuration.putCustomerSupportModifyIssue(input, id);
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    Assert.assertEquals("Everything is working.", response.getBody().path("description"));
    Assert.assertEquals("Working", response.getBody().path("status"));
  }

  /*
  This is to test that if the call updates the record with only tilte.
   */

  @Test
  public void modifiedIssueWithOnlyTitle() throws JsonProcessingException {
    String id = "5888abddf54b5f5900000409";
    Map<String, String> input = new HashMap<String, String>();
    input.put("title", "New Issue71");
    Response response = configuration.putCustomerSupportModifyIssue(input, id);
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    Assert.assertEquals("New Issue71", response.getBody().path("title"));
  }

  /*
  This is to verify if the id gets updated or not Ideally the Id should not get updated with the put call.
   */

  @Test
  public void modifiedIssueWithId() throws JsonProcessingException {
    String id = "5888abddf54b5f5900000409";
    Map<String, String> input = new HashMap<String, String>();
    input.put("_id", "5888abddf54b5f5900000410");
    input.put("title", "New Issue71");
    Response response = configuration.putCustomerSupportModifyIssue(input, id);
    Assert.assertNotEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

}
