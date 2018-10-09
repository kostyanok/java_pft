package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {
  NavigationHelper navigationHelper = new NavigationHelper(wd);

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhoneNumber());
    type(By.name("mobile"), contactData.getMobilePhoneNumber());
    type(By.name("work"), contactData.getWorkPhoneNumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    attach(By.name("photo"), contactData.getPhoto());
   if (creation) {
     if(contactData.getGroups().size() > 0){
       Assert.assertTrue(contactData.getGroups().size() == 1);
       new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
     }
   } else {
    Assert.assertFalse(isElementPresent(By.name("new_group")));
  }
  }

  public void contactSelectionById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public ContactData create(ContactData contactData, boolean b) {
    navigationHelper.goToAddContact();
    fillContactForm(contactData, b);
    submitNewContactCreation();
    contactCache = null;
    navigationHelper.HomePage();
    return contactData;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    contactSelectionById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    closeAlert();
  }
  public void addToGroup(ContactData contact, GroupData group) {
    downloadAllContacts();
    contactSelectionById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    click(By.name("add"));
    contactCache = null;
  }
  public void removeFromGroup(ContactData contact, GroupData group) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    contactSelectionById(contact.getId());
    click(By.name("remove"));
    contactCache = null;
  }
  public void downloadAllContacts() {

    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactsList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      String allAddresses = element.findElement(By.xpath(".//td[4]")).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAllAddresses(allAddresses);
      String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
      //ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
      //         .withHomePhoneNumber(phones[0]).withMobilePhoneNumber(phones[1]).withWorkPhoneNumber(phones[2]);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String address1 = wd.findElement(By.name("address")).getAttribute("value");
    String address2 = wd.findElement(By.name("address2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withMobilePhoneNumber(mobile).withHomePhoneNumber(home).withWorkPhoneNumber(work).withEmail(email)
            .withEmail2(email2).withAddress1(address1);

  }
}
