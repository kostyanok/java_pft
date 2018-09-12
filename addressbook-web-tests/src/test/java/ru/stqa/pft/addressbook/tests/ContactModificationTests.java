package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("firstname", "lastname", "address", "+38022746489", "+3859568272", "test@gmail.com", null, "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactsList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"firstname", "lastname", "address", "+38022746489", "+3859568272", "test@gmail.com", "test1@gmail.com", null);
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
