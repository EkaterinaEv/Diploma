package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private final SelenideElement header = $(byText("Кредит по данным карты"));
    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $("[placeholder='08']");
    private final SelenideElement year = $("[placeholder='22']");
    private final SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $("[placeholder='999']");
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private final SelenideElement invalidValueFormat = $(byText("Неверный формат"));

    private final SelenideElement invalidOwner = $(byText("Поле обязательно для заполнения"));

    public CreditPage() {
        header.shouldBe(visible);
    }

    public void getSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
        successNotification.$("[class=notification__title]").should(text("Успешно"));
        successNotification.$("[class=notification__content]").should(text("Операция одобрена Банком."));
        successNotification.shouldBe(hidden);
    }

    public void getErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
        errorNotification.$("[class=notification__title]").should(text("Ошибка"));
        errorNotification.$("[class=notification__content]").should(text("Ошибка! Банк отказал в проведении операции."));
        errorNotification.shouldBe(hidden);
    }

    public void inputData(DataHelper.CardInfo card) {
        cardNumber.setValue(card.getCardNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        owner.setValue(card.getOwner());
        cvc.setValue(card.getCvc());
        continueButton.click();
    }

    public void checkingEmptyField() {
        invalidOwner.shouldBe(Condition.visible);
    }

    public void checkingWrongFormat() {
        invalidValueFormat.shouldBe(Condition.visible);
    }

    public void checkingDeclinedNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}
