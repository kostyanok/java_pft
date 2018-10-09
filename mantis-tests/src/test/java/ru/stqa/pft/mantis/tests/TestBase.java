package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {
  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config/config_inc.php"), "config/config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config/config_inc.php");
    app.stop();
  }

  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    IssueData issue = app.soap().getIssue(issueId);
    if (issue.getStatus().getName().equals("new")) {
      return true;
    }
    return false;
  }

  public boolean isIssueOpenBugify(int issueId){
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
    JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
    String status = jsonObject.get("issues")
            .getAsJsonArray().get(0).getAsJsonObject()
            .get("state_name")
            .getAsString();
    if (status.equals("Open")){
      return true;
    }
    return false;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public void skipIfNotFixedBugify(int issueId) throws IOException, ServiceException {
    if (isIssueOpenBugify(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
