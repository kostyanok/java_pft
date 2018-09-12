package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testNewContactCreation() {
    List<ContactData> before= app.getContactHelper().getContactsList();
    ContactData contact = new ContactData("firstname", "lastname", "address", "+38022746489", "+3859568272", "test@gmail.com", null, "test1");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after= app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
