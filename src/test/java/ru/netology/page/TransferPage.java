package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement button = $("[data-test-id='action-transfer']");
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement actionTransfer = $(byText("Пополнение карты"));
    private SelenideElement error = $("[data-test-id='error-message']");

    public TransferPage() {
        actionTransfer.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        rechargeCard(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void rechargeCard(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        from.setValue(cardInfo.getCardNumber());
        button.click();
    }

    public void findErrorMessage(String expectedText) {
        error.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}