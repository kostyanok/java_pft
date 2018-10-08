package ru.stqa.pft.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import ru.stqa.pft.mantis.model.Issue;

import java.util.Set;

public class RestHelper {
    private ApplicationManager app;

    public RestHelper(ApplicationManager app){
        this.app = app;
    }

    public Set<Issue> getIssues() {
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
       // JsonElement states = parsed.getAsJsonObject().get("state_name");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }
    public int createIssue(Issue newIssue){
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSummary())
                .parameter("description", newIssue.getDescription())
                .post("http://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
