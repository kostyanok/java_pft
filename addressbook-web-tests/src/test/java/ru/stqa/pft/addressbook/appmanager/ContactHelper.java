package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper {
  NavigationHelper navigationHelper = new NavigationHelper(wd);

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getAddress());
    type(By.name("mobile"),contactData.getMobilePhoneNumber());
    type(By.name("email"),contactData.getEmail());
    type(By.name("email2"),contactData.getEmail2());
    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }else {
      Assert.assertFalse(isElementPresent (By.name("new_group")));
    }
  }

  public void contactSelection(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void contactSelectionById (int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initContactModification(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"']")).click();

  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contactData, boolean b) {
    navigationHelper.goToAddContact();
    fillContactForm(contactData,b);
    submitNewContactCreation();
    navigationHelper.HomePage();
  }
  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact,false);
    submitContactModification();
  }
  public void delete(int index) {
    contactSelection(index);
    deleteSelectedContact();
    closeAlert();
  }
  public void delete(ContactData contact) {
    contactSelectionById (contact.getId());
    deleteSelectedContact();
    closeAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
  }

  public int getContactsCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactsList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements){
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }
  public Contacts all() {
    Contacts contacts = new Contacts();
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
}
