package com.CustomerSupport.Test;

import com.CustomerSupport.framework.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateIssue {
  private Configuration configuration = new Configuration();

  /*
  To get the status of the api.
   */
  @Test(priority = 0)
  public void postCustomerSupportCreateIssue() throws JsonProcessingException {
    Map<String, String> inputParameters = new HashMap<String, String>();
    inputParameters.put("description", "This is a new issue");
    inputParameters.put("fromemail", "knutmt@gmail.com");
    inputParameters.put("title", "New Issue");
    Response response = configuration.postCustomerSupportCreateIssue(inputParameters);
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  /*
  Method to verfiy the response of the api.
   */
  @Test
  public void postCustomerSupportCreateIssueresponse() throws JsonProcessingException {
    Map<String, String> inputParameters = new HashMap<String, String>();
    inputParameters.put("description", "This is a new issue");
    inputParameters.put("fromemail", "knutmt@gmail.com");
    inputParameters.put("title", "New Issue");
    Response response = configuration.postCustomerSupportCreateIssue(inputParameters);
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    Assert.assertEquals("This is a new issue", response.getBody().path("description"));
    Assert.assertEquals("knutmt@gmail.com", response.getBody().path("fromemail"));
    Assert.assertEquals("New Issue", response.getBody().path("title"));

    /*
    More tests can be made but the api is not working.
     */
  }
}
