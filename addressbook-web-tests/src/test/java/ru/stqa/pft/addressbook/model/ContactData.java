package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String homePhoneNumber;
  private final String mobilePhoneNumber;
  private final String email;
  private final String email2;

  public ContactData(String firstname, String lastname, String address, String homePhoneNumber, String mobilePhoneNumber, String email, String email2) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homePhoneNumber = homePhoneNumber;
    this.mobilePhoneNumber = mobilePhoneNumber;
    this.email = email;
    this.email2 = email2;
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
}
