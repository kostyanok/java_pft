package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

public class UserHelper extends HelperBase {
    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void selectById(int id) {
        wd.findElement(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id + "']")).click();
    }

    public void resetPassword() {
        wd.findElement(By.xpath(".//*[@id='manage-user-reset-form']/fieldset/span/input")).click();
    }
    public String  findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

}
