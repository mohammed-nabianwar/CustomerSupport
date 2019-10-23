package com.CustomerSupport.Test;

import com.CustomerSupport.framework.Configuration;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetById {
  private Configuration configuration = new Configuration();

  /*
  This is to verify status code.
   */
  @Test(priority = 0)
  public void verifyStatusCode() {
    String id = "5888b0d1f54b5f590000040f";
    Response response = configuration.getCustomerSupportListById(id);
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
  }

  /*
  Check that if searching by Id then unique record comes.
   */

  @Test(priority = 1)
  public void verifySingleRecord() {
    String id = "5888b0d1f54b5f590000040f";
    Response response = configuration.getCustomerSupportListById(id);
    Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    try {
      int len = response.jsonPath().getList("$").size();
    } catch (ClassCastException classCastException) {
      Assert.assertTrue(true);
      return;
    }
    Assert.assertTrue(false);
  }

  /*
  Check the fields of the record. If they are as per the user has inserted or not. This can
  include the check for whole response and can also include specific fields.
   */

  @Test
  public void verifyElementsOfRecord() {
    String id = "5888b0d1f54b5f590000040f";
    Response response = configuration.getCustomerSupportListById(id);
    Assert.assertEquals("knutmt@gmail.com", response.getBody().path("fromemail"));
    Assert.assertEquals("D6FWV", response.getBody().path("ticket"));
    Assert.assertEquals("New Issue", response.getBody().path("title"));
  }

  /*
  A lot more checks can be added on different elements of the response depending on the use case of
  the of each field or the validations on each of them.
   */

}
