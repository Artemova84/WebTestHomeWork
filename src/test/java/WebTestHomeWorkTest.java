import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.SelenideElement.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTestHomeWorkTest {

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void bankCardTest() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Оксана Артемова");
        $("[data-test-id=phone] input").setValue("+79103696853");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        Thread.sleep(5000);
    }

    @Test
    void bankCardNegativeNameTest() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Oksana Artemova");
        $("[data-test-id=phone] input").setValue("+79103696853");
        $("[data-test-id=agreement]").click();
        $("button").click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = $("[data-test-id=name].input_invalid .input__sub").getText().trim();
        assertEquals(expected, actual);
        Thread.sleep(5000);
    }

    @Test
    void bankCardNegativeName2Test() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Oksana Artemova");
        $("[data-test-id=phone] input").setValue("+79103696853");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        Thread.sleep(5000);
    }

    @Test
    void bankCardNegativePhoneTest() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Оксана Артемова");
        $("[data-test-id=phone] input").setValue("89103696853");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        Thread.sleep(5000);
    }
}
