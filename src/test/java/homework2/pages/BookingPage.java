package homework2.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BookingPage {

    SelenideElement
            passport = $("#passportNumber"),
            finishButton = $x("//button[.='Завершить регистрацию']"),
            message = $("#registrationMessage");

    @Step("Ввод номера паспорта")
    public void setPassport(String value) {
        passport.setValue(value);
    }

    @Step("Завершение бронирования")
    public void finish() {
        finishButton.click();
    }

    @Step("Проверка сообщения")
    public void shouldHaveMessage(String textMsg) {
        message.shouldHave(text(textMsg));
    }
}
