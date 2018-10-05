package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstname;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhoneNumber;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhoneNumber;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhoneNumber;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;
  @Transient
  private String allPhones;
  @Transient
  private String allEmails;
  @Transient
  private String allAddresses;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();

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
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(homePhoneNumber, that.homePhoneNumber) &&
            Objects.equals(mobilePhoneNumber, that.mobilePhoneNumber) &&
            Objects.equals(workPhoneNumber, that.workPhoneNumber) &&
            Objects.equals(email, that.email) &&
            Objects.equals(email2, that.email2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, address, homePhoneNumber, mobilePhoneNumber, workPhoneNumber, email, email2);
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public String getWorkPhoneNumber() {
    return workPhoneNumber;
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

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAllAddresses() {
    return allAddresses;
  }

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    } else
      return null;
  }


  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withWorkPhoneNumber(String workPhoneNumber) {
    this.workPhoneNumber = workPhoneNumber;
    return this;
  }


  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress1(String address1) {
    this.address = address1;
    return this;
  }

  public ContactData withHomePhoneNumber(String homePhoneNumber) {
    this.homePhoneNumber = homePhoneNumber;
    return this;
  }

  public ContactData withMobilePhoneNumber(String mobilePhoneNumber) {
    this.mobilePhoneNumber = mobilePhoneNumber;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


  public ContactData withAllAddresses(String allAddresses) {
       this.allAddresses = allAddresses;
    return this;
  }

  public Set<GroupData> getGroups() {
    return groups;
  }

  public ContactData withPhoto(String photo) {
    this.photo = photo;
    return this;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
  public ContactData fromGroup(GroupData group) {
    groups.remove(group);
    return this;
  }

}
