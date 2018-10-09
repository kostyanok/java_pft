package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException {
        app.sessionHelper().login("administrator", "root");
        app.goTo().ManageUserPage();
        Users before = app.db().users();
        Set<UserData> usersWithoutAdmin = app.db().users().withoutUser(before, 1);
        UserData selectedUser = usersWithoutAdmin.iterator().next();
        app.user().selectById(selectedUser.getId());
        app.user().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000);
        String confirmationLink = app.user().findConfirmationLink(mailMessages, selectedUser.getEmail());
        app.registration().finish(confirmationLink, selectedUser.getUsername(), selectedUser.getPassword());
        assertTrue(app.newSession().login(selectedUser.getUsername(), selectedUser.getPassword()));

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
