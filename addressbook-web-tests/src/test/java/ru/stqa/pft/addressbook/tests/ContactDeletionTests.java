package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "address", "+38022746489", "+3859568272", "test@gmail.com", null, "test1"), true);
        }
        int before = app.getContactHelper().getContactsCount();
        app.getContactHelper().contactSelection(before - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getGroupHelper().closeAlert();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactsCount();
        Assert.assertEquals(after, before - 1);
    }

}
