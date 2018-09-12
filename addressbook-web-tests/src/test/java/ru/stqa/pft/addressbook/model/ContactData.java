package ru.stqa.pft.addressbook.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String homePhoneNumber;
  private final String mobilePhoneNumber;
  private final String email;
  private final String email2;
  private String group;
  private int id;

  public ContactData(String firstname, String lastname, String address, String homePhoneNumber, String mobilePhoneNumber, String email, String email2, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homePhoneNumber = homePhoneNumber;
    this.mobilePhoneNumber = mobilePhoneNumber;
    this.email = email;
    this.email2 = email2;
    this.group = group;
  }
  public ContactData(int id, String firstname, String lastname, String address, String homePhoneNumber, String mobilePhoneNumber, String email, String email2, String group) {
    this.id  = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homePhoneNumber = homePhoneNumber;
    this.mobilePhoneNumber = mobilePhoneNumber;
    this.email = email;
    this.email2 = email2;
    this.group = group;
  }
  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", id=" + id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhoneNumber() {
    return homePhoneNumber;
  }

  public String getMobilePhoneNumber() {
    return mobilePhoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getGroup() {
    return group;
  }

}
