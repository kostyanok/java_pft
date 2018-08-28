package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("firstname", "lastname", "address", "+38022746489", "+3859568272", "test@gmail.com", "test1@gmail.com", null), true);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToPage(By.linkText("home page"));
  }
}
