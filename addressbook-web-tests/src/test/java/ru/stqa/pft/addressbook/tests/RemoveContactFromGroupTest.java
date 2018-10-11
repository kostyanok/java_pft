package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withFirstname("firstname").withLastname("lastname").withAddress1("address").withHomePhoneNumber("+38022746489").withMobilePhoneNumber("+3859568272").withEmail("test@gmail.com")/*.withGroup("test1")*/, true);
    }
    app.goTo().groupPage();
    if(app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testRemoveContactFromGroup () {
    app.goTo().HomePage();
    Contacts contacts = app.db().contacts();
    GroupData group = app.db().groups().iterator().next();
    ContactData selectedContact = contacts.iterator().next();
    ContactData contact = new ContactData()
            .withId(selectedContact.getId()).withFirstname(selectedContact.getFirstname()).withLastname(selectedContact.getLastname())
            .withAddress1(selectedContact.getAddress()).withMobilePhoneNumber(selectedContact.getMobilePhoneNumber())
            .withHomePhoneNumber(selectedContact.getHomePhoneNumber()).withWorkPhoneNumber(selectedContact.getWorkPhoneNumber())
            .withEmail(selectedContact.getEmail()).withEmail2(selectedContact.getEmail2());
    Set<GroupData> before = contact.getGroups();
    if (contact.getGroups().size() == 0){
      app.contact().addToGroup(contact, group);
    }
    app.goTo().HomePage();
    app.contact().removeFromGroup(contact, group);
    Set<GroupData>after = contact.getGroups();
    assertThat(after, equalTo(new Groups(before).without(group)));
  }
}
