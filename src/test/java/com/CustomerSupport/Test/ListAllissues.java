package com.CustomerSupport.Test;

import com.CustomerSupport.framework.Configuration;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ListAllissues {
  private Configuration configuration = new Configuration();

  /*
  This is to verify the status code.
   */

  @Test
  public void getCustomerSupport() {
    Response response = configuration.getCustomerSupportListAll();
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  /*
  Many cases can be added based on the count and different field.
   */
}
