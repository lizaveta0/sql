package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement inputLogin = $("[data-test-id=login] input");
    private final SelenideElement inputPassword = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification] div.notification__content");

    public void fillForm(String login, String password) {
        inputLogin.setValue(login);
        inputPassword.setValue(password);
        loginButton.click();
    }

    public void checkErrorNotification(String error) {
        errorNotification.shouldBe(visible, exactText(error));
    }

}
