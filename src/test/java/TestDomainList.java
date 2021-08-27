import club.novaclient.gatohost.GatoHostAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestDomainList {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        File userInfo = new File("user-info.json");
        JsonObject config = gson.fromJson(new FileReader(userInfo), JsonObject.class);
        GatoHostAPI api = new GatoHostAPI(config.get("baseURL").getAsString(), config.get("key").getAsString());
        GatoHostAPI.DomainListQuery query = new GatoHostAPI.DomainListQuery();
        GatoHostAPI.DomainListResponse execute = api.execute(query);
        GatoHostAPI.GatoDomain[] domains = execute.getDomains();
        for (GatoHostAPI.GatoDomain domain : domains) {
            System.out.println(domain);
        }
    }

}
