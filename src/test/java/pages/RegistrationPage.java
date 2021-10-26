package pages;

import com.codeborne.selenide.SelenideElement;
import components.Calendar;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    private final static String PAGE_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private SelenideElement
            finalTitle = $("#example-modal-sizes-title-lg"),
            titleForm = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmail = $("#userEmail"),
            genterWrapper = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            hobbieWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            finalClick = $("#submit"),
            modal = $(".table-responsive");

    private Calendar calendar = new Calendar();

    @Step("Открываем главную страницу")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        titleForm.shouldHave(text(PAGE_TITLE));
        return this;
    }

    @Step("Пишем имя {firstName}")
    public RegistrationPage typeFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;

    }

    @Step("Пишем фамилию")
    public RegistrationPage typeLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;

    }

    @Step("Пишем почту")
    public RegistrationPage typeEmail(String email) {
        userEmail.setValue(email);
        return this;

    }

    @Step("Пишем имя телефон")
    public RegistrationPage typePhone(String phone) {
        userNumber.setValue(phone);
        return this;

    }

    @Step("Выбираем дату рождения")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;

    }

    @Step("Выбираем пол")
    public RegistrationPage typeGender(String gender) {
        genterWrapper.$(byText(gender)).click();
        return this;

    }

    @Step("Выбираем предмет")
    public RegistrationPage typeSubject(String subject) {
        subjectInput.setValue(subject).pressEnter();
        return this;

    }

    @Step("Выбираем хобби")
    public RegistrationPage typeHobby(String hobby) {
        hobbieWrapper.$(byText(hobby)).click();
        return this;

    }

    @Step("Вставлем картинку")
    public RegistrationPage typePicture(String picture) {
        uploadPicture.uploadFromClasspath(picture);
        return this;

    }

    @Step("Выбираем текущий адрес")
    public RegistrationPage currentAddress(String adress) {
        currentAddress.val(adress);
        return this;

    }

    @Step("Выбрать штат")
    public RegistrationPage currentState(String state) {
        stateInput.setValue(state).pressEnter();
        return this;

    }

    @Step("Выбираем город")
    public RegistrationPage currentCity(String city) {
        cityInput.setValue(city).pressEnter();
        return this;

    }

    @Step("Подтверждаем введенные данные")
    public RegistrationPage submit() {
        finalClick.click();
        return this;
    }

    @Step("Проверяем заголов")
    public void checkResultsTitle() {
        finalTitle.shouldHave(text(RESULTS_TITLE));
    }

    @Step("Проверяем введенные данные")
    public void checkResultsValue(String value) {
        modal.shouldHave(text(value));

    }
}
