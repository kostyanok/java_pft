package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testLogin() throws IOException {
        app.sessionHelper().login("administrator", "root");
        app.goTo().ManageUserPage();
        Users before = app.db().users();
        UserData selectedContact = before.iterator().next();
        app.user().selectById(selectedContact.getId());
        app.user().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000);
        String confirmationLink = app.user().findConfirmationLink (mailMessages, selectedContact.getEmail());
        app.registration().finish(confirmationLink,selectedContact.getUsername(), selectedContact.getPassword());
        assertTrue(app.newSession().login(selectedContact.getUsername(), selectedContact.getPassword()));

    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
