package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData().withFirstname("firstname").withLastname("lastname").withAddress("address").withHomePhoneNumber("+38022746489").withMobilePhoneNumber("+3859568272").withEmail("test@gmail.com").withGroup("test1"), true);
        }
    }
    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().getContactsList();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().HomePage();
        List<ContactData> after = app.contact().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
