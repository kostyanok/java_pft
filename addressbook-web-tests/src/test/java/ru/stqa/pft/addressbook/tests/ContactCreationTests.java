package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testNewContactCreation() {
    List<ContactData> before= app.contact().getContactsList();
    ContactData contact = new ContactData().withFirstname("firstname").withLastname("lastname").withAddress("address").withMobilePhoneNumber("+38022746489").withHomePhoneNumber("+3859568272").withEmail("test@gmail.com").withGroup("test1");
    app.contact().create(contact, true);
    List<ContactData> after= app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

   // contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
