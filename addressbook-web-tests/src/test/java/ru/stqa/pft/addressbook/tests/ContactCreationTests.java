package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testNewContactCreation() {
    Contacts before= app.contact().all();
    File photo = new File ("src/test/resources/cropped-brave_icon_512x.png");
    ContactData contact = new ContactData().withFirstname("firstname").withLastname("lastname")
            .withAddress1("address1").withMobilePhoneNumber("+38022746489").withHomePhoneNumber("+3859568272")
            .withEmail("test@gmail.com").withGroup("test1").withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after= app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
  }

}
