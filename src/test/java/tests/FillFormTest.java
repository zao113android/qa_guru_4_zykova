package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FillFormTest {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void confirmationIsDispplayedTest() {
        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue("Ann");
        $("#lastName").setValue("Z");
        $("#userEmail").setValue("mail@gmail.com");

        // I don't like this variant. Still thinking how ro fix it with .selectRadio()
        // I thought about $("#gender-radio-3").selectRadio("Other");
        // Also $("#gender-radio-3").setSelected(true);
        $(byText("Other")).click();

        // Wanna add here autogeneration with limit 10
        $("#userNumber").setValue("0123456789");

        $("#dateOfBirthInput").click();
        $(byClassName("react-datepicker__month-select")).selectOption(0);
        $(byClassName("react-datepicker__year-select")).selectOption("1993");
        // this one should be fixed, still can't find the way how to choose exact day
        $(byClassName("react-datepicker__month")).click();

        // is it a hack too? think that there is an alternative way
        $("#subjectsInput").setValue("English");
        $("#subjectsInput").pressEnter();

        // the same hack as with radiobutton
        $(byText("Reading")).click();

        $("#uploadPicture").uploadFile(new File("/Users/annazykova/Documents/An/Photo/17ab8d64109801.5ac71cf4d999c.jpg"));

        $("#currentAddress").setValue("street");

        $("#state").click();
        $("#react-select-3-input").setValue("NCR");
        $("#react-select-3-input").pressEnter();

        // the same question as for subjectsInput
        // like selectOptionByValue
        // but haven't found how to apply it here, doesn't work for me
        $("#city").click();
        $("#react-select-4-input").setValue("delhi");
        $("#react-select-4-input").pressEnter();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

}
