package homework2.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    SelenideElement
            cityFrom = $("#departureCity"),
            cityTo = $("#arrivalCity"),
            date = $("#departureDate"),
            findButton = $x("//button[.='Найти']"),
            message = $("#searchMessage");

    @Step("Поиск рейсов")
    public void search(String from, String to, String depDate) {
        cityFrom.selectOption(from);
        cityTo.selectOption(to);
        date.setValue(depDate);
        findButton.click();
    }

    @Step("Проверка сообщения об ошибке")
    public void shouldHaveMessage(String textMsg) {
        message.shouldHave(text(textMsg));
    }
}
