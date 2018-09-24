package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {


  @Test
  public void testNewContactCreation() {
    Contacts before= app.contact().all();
    ContactData contact = new ContactData().withFirstname("firstname").withLastname("lastname").withAddress("address").withMobilePhoneNumber("+38022746489").withHomePhoneNumber("+3859568272").withEmail("test@gmail.com").withGroup("test1");
    app.contact().create(contact, true);
    Contacts after= app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

   // contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
  }

}
