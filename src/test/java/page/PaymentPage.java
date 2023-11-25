package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class PaymentPage {

    private final SelenideElement header = $(byText("Оплата по карте"));
    private final SelenideElement cardNumber = $(byText("Номер карты"));
    private final SelenideElement month = $(byText("Месяц"));
    private final SelenideElement year = $(byText("Год"));
    private final SelenideElement owner = $(byText("Владелец"));
    private final SelenideElement cvc = $(byText("CVC/CVV"));
    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");


    public PaymentPage() {
        header.shouldBe(visible);
        cardNumber.shouldBe(visible);
        month.shouldBe(visible);
        year.shouldBe(visible);
        owner.shouldBe(visible);
        cvc.shouldBe(visible);
        continueButton.shouldBe(visible);
        successNotification.shouldBe(hidden);
        errorNotification.shouldBe(hidden);
    }


    public void getSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void getDeclinedNotification() {
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

