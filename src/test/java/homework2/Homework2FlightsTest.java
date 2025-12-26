package homework2;

import homework2.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Homework2FlightsTest {

    LoginPage loginPage = new LoginPage();
    SearchPage searchPage = new SearchPage();
    FlightsPage flightsPage = new FlightsPage();
    BookingPage bookingPage = new BookingPage();

    @BeforeEach
    void setUp() {
        open("https://slqamsk.github.io/cases/slflights/v01/");
        loginPage.login(System.getenv("FLIGHT_USER"), System.getenv("FLIGHT_PASS"));
        loginPage.shouldBeLoggedIn("Иванов Иван Иванович");
    }

    @Test
    @Order(1)
    @DisplayName("BR-02-02 Поиск с одинаковыми городами")
    void HW2_01_CitiesAreEqual() {
        searchPage.search("Москва", "Москва", "16.03.2026");
        sleep(20000);
        searchPage.shouldHaveMessage("Город вылета и город назначения должны различаться");
    }

    @Test
    @Order(2)
    @DisplayName("BR-03-05 Рейсы не найдены")
    void HW2_02_NoFlightsFound() {
        searchPage.search("Казань", "Париж", "16.03.2026");
        sleep(20000);
        flightsPage.shouldHaveNoFlights();
    }

    @Test
    @Order(3)
    @DisplayName("BR-04-05 Неверный формат номера паспорта")
    void HW2_03_WrongPassport() {
        searchPage.search("Москва", "Нью-Йорк", "16.03.2026");
        flightsPage.selectFlight();
        bookingPage.setPassport("паспорт");
        bookingPage.finish();
        sleep(20000);
        bookingPage.shouldHaveMessage("Номер паспорта должен содержать только цифры и пробелы");
    }

    @Test
    @Order(4)
    @DisplayName("BR-04-06 Успешное бронирование")
    void HW2_04_SuccessBooking() {
        searchPage.search("Москва", "Нью-Йорк", "16.03.2026");
        flightsPage.selectFlight();
        bookingPage.finish();
        sleep(20000);
        bookingPage.shouldHaveMessage("Регистрация успешно завершена!");
    }
}
