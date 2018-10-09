package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final String browser;
  private final Properties properties;
  private WebDriver wd;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;
  private SoapHelper soapHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private UserHelper userHelper;
  private DbHelper dbHelper;
  private RestHelper restHelper;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
  }

  public void stop() {

    if (wd != null){
      wd.quit();
    }
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }
  public SessionHelper sessionHelper() {
    if (sessionHelper == null) {
      sessionHelper = new SessionHelper(this);
    }
    return sessionHelper;
  }
  public NavigationHelper goTo() {
    if (navigationHelper == null) {
      navigationHelper = new NavigationHelper(this);
    }
    return navigationHelper;
  }
  public UserHelper user() {
    if (userHelper == null) {
      userHelper = new UserHelper(this);
    }
    return userHelper;
  }
  
  public FtpHelper ftp(){
    if(ftp == null){
      ftp = new FtpHelper(this);
    }
    return ftp;
  }
  public MailHelper mail(){
    if(mailHelper == null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james(){
    if(jamesHelper == null){
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }
  public SoapHelper soap(){
    if(soapHelper == null){
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }
  public RestHelper rest(){
    if(restHelper == null){
      restHelper = new RestHelper(this);
    }
    return restHelper;
  }
  public DbHelper db(){
    return dbHelper;
  }

  public WebDriver getDriver() {
    if(wd == null){
      if(browser.equals(BrowserType.FIREFOX)){
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
      }else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      }else if (browser.equals(BrowserType.IEXPLORE)) {
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }
}
