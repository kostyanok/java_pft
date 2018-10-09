package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
            wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
            type(By.name("username"), username);
            click(By.xpath(".//*[@id='login-form']/fieldset/input[2]"));
            type(By.name("password"), password);
            click(By.xpath(".//*[@id='login-form']/fieldset/input[3]"));
        }
    }
