package homework;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/*
 TODO: уточнить, достаточно ли для учебных автотестов держать всю логику в одном классе и
 нужно ли оборачивать в Step для алюр?
 */

public class FiveAT {
    @Test
    void AT_1 () {

        Configuration.pageLoadTimeout = 60000; // временное решение, так как страница долго грузится

        open("https://www.specialist.ru/");
        $x("//button[contains(text(),'Согласен(-на)')]")
                .shouldBe(visible)
                .click();

        $x("//a[contains(text(),'Форматы обучения')]")
                .shouldBe(visible)
                .click();

        $x("//a[contains(text(),'Свободное обучение')]")
                .scrollIntoView(true)
                .shouldBe(visible)
                .click();

        $x("//a[contains(text(),'Выбрать курс')]")
                .shouldBe(visible)
                .click();

        $x("//select[@id='Filter_CategoriesDirectionFilter']")
                .shouldBe(visible)
                .selectOption("Программирование");

        $x("//button[contains(text(),'Применить')]")
                .shouldBe(visible)
                .click();

        $x("//*[contains(text(),'Тестирование ПО')]")
                .scrollIntoView(true)
                .shouldBe(visible)
                .shouldHave(text("Тестирование ПО"));
    }
}
