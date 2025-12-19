package homework;

//ТК смотреть в AT2_TestCases.md
//TODO: добавил @Order и @DisplayName - так в нашей корп. среде и так как будто удобней/привычней

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AT2 {

    @BeforeEach
    void setUp() {
        open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
    }

    @Test
    @Order(1)
    @DisplayName("TC-1 Корректный расчёт без скидок")
    void AT2_TC1() {

        $(By.name("age")).setValue("25");
        $(By.name("date")).setValue("21-12-2025"); // выходной день
        $(By.name("session")).selectRadio("7");    // 19:00
        $(By.name("film")).selectRadio("crime");   // 18+
        $x("//input[@type='submit']").click();
        //sleep(20000);
        $("div").shouldHave(text("500")); //Ожидаемый результат: Стоимость билета 500 рублей
    }

    @Test
    @Order(2)
    @DisplayName("TC-2 Детская скидка")
    void AT2_TC2() {

        $(By.name("age")).setValue("5");
        $(By.name("date")).setValue("21-12-2025");
        $(By.name("session")).selectRadio("5");    // 16:00
        $(By.name("film")).selectRadio("king");    // 0+

        $x("//input[@type='submit']").click();
        //sleep(20000);
        $("div").shouldHave(text("250")); //Ожидаемый результат:Стоимость билета: 500-250=250руб.
    }

    @Test
    @Order(3)
    @DisplayName ("TC-3. Несоответствие возрастного ограничения")
    void AT2_TC3() {

        $(By.name("age")).setValue("16");
        $(By.name("date")).setValue("22-12-2025");
        $(By.name("session")).selectRadio("7");    // 19:00
        $(By.name("film")).selectRadio("crime");   // 18+

        $x("//input[@type='submit']").click();
        //sleep(20000);
        $("div").shouldHave(text("только с 18 лет")); //Ожидаемый результат:Выводится сообщение *Этот фильм можно смотреть только с 18 лет*

    }

    @Test
    @Order(4)
    @DisplayName ("TC-4. Не заполнены обязательные поля")
    void AT2_TC4() {

        $(By.name("date")).setValue("22-12-2025");
        $(By.name("session")).selectRadio("7");
        $(By.name("film")).selectRadio("king");
        $x("//input[@type='submit']").click();
        //sleep(20000);
        $("div").shouldHave(text("надо указать возраст")); //Ожидаемый результат:Отображается сообщение: *надо указать возраст для расчёта стоимости билета*.

    }

    @Test
    @Order(5)
    @DisplayName ("TC-5. Проверка суммирования всех скидок")
    void AT2_TC5() {

        $(By.name("age")).setValue("5");
        $(By.name("date")).setValue("22-12-2025"); // будний
        $(By.name("session")).selectRadio("1");    // 09:30
        $(By.name("film")).selectRadio("king");    // 0+

        $x("//input[@type='submit']").click();
        //sleep(20000);
        $("div").shouldHave(text("100")); //Ожидаемый результат:Стоимость билета: 100 рублей

    }
}

