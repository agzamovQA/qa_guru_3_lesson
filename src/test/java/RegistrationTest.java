import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    @BeforeAll
    static void testBrowseConfiguration() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillingAllFields() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        $("#firstName").setValue("Johnny");
        $("#lastName").setValue("Silverhand");
        $("#userEmail").setValue("samurai23@nightcity.com");
        $("#userNumber").setValue("2023002077");

//        Выбираем пол
        $("[for=gender-radio-1]").click();

//        Выбор даты рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1988");
        $(".react-datepicker__day--016").click();

//        Выбор предметов + поиск по тексту в выпадающем окне
        $("#subjectsInput").setValue("E");
        $(".subjects-auto-complete__menu").shouldHave(text("Chemistry"));
        $(".subjects-auto-complete__menu").$(byText("Chemistry")).click();

//        Выбор хобби
        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-2]").click();
        $("[for=hobbies-checkbox-3]").click();

//        Загрузка фото
        $("#uploadPicture").uploadFromClasspath("img.png");

//        Ввод Адреса
        $("#currentAddress").scrollTo();
        $("#currentAddress").setValue("North California, Night City");

//        Выбор штата и города
        $("#state").click();
        $(".css-11unzgr").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $(".css-11unzgr").$(byText("Merrut")).click();

        $("#submit").click();

//        Проверка результата
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Johnny Silverhand"));
        $(".table-responsive").shouldHave(text("samurai23@nightcity.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("2023002077"));
        $(".table-responsive").shouldHave(text("16 November,1988"));
        $(".table-responsive").shouldHave(text("Chemistry"));
        $(".table-responsive").shouldHave(text("img.png"));
        $(".table-responsive").shouldHave(text("16 November,1988"));
        $(".table-responsive").shouldHave(text("North California, Night City"));
        $(".table-responsive").shouldHave(text("Uttar Pradesh Merrut"));
    }
}