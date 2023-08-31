import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest2 {

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldSendTheForm() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Че");
        $$(".menu-item").find(text("Челябинск")).click();
        $("[data-test-id='date']").click();
        int daysToAdd = 7;
        String planningDate = generateDate(daysToAdd, "dd.MM.yyyy");
        if (!generateDate(daysToAdd, "MM").equals(generateDate(3, "MM"))) {
        $("[data-step='1']").click(); }
        $$("[data-day]").findBy(text(generateDate(daysToAdd, "d"))).click();
        $("[data-test-id='name'] input").setValue("Кузнецова Александра");
        $("[data-test-id='phone'] input").setValue("+79820000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
