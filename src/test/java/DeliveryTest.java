import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {

    static String date1;

    @BeforeAll
    static void GetDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy "); //создаем объект для дальнейшего форматирования даты
        Calendar calendar = Calendar.getInstance(); //создаем объект календарь и получаем текущую системную дату
        calendar.add(Calendar.DAY_OF_YEAR, 3); //добавляем 3 дня к текущей дате
        Date futureDateTime = calendar.getTime();
        date1 = dateFormat.format(futureDateTime); //форматируем дату
    }

    @Test
    public void shouldSendTheForm() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Челябинск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] input").setValue(date1);
        $("[data-test-id='name'] input").setValue("Кузнецова Александра");
        $("[data-test-id='phone'] input").setValue("+79820000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
