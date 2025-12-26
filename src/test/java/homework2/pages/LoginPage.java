package homework2.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    SelenideElement
            username = $("#username"),
            password = $("#password"),
            loginButton = $("#loginButton"),
            greeting = $("#greeting");

    @Step("Логин в систему")
    public void login(String user, String pass) {
        username.setValue(user);
        password.setValue(pass);
        loginButton.click();
    }

    @Step("Проверка успешного входа")
    public void shouldBeLoggedIn(String fio) {
        greeting.shouldHave(text("Добро пожаловать, " + fio));
    }
}
