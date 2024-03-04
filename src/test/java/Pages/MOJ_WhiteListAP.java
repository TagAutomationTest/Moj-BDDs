package Pages;
import APIs_Payloads.Payloads;
import Steps.Takaamol;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class MOJ_WhiteListAP {

    Takaamol Obj=new Takaamol();
    String RequiredFunction;
    Response response;
    Logger log = Logger.getLogger(MOJ_WhiteListAP.class);
    String BASE_URI;
    //String MOCBaseUrl= "https://api-test.moj.gov.local";

    public String SetEnv(String Env){
        switch (Env){
            case "Testing":
                BASE_URI="https://execution-tst.moj.gov.local/Najiz.Api";
                break;

            case "Staging":
                BASE_URI="https://10.162.2.161:9001";
                break;
        }
        return BASE_URI;
    }

    @Test
    //Happy
    //Test Case 441655: White list Service-verify that Identity will move to white list table after performing execution API with RequiredAction=1 "Add"
    public Response MoveIdentityToWhiteList(String RequiredFunction,String Identity) throws Exception {
        try {
            Long IdentityNo =Long.parseLong(Identity);
            response = given()
                    .relaxedHTTPSValidation()
                    .header("Content-Type","application/json")
                    .log().all()
                    .body(Payloads.WhiteListHappyPayload(RequiredFunction,Identity))
                    .post(BASE_URI + "/api/Najiz/ocp/IllegalWhiteList/AddUpdateIdentityIllegalWhiteList")
                    .then().log().all().extract().response();
            return  response;
        }
        //message_create.message_data.text
        catch (Exception e) {
            log.info("Failed to move identity to white list "+e.getMessage());
            throw e;
        }

    }

    @Test
    //Test Case 441656: White list Service-verify that Identity is deleted from white list table after performing execution API with RequiredAction=2 "Delete"
    public void DeleteIdentityFromWhiteList(String RequiredFunction,String Identity ) throws Exception {
        try {
            response = given()
                    .relaxedHTTPSValidation()
                    .log().all()
                    .body(Payloads.WhiteListHappyPayload(RequiredFunction,Identity))
                    .post(BASE_URI + "/api/Najiz/ocp/IllegalWhiteList/AddUpdateIdentityIllegalWhiteList")
                    .then().log().all().extract().response();
            Assert.assertTrue(response.getStatusCode() == 200);
            log.info("Identity deleted properly from white list");
        }
        //message_create.message_data.text
        catch (Exception e) {
            log.info("Failed during delete identity from white list "+e.getMessage());
            throw e;
        }

    }

    public void GetIdentirydetailsFromMoc(String Identity) throws Exception {
        try{
            Long Identitynumber =Long.parseLong(Identity);
            response = given()
                    .relaxedHTTPSValidation()
                    .headers("Authorization", "Bearer " + "")
                    .queryParam("CRNumber", Identity)
                    .log().all()
                    .get(BASE_URI + "/v6/integ_gsb_mci_inquiry/GetCRInformationByCRNumber")
                    .then().log().all().extract().response();
            Assert.assertTrue(response.getStatusCode() == 200);
        }

        catch (Exception e){
            if (e.getMessage().toString().contains("No such host is known")){
                log.info("Connection Issue");
                throw new Exception("Connection Issue"+e.getMessage());
            }
            else {
                log.info("Identity didn't exist on MOC "+e.getMessage());
                throw e;
            }
        }
    }
}


