package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData selectedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(selectedContact.getId()).withFirstname(selectedContact.getFirstname()).withLastname(selectedContact.getLastname())
            .withAddress1(selectedContact.getAddress()).withMobilePhoneNumber(selectedContact.getMobilePhoneNumber())
            .withHomePhoneNumber(selectedContact.getHomePhoneNumber()).withWorkPhoneNumber(selectedContact.getWorkPhoneNumber())
            .withEmail(selectedContact.getEmail()).withEmail2(selectedContact.getEmail2())
            .inGroup(groups.iterator().next());
    app.contact().addToGroup(contact);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(selectedContact).withAdded(contact)));
  }
}

