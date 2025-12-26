package homework2.agiletravel.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FlightsSearchPage {

    SelenideElement
            fromPort = $("select[name='fromPort']"),
            toPort = $("select[name='toPort']"),
            departDay = $("#departDay"),
            departMonth = $("#departMonth"),
            returnDay = $("#returnDay"),
            returnMonth = $("#returnMonth"),
            flightsTable = $("#flights"),
            firstFlightCheckbox = $("#flights tbody tr:first-child input[type='checkbox']"),
            continueButton = $("input[value='Continue']");

    @Step("Выбор параметров перелёта")
    public void selectFlightParams(String from, String to) {
        fromPort.selectOption(from);
        toPort.selectOption(to);
        departDay.selectOption("10");
        departMonth.selectOptionContainingText("March 2026");
        returnDay.selectOption("20");
        returnMonth.selectOptionContainingText("March 2026");
    }

    @Step("Проверка появления таблицы рейсов")
    public void shouldSeeFlightsTable() {
        flightsTable.shouldBe(visible);
    }

    @Step("Выбор первого рейса")
    public void selectFirstFlight() {
        firstFlightCheckbox.click();
        continueButton.click();
    }
    @Step("Выбор первого рейса и переход к данным пассажира")
    public void selectFirstFlightAndContinue() {
        $("#flights").shouldBe(visible);
        $("#flights input[type='checkbox']").click();
        $("input[value='Continue']").click();
    }

}

