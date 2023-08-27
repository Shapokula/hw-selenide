import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest2 {

    @Test
    public void shouldSendTheForm() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Че");
        $$(".menu-item").find(text("Челябинск")).click();
        $("[data-test-id='date']").click();
        $("[data-step='1']").click();
        $$(".calendar__day").find(text("3")).click();
        $("[data-test-id='name'] input").setValue("Кузнецова Александра");
        $("[data-test-id='phone'] input").setValue("+79820000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
