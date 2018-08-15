package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void testNewContactCreation() {
        app.getNavigationHelper().goToPage(By.linkText("add new"));
        app.getContactHelper().fillContactForm(new ContactData("firstname", "lastname", "address", "+38022746489", "+3859568272", "test@gmail.com", "test1@gmail.com"));
        app.getContactHelper().submitNewContactCreation();
        app.getNavigationHelper().goToPage(By.linkText("home page"));
    }

}
