package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withFirstname("firstname").withLastname("lastname").withAddress("address").withHomePhoneNumber("+38022746489").withMobilePhoneNumber("+3859568272").withEmail("test@gmail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().getContactsList();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("firstname").withLastname("lastname").withAddress("address").withMobilePhoneNumber("+38022746489").withHomePhoneNumber("+3859568272").withEmail("test@gmail.com").withEmail2("test1@gmail.com");
    app.contact().modify(index, contact);
    app.goTo().HomePage();
    List<ContactData> after = app.contact().getContactsList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
