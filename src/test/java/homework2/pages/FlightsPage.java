package homework2.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FlightsPage {

    SelenideElement
            flightsTable = $("#flightsTable"),
            registerButton = $x("//button[.='Зарегистрироваться']");

    @Step("Проверка что рейсы не найдены")
    public void shouldHaveNoFlights() {
        flightsTable.shouldHave(text("Рейсов по вашему запросу не найдено"));
    }

    @Step("Выбор рейса для бронирования")
    public void selectFlight() {
        registerButton.click();
    }
}
