package homework2.agiletravel;

import homework2.agiletravel.pages.FlightsSearchPage;
import homework2.agiletravel.pages.LoginPage;
import homework2.agiletravel.pages.PassengerDetailsPage;
import homework2.agiletravel.pages.PaymentPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class AgileTravelTests {

    LoginPage loginPage = new LoginPage();
    FlightsSearchPage flightsPage = new FlightsSearchPage();

    String USER = System.getenv("AGILE_USER");
    String PASS = System.getenv("AGILE_PASS");
    PassengerDetailsPage passengerPage = new PassengerDetailsPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUp() {
        open("https://travel.agileway.net/login");
    }

    @Test
    @Order(1)
    @DisplayName("AT01 Успешная авторизация пользователя")
    void AT01_successLogin() {
        loginPage.login(USER, PASS);
        loginPage.shouldBeLoggedIn("agileway");
    }

    @Test
    @Order(2)
    @DisplayName("AT02 Поиск рейсов — отображается таблица результатов")
    void AT02_searchFlights() {
        loginPage.login(USER, PASS);
        loginPage.shouldBeLoggedIn("agileway");

        flightsPage.selectFlightParams("New York", "Sydney");
        flightsPage.shouldSeeFlightsTable();
    }

    @Test
    @Order(3)
    @DisplayName("AT03 Полный путь бронирования авиабилета до экрана оплаты")
    void AT03_selectFlightAndContinue() {
        loginPage.login(USER, PASS);
        loginPage.shouldBeLoggedIn("agileway");

        flightsPage.selectFlightParams("New York", "Sydney");
        flightsPage.shouldSeeFlightsTable();
        flightsPage.selectFirstFlight();

        passengerPage.shouldBeOpened();
        passengerPage.fillPassenger("Ivan", "Ivanov");

        paymentPage.shouldBeOpened();
        paymentPage.fillCard();
        paymentPage.payAndCheckConfirmation();
    }
}

