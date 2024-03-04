package APIs_Payloads;

import Steps.Takaamol;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class Payloads {
    Takaamol Takaam = new Takaamol();
    Map<String, String> WhiteListPayload;
    String CompanyName;
    String Nationality;
    String legalTypeID;
    String PartyTypeId;
    String LegalTypeAR;
    String CRStatusReasonAr;
    String RequiredAction;
    String ExpirationDate;
    String OcpRequestId;
    String IdentityId;

    public static String UpdateDaemTicketPayload() {
        return "{\n" +
                "    \"state\": 2, \n" +
                "    \"close_code\": 2,\n" +
                "    \"close_notes\": \"Test\", \n" +
                "    \"u_cancellation_notes\": \"test\",\n" +
                "    \"comments\": \"test\" \n" +
                "}";
    }

    public static String WhiteListHappyPayload(String RequiredFun, String Identitty) throws Exception {
        try {
            // Long Identitynumber =Long.parseLong(Identitty);
            Integer RequiredAction;
            if (RequiredFun.equalsIgnoreCase("Add")) {
                RequiredAction = 1;
            } else if (RequiredFun.equalsIgnoreCase("Delete")) {
                RequiredAction = 2;
            } else {
                throw new Exception("Entered" + RequiredFun + "Is not valid");
            }
            return "{\"MOC_CRNameAR\":\"مطاعم عالم البخاري المميز لتقديم الوجبات\" ,\n" +
                    "\"MOC_CRNationalNumber\":\"7012291279\" ,\n" +
                    "\"MOC_CRlegalTypeID\":\"214\" ,\n" +
                    "\"PartyTypeId\":\"2\" ,\n" +
                    "\"MOC_CRLegalTypeAR\":\"ذات مسئولية محدودة مختلطة\" ,\n" +
                    "\"MOC_CRStatusId\":\"0\" ,\n" +
                    "\"MOC_CRStatusReasonAr\":\"السجل التجاري قائم\" ,\n" +
                    "\"RequiredAction\":" + RequiredAction + "\r\n" +
                    ",\"ExpirationDate\" :\"2026-01-12 12:43:17.2797078\",\n" +
                    "\"OcpRequestId\" :\"1\",\n" +
                    " \"IdentityId\" :\"" + Identitty + "\"}";
        } catch (Exception e) {

            throw e;
        }
    }

public JSONObject ReadJson() throws IOException, ParseException {
    try {
    JSONParser parser = new JSONParser();
    String FilePath = System.getProperty("user.dir") + "/src/test/testdata/TestData.Json";
    Object obj = parser.parse(new FileReader(FilePath));
    JSONObject jsonObj = new JSONObject((Map) obj);
    return jsonObj;
    } catch (Exception e) {
        throw e;
    }
}
    public JSONObject PrepareWhitleListPayload() throws IOException, ParseException, IllegalAccessException {
        JSONArray ja_data;
        try {
            ja_data = (JSONArray) ReadJson().get("WhiteList");
            ja_data.get(0);

        } catch (Exception e) {
            throw e;
        }


        return (JSONObject) ja_data.get(0);
    }
}

