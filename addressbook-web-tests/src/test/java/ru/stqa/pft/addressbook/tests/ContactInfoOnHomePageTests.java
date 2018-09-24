package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoOnHomePageTests extends TestBase {
  ContactData contact = new ContactData();
  ContactData contactInfoFromEditForm = new ContactData();

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    contact = app.contact().all().iterator().next();
    contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }

  @Test
  public void testContactPhones() {

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    //assertThat(contact.getHomePhoneNumber(), equalTo(cleaned(contactInfoFromEditForm.getHomePhoneNumber())));
    //assertThat(contact.getMobilePhoneNumber(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhoneNumber())));
    //assertThat(contact.getWorkPhoneNumber(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhoneNumber())));
  }

  @Test
  public void testContactEmails() {
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  @Test
  public void testContactAddress() {
    assertThat(contact.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhoneNumber(), contact.getMobilePhoneNumber(), contact.getWorkPhoneNumber())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactInfoOnHomePageTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergeAddresses(ContactData contact) {
    return Arrays.asList(contact.getAddress1(), contact.getAddress2())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
