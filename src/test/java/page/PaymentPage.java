package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PaymentPage {

    private final SelenideElement header = $(byText("Оплата по карте"));
    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $("[placeholder='08']");
    private final SelenideElement year = $("[placeholder='22']");
    private final SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $("[placeholder='999']");
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    // нужны ли переменные такого типа?:
    private final SelenideElement invalidFormat = $(byText("Неверный формат"));


    public PaymentPage() {
        header.shouldBe(visible);
    }


    public void getSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void getErrorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    public void inputData(DataHelper.CardInfo card) {
        cardNumber.setValue(card.getCardNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        owner.setValue(card.getOwner());
        cvc.setValue(card.getCvc());
        continueButton.click();
    }

}

