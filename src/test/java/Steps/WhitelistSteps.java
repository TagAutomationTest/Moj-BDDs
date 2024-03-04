package Steps;

import APIs_Payloads.Payloads;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class WhitelistSteps {
    Integer RequiredAction;
    Long Identity;
    String Date;
    String RequiredFun;
    Payloads Payload=new Payloads();
    Response response;
    String MOCBaseUrl = "https://api-test.moj.gov.local";
    Logger log = Logger.getLogger(WhitelistSteps.class);
    String BASE_URI;

    @And("Set Environment")
    public void SetEnvironmrnttt(List<Map<String, String>> Tagg) {
        for (Map<String, String> taagg : Tagg) {
            String Env = taagg.get("EnvironmrntType");
            //WhiteListBaseUrl= SetEnv(Env);
            switch (Env) {
                case "Testing":
                    BASE_URI = "https://execution-tst.moj.gov.local/Najiz.Api";
                    break;

                case "Staging":
                    BASE_URI = "https://10.162.2.161:9001";
                    break;
                //var agentName = YAML.read("Test.yaml").get("agent.name").asString();

            }

        }}

    @And("verify that identity had details from Moc")
    public void Setrrrst(List<Map<String, String>> tt) throws Exception {
       String token = Takaamol.getAccessToken();
        for (Map<String, String> taagg : tt) {
            Long Identity = Long.parseLong(taagg.get("CRNumber"));
         response = given()
                .relaxedHTTPSValidation()
                .headers("Authorization", "Bearer " +Takaamol.getAccessToken())
                .queryParam("CRNumber", Identity)
                .log().all()
                .get(MOCBaseUrl +   Payload.ReadJson().get("MocUrl"))
                .then().log().all().extract().response();
        Assert.assertTrue(response.getStatusCode() == 200);
    }

}
        @When("Execute WhiteList API")
        public Response PostIdentity(List<Map<String, String>> tt) throws Exception {
        try {
            JSONObject jsonObj = new JSONObject();
            for (Map<String, String> taagg : tt) {
                Identity = Long.parseLong(taagg.get("IdentityId"));
                RequiredFun = taagg.get("RequiredAction");
                if (RequiredFun.equalsIgnoreCase("Add")) {
                     RequiredAction = 1;
                } else if (RequiredFun.equalsIgnoreCase("Delete")) {
                    RequiredAction = 2;
                } else {
                    throw new Exception("Entered" + RequiredFun + "Is not valid");
                }
                Date = taagg.get("ExpirationDate");
                jsonObj = Payload.PrepareWhitleListPayload();
                jsonObj.put("IdentityId", Identity);
                jsonObj.put("RequiredAction", RequiredAction);
                jsonObj.put("ExpirationDate", Date);
            }
            response = given()
                    .relaxedHTTPSValidation()
                    .header("Content-Type", "application/json")
                    .log().all()
                    .body(jsonObj.toJSONString())
                    .post(BASE_URI + Payload.ReadJson().get("NagezUrl"))
                    .then().log().all().extract().response();

            return response;
        } catch (Exception e){
            throw  e;
        }
        }

        @Then("validate that response is 200 Ok")
    public void validateWhitelistResponseCode(){
            try {
                Assert.assertTrue(response.getStatusCode() == 200);
            } catch(Exception e){
                log.info("Response status code is "+response.getStatusCode() +"and expected to be 200 -ok" );
                throw  e;
            }

        }
    @And("validate response body")
    public void validateWhitelistResponseBody(){
        try {
        Assert.assertEquals (response.jsonPath().get("isSuccess"),true);
        Assert.assertEquals (response.jsonPath().get("errorMessage"),"Success");
    } catch(Exception e){
            log.info("isSuccess key value is "+response.jsonPath().get("isSuccess") +"and expected to be true" );
            log.info("errorMessage key value is "+response.jsonPath().get("errorMessage") +"and expected to be Success" );
        throw  e;
    }
    }
        }


