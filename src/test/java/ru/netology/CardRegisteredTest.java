package ru.netology;

import com.codeborne.selenide.Condition;
import net.bytebuddy.asm.Advice;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardRegisteredTest {
    private String generateDate(long AddDays, String pattern) {
        return LocalDate.now().plusDays(AddDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void SuccessRegistered() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Великий Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Сальников Максим");
        $("[data-test-id='phone'] input").setValue("+79209234363");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + date));



    }
}
