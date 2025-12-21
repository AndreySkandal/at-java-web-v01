package homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AT3 {

    @BeforeEach
    void setUp() {
        open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/AT3_dataset.csv", numLinesToSkip = 1)
    void AT_3_CinemaCalculation(
            String age,
            String date,
            String session,
            String film,
            String expected
    ) {

        if (!age.isEmpty()) {
            $("input[name='age']").setValue(age);
        }

        if (!date.isEmpty()) {
            $("input[name='date']").setValue(date);
        }

        if (!session.isEmpty()) {
            $(String.format("input[name='session'][value='%s']", session)).click();
        }

        if (!film.isEmpty()) {
            $(String.format("input[name='film'][value='%s']", film)).click();
        }

        $("input[value='Рассчитать']").click();

        if (expected.equals("AGE_ERROR")) {
            $("body").shouldHave(text("только с 12 лет"));
        } else if (expected.equals("EMPTY_FIELDS")) {
            $("body").shouldHave(text("надо указать"));
        } else {
            $("div").shouldHave(text(expected));
        }
    }
}