package test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBaseHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditUITest {
    DashboardPage page = open("http://localhost:8080/", DashboardPage.class);
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Card with APPROVED status")
    void shouldSuccessPaymentByCard() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        creditPage.inputData(cardInfo);
        creditPage.getSuccessNotification();
        var actual = DataBaseHelper.getStatusCreditRequest();
        assertEquals("APPROVED", actual);
    }

    @Test
    @DisplayName("Card with DECLINED status")
    void shouldDeclinePaymentByCard() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        creditPage.inputData(cardInfo);
        creditPage.getErrorNotification();
        var actual = DataBaseHelper.getStatusCreditRequest();
        assertEquals("DECLINED", actual);
    }

    //Без заполнения полей формы

    @Test
    @DisplayName("Card with empty fields")
    void shouldFailValidationWithEmptyFields () {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyFields();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    //Картой с невалидным номером длиной 16 цифр
    @Test
    @DisplayName("Card with random number")
    void shouldDeclineTransactionWithRandomNumber() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithRandomNumber();
        creditPage.inputData(cardInfo);
        creditPage.checkingDeclinedNotification();
    }

    //С пустым полем Номер карты
    @Test
    @DisplayName("Card with empty number")
    void shouldFailValidationWithEmptyCardNumber () {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    //Картой с невалидным номером длиной 15 цифр
    @Test
    @DisplayName("Card with 15 numbers")
    void shouldFailValidationWithCard15Numbers() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith15();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //Картой с номером длиной 17 цифр
    @Test
    @DisplayName("Card with 17 numbers")
    void shouldFailValidationWithCard17Numbers() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith17();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //Картой с номером, состоящим из спецсимволов
    @Test
    @DisplayName("Card with with special characters")
    void shouldFailValidationCardWithSpecialCharacters() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }
    //C пустым полем Месяц getCardInfoWithNullMonth
    //Cо значением 00 в поле Месяц getCardInfoWithMonthWithTwoZero
    //Cо значением 13 в поле Месяц getCardInfoWith13Month
    //Cо значением, состоящим из одной цифры в поле Месяц getCardInfoWithMonthWith1Number
    //Cо значением, состоящим из спецсимвола в поле Месяц getCardInfoWithMonthWithSpecialCharacters





}
