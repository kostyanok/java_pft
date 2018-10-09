package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void ManageUserPage(){
        click(By.xpath(".//*[@id='sidebar']/ul/li[7]/a/i"));
        click(By.xpath(".//*[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a"));


    }
}
