import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataGenerator.*;
import static data.SQLHelper.cleanDataBase;
import static data.SQLHelper.getVerificationCode;


public class LoginTest {

    private LoginPage loginPage;
    private VerificationPage verificationPage;
    private DashboardPage dashboardPage;
    String login;
    String password;


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        loginPage = new LoginPage();
        login = getAuthInfo().getLogin();
        password = getAuthInfo().getPassword();
    }

    @AfterAll
    static void tearDownAll() {
        cleanDataBase();
    }

    @Test
    public void testLoginPositive() {
        loginPage.fillForm(login, password);
        verificationPage = new VerificationPage();
        String code = getVerificationCode();
        verificationPage.fillVerificationForm(code);
        dashboardPage = new DashboardPage();
        dashboardPage.checkLoadPage();
    }

    @Test
    public void testLoginPasswordIncorrect() {
        AuthInfo authInfo = generateUser();
        login = authInfo.getLogin();
        password = authInfo.getPassword();

        loginPage.fillForm(login, password);
        loginPage.checkErrorNotification("Ошибка!  \nНеверно указан логин или пароль");
    }

    @Test
    public void testCodeIncorrect() {
        String code = generateVerificationCode().getCode();
        loginPage.fillForm(login, password);
        verificationPage = new VerificationPage();
        verificationPage.fillVerificationForm(code);
        verificationPage.verifyErrorNotification("Ошибка! \nНеверно указан код! Попробуйте ещё раз.");
    }

}
