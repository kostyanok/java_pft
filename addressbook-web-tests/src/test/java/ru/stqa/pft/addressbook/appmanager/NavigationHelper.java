package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
      && wd.findElement(By.tagName("h1")).equals("Groups")
      && isElementPresent(By.name("new"))){
      return;
    }
    click(By.linkText("groups"));
  }
  public void HomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }
  public void goToAddContact() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))){
      return;
    }
    click(By.linkText("add new"));
  }
}
