package homework2.agiletravel.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class PaymentPage {

    SelenideElement
            header = $("h2"),
            visa   = $("input[value='visa']"),
            holder = $("input[name='holder_name']"),
            number = $("input[name='card_number']"),
            payBtn = $("input[value='Pay now']"),
            confirmation = $("#confirmation");

    @Step("Проверка страницы оплаты")
    public void shouldBeOpened() {
        header.shouldHave(text("Pay by Credit Card"));
    }

    @Step("Заполнение данных карты")
    public void fillCard() {
        visa.click();
        holder.setValue("IVAN IVANOV");
        number.setValue("4111111111111111");
    }
    @Step("Оплата и проверка подтверждения бронирования")
    public void payAndCheckConfirmation() {
        payBtn.click();
        confirmation.shouldBe(visible);
        confirmation.shouldHave(text("Booking number:"));
        confirmation.shouldHave(text("Passenger Details: IVAN IVANOV"));
    }
}
