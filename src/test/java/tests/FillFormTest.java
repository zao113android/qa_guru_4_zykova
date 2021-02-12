package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class FillFormTest {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void confirmationIsDispplayedTest() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                name = firstName + " " + lastName,
                gender =  "Other",
                email = faker.internet().emailAddress(),
                state = "NCR",
                city = "Delhi",
                location = state + " " + city,
                number = faker.number().digits(10),
                subject = "English",
                address = faker.address().fullAddress(),
                month = "January",
                year = faker.number().numberBetween(1900, 2100) + "",
                day = faker.number().numberBetween(1, 29) + "",
                hobby = "Reading",
                picture = "17ab8d64109801.5ac71cf4d999c.jpg";

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        // just strings
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);

        // select radiobutton
        $(byText(gender)).click();

        // numbers
        $("#userNumber").setValue(number);

        // date
        $("#dateOfBirthInput").click();
        $((".react-datepicker__month-select")).selectOption(month);
        $((".react-datepicker__year-select")).selectOption(year);
        $((".react-datepicker__day--0" + day)).click();

        // reworked for dropdown
        //$("#subjectsInput").setValue(subject).pressEnter();
        $("#subjectsInput").setValue(subject);
        $(".subjects-auto-complete__menu-list").$(byText(subject)).click();

        // checkbox
        $(byText(hobby)).click();

        // file uploading
        $("#uploadPicture").uploadFile(new File("/Users/annazykova/Documents/An/Photo/" + picture));

        // address
        $("#currentAddress").setValue(address);

        // I have a small screen, so that I have to scroll
        $("#state").scrollTo();

        // state and city
        // reworked, now it's better
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        // quick check of filled info
        // also wanna change to field - value validation
        $(byClassName("table-responsive")).shouldHave(text(name), text(location), text(email));

        // and the better checker
        $x("//td[text()='Student Name']").parent().shouldHave(text(name));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(number));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(day + " " + month + "," + year));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subject));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby));
        $x("//td[text()='Picture']").parent().shouldHave(text(picture));
        $x("//td[text()='Address']").parent().shouldHave(text(address));
        $x("//td[text()='State and City']").parent().shouldHave(text(location));
    }

}
