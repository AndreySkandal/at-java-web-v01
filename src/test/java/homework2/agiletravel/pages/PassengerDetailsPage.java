package homework2.agiletravel.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class PassengerDetailsPage {

    SelenideElement
            firstName = $("input[name='passengerFirstName']"),
            lastName  = $("input[name='passengerLastName']"),
            nextBtn   = $("input[value='Next']"),
            header    = $("h2");

    @Step("Ввод данных пассажира")
    public void fillPassenger(String fname, String lname) {
        firstName.setValue(fname);
        lastName.setValue(lname);
        nextBtn.click();
    }

    @Step("Проверка страницы данных пассажира")
    public void shouldBeOpened() {
        header.shouldHave(text("Passenger Details"));
    }
}
