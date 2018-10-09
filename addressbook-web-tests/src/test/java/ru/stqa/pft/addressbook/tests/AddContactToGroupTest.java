package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {
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
  public void testAddContactToGroup() {
    app.goTo().HomePage();
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData selectedContact = contacts.iterator().next();
    GroupData group = groups.iterator().next();
    ContactData contact = new ContactData()
            .withId(selectedContact.getId()).withFirstname(selectedContact.getFirstname()).withLastname(selectedContact.getLastname())
            .withAddress1(selectedContact.getAddress()).withMobilePhoneNumber(selectedContact.getMobilePhoneNumber())
            .withHomePhoneNumber(selectedContact.getHomePhoneNumber()).withWorkPhoneNumber(selectedContact.getWorkPhoneNumber())
            .withEmail(selectedContact.getEmail()).withEmail2(selectedContact.getEmail2())
            .inGroup(group);
    Set<GroupData> before = contact.getGroups();
    if (contact.getGroups().size() == groups.size()){
      app.goTo().groupPage();
      GroupData newGroup = new GroupData().withName("test3");
      app.group().create(newGroup);
      app.goTo().HomePage();
      app.contact().addToGroup(contact, newGroup);
    }else{
      for(int i = 0; i < contact.getGroups().size(); i++){
        if (contact.getGroups().iterator().next().getId() != group.getId()){
          app.contact().addToGroup(contact, group);
        }
      }
    }
    app.goTo().HomePage();
    Set<GroupData> after = contact.getGroups();
    assertThat(after, equalTo(new Groups(before).withAdded(group)));
  }
}

