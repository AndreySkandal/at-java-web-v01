package homework2.agiletravel.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    SelenideElement
            username = $("#username"),
            password = $("#password"),
            signInButton = $("input[value='Sign in']"),
            flashNotice = $("#flash_notice"),
            userNav = $("#user_nav");

    @Step("Логин в систему")
    public void login(String user, String pass) {
        username.setValue(user);
        password.setValue(pass);
        signInButton.click();
    }

    @Step("Проверка успешного входа")
    public void shouldBeLoggedIn(String username) {
        flashNotice.shouldHave(text("Signed in!"));
        userNav.shouldHave(text("Welcome " + username));
    }
}

