package hello.vuespring.repository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class University {

    String path = System.getProperty("user.dir") + "/src/main/java/hello/vuespring/repository/db.json";

    JSONParser parser = new JSONParser();
    Reader reader = new FileReader(path);

    JSONObject jsonObject = (JSONObject) parser.parse(reader);

    public University() throws IOException, ParseException {
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
