package Steps;

import APIs_Payloads.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Takaamol {
    Logger log = Logger.getLogger(Takaamol.class);
    String TicketState;
    @Getter
    @Setter
   String TicketSysId;
    @Setter  @Getter
   public static String UserName;
    @Setter  @Getter
    public static String Password;
    @Setter  @Getter
    public static  String Takamol_BASE_URI ;
    @Setter  @Getter
   public static String AccessToken;
    Response response;

    @Given("Get Access Token")
    public void ExtractToken(List<Map<String, String>> datatable) {
        try {
            for (Map<String, String> UserCredentials : datatable) {
                UserName = UserCredentials.get("UserName");
                Password = UserCredentials.get("Password");
                Takamol_BASE_URI=UserCredentials.get("TakamolBaseURI");
                response = given()
                        .auth().preemptive().basic(UserName, Password)
                        .and().relaxedHTTPSValidation()
                        .log().all()
                        .post(Takamol_BASE_URI + "v1/authorize/access-token")
                        .then().log().all().extract().response();
                Assert.assertTrue(response.getStatusCode() == 200);
                log.info("Media uploaded correctly with Media-ID=" + response.jsonPath().get("access_token").toString());
                setAccessToken(response.jsonPath().get("access_token").toString());
                setTakamol_BASE_URI(Takamol_BASE_URI);

            }
        }
        //message_create.message_data.text
        catch (Exception e) {

            throw e;
        }
    }
    @And("Get ticket information")
    public void ExtractTicketSystemId(List<Map<String, String>> datatable) {
        try {
            for (Map<String, String> Ticketlist : datatable) {
                response = given().relaxedHTTPSValidation().
                        headers("Authorization", "Bearer " + getAccessToken())
                        .and().relaxedHTTPSValidation()
                        .log().all()
                        .queryParam("number",  Ticketlist.get("TicketNo"))
                        .when().log().all()
                        .get(Takamol_BASE_URI + "servicenow-incident-integration/enforcement")
                        .then().log().all().extract().response();
                Assert.assertTrue(response.getStatusCode() == 200);
                log.info("Syst ID" + response.jsonPath().get("result.sys_id").toString());
                setTicketSysId(response.jsonPath().get("result.sys_id").toString());
            }
        }
        //message_create.message_data.text
        catch (Exception e) {

            throw e;
        }
    }
    @Then("Update ticket")
    public void UpdateTicketState(List<Map<String, String>> datatable) {
        try {
            for (Map<String, String> RequestInfo : datatable) {
                TicketState = RequestInfo.get("TicketStatusToBe");
             String sysid=getTicketSysId();
            response = given().relaxedHTTPSValidation()
                    .header("Content-Type", "application/json")
                    .headers("Authorization", "Bearer " + getAccessToken()) //+ //GetAccessToken())
                    .log().all()
                    .body(Payloads.UpdateDaemTicketPayload())
                    .patch(Takamol_BASE_URI + "servicenow-incident-integration/incident/{sysid}", getTicketSysId())
                    .then().log().all().extract().response();
            Assert.assertTrue(response.getStatusCode() == 200);
            log.info("Ticket Updatede" + response.jsonPath().get("result.task_effective_number"));
           // Assert.assertEquals(response.jsonPath().get("result.task_effective_number").toString(),TicketNo);

        }}
        //message_create.message_data.text
        catch (Exception e) {

            throw e;
        }
    }


}



