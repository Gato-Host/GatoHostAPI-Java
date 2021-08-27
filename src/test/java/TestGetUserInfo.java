import club.novaclient.gatohost.GatoHostAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestGetUserInfo {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        File userInfo = new File("user-info.json");
        JsonObject config = gson.fromJson(new FileReader(userInfo), JsonObject.class);
        GatoHostAPI api = new GatoHostAPI(config.get("baseURL").getAsString(), config.get("key").getAsString());
        GatoHostAPI.GetUserInfoQuery query = new GatoHostAPI.GetUserInfoQuery();
        GatoHostAPI.GetUserInfoResponse execute = api.execute(query);
        System.out.println("Domain ID: " + execute.getDomainID());
        GatoHostAPI.DomainListQuery domainListQuery = new GatoHostAPI.DomainListQuery();
        GatoHostAPI.DomainListResponse domainListResponse = api.execute(domainListQuery);
        for (GatoHostAPI.GatoDomain domain : domainListResponse.getDomains()) {
            if (domain.getId().equalsIgnoreCase(execute.getDomainID())) {
                System.out.println("Domain Name: " + domain.getName());
                return;
            }
        }
        System.out.println("Domain Information Not Found!");
    }

}
