package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static tests.TestData.gender;
import static tests.TestData.phone;
import static tests.TestData.day;
import static tests.TestData.month;
import static tests.TestData.year;
import static tests.TestData.subject;
import static tests.TestData.hobby;
import static tests.TestData.picture;
import static tests.TestData.adress;
import static tests.TestData.state;
import static tests.TestData.city;

public class RegistrationWithPageObjectsTests extends TestBase {
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();

    RegistrationPage registrationPage = new RegistrationPage();
    @Tag("RegistrationForm")
    @Test
    void successfulRegistrationTest() {

        registrationPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .typeGender(gender)
                .typePhone(phone)
                .setDateOfBirth(day, month, year)
                .typeSubject(subject)
                .typeHobby(hobby)
                .typePicture(picture)
                .currentAddress(adress)
                .currentState(state)
                .currentCity(city)
                .submit();

        registrationPage.checkResultsTitle();
        registrationPage.checkResultsValue((firstName + " " + lastName));
        registrationPage.checkResultsValue((email));
        registrationPage.checkResultsValue((gender));
        registrationPage.checkResultsValue((phone));
        registrationPage.checkResultsValue((day + " " + month + "," + year));
        registrationPage.checkResultsValue((subject));
        registrationPage.checkResultsValue((hobby));
        registrationPage.checkResultsValue((picture));
        registrationPage.checkResultsValue((state + " " + city));
    }
}
