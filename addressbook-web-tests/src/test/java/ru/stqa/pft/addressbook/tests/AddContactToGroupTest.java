package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
    Random r = new Random();
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData selectedContact = contacts.iterator().next();
    GroupData group = groups.iterator().next();
    Set<GroupData> before = selectedContact.getGroups();
    if (selectedContact.getGroups().size() == groups.size()) {
      app.goTo().groupPage();
      group = new GroupData().withName(String.format("test%s", r.nextInt()));
      app.group().create(group);
    }
    app.goTo().HomePage();
    app.contact().addToGroup(selectedContact, group);
    Set<GroupData> after = selectedContact.getGroups();
    assertThat(after, equalTo(new Groups(before).withAdded(group)));
  }
}

