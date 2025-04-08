package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {
    private final SelenideElement inputCode = $("[data-test-id=code] input");
    private final SelenideElement submitButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification] .notification__content");


    public void verifyErrorNotification(String expectedText) {
        errorNotification.shouldHave(exactText(expectedText)).shouldBe(visible);
    }


    public void fillVerificationForm(String code) {
        inputCode.setValue(code);
        submitButton.click();
    }
}
