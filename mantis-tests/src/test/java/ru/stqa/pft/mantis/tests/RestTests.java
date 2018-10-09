package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{
    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }
    @Test
    public void testCreateIssue() throws IOException, ServiceException {
        skipIfNotFixedBugify(345);
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSummary("Test issue").withDescription("New test issue");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues,oldIssues);
    }
}
