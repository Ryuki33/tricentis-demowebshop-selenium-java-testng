package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import com.wilsonmanaog.automation.model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class CheckoutPage extends BasePage {

    @FindBy(xpath="//div/h1[contains(text(),'Checkout')]")
    WebElement checkoutPageTitle;

    @FindBy(xpath="//li[@id='opc-billing']//h2[contains(text(),'Billing address')]")
    WebElement billingAddressStepTitle;

    @FindBy(xpath="//li[@id='opc-shipping']//h2[contains(text(),'Shipping address')]")
    WebElement shippingAddressStepTitle;

    @FindBy(xpath="//li[@id='opc-shipping_method']//h2[contains(text(),'Shipping method')]")
    WebElement shippingMethodStepTitle;

    @FindBy(xpath="//li[@id='opc-payment_method']//h2[contains(text(),'Payment method')]")
    WebElement paymentMethodStepTitle;

    @FindBy(xpath="//li[@id='opc-payment_info']//h2[contains(text(),'Payment information')]")
    WebElement paymentInfoStepTitle;

    @FindBy(xpath="//li[@id='opc-confirm_order']//h2[contains(text(),'Confirm order')]")
    WebElement confirmOrderStepTitle;

    @FindBy(css="li#opc-billing input[title='Continue']")
    WebElement billingAddressContinueButton;

    @FindBy(css="li#opc-shipping input[title='Continue']")
    WebElement shippingAddressContinueButton;

    @FindBy(css="li#opc-shipping_method input[value='Continue']")
    WebElement shippingMethodContinueButton;

    @FindBy(css="li#opc-payment_method input[value='Continue']")
    WebElement paymentMethodContinueButton;

    @FindBy(css="li#opc-payment_info input[value='Continue']")
    WebElement paymentInfoContinueButton;

    @FindBy(css="li#opc-confirm_order input[value='Confirm']")
    WebElement confirmOrderButton;

    @FindBy(css="li#opc-billing select#billing-address-select")
    WebElement billingAddressSelectDropdown;

    @FindBy(css="li#opc-shipping select#shipping-address-select")
    WebElement shippingAddressSelectDropdown;

    @FindBy(css="li#opc-billing select#BillingNewAddress_CountryId")
    WebElement billingAddressCountryDropdown;

    @FindBy(css="li#opc-shipping select#ShippingNewAddress_CountryId")
    WebElement shippingAddressCountryDropdown;

    @FindBy(css="li#opc-billing select#BillingNewAddress_StateProvinceId")
    WebElement billingAddressStateDropdown;

    @FindBy(css="li#opc-shipping select#ShippingNewAddress_StateProvinceId")
    WebElement shippingAddressStateDropdown;

    @FindBy(css="li#opc-billing input#BillingNewAddress_City")
    WebElement billingAddressCityTextBox;

    @FindBy(css="li#opc-shipping input#ShippingNewAddress_City")
    WebElement shippingAddressCityTextBox;

    @FindBy(css="li#opc-billing input#BillingNewAddress_Address1")
    WebElement billingAddressAddress1TextBox;

    @FindBy(css="li#opc-shipping input#ShippingNewAddress_Address1")
    WebElement shippingAddressAddress1TextBox;

    @FindBy(css="li#opc-billing input#BillingNewAddress_ZipPostalCode")
    WebElement billingAddressZipPostalCodeTextBox;

    @FindBy(css="li#opc-shipping input#ShippingNewAddress_ZipPostalCode")
    WebElement shippingAddressZipPostalCodeTextBox;

    @FindBy(css="li#opc-billing input#BillingNewAddress_PhoneNumber")
    WebElement billingAddressPhoneNumberTextBox;

    @FindBy(css="li#opc-shipping input#ShippingNewAddress_PhoneNumber")
    WebElement shippingAddressPhoneNumberTextBox;

    @FindBy(css="div.section.pickup-in-store input#PickUpInStore")
    WebElement inStorePickupCheckbox;

    @FindBy(css="li#opc-payment_info select#CreditCardType")
    WebElement creditCardSelectDropdown;

    @FindBy(css="li#opc-payment_info select#ExpireMonth")
    WebElement expireMonthSelectDropdown;

    @FindBy(css="li#opc-payment_info select#ExpireYear")
    WebElement expireYearSelectDropdown;

    @FindBy(css="li#opc-payment_info input#CardholderName")
    WebElement cardHolderNameTextBox;

    @FindBy(css="li#opc-payment_info input#CardNumber")
    WebElement cardNumberTextBox;

    @FindBy(css="li#opc-payment_info input#CardCode")
    WebElement cardCodeTextBox;

    @FindBy(css="li#opc-payment_info input#PurchaseOrderNumber")
    WebElement purchaseOrderNumberTextBox;

    @FindBy(css="li#opc-confirm_order ul.billing-info li")
    List<WebElement> billingInfo;

    @FindBy(css="li#opc-confirm_order ul.shipping-info li")
    List<WebElement> shippingInfo;

    @FindBy(xpath="//div[@class='section order-completed']//strong[contains(text(),'Your order has been successfully processed')]")
    WebElement orderSuccessMessage;

    @FindBy(xpath="//table[@class='cart-total']//tr")
    List<WebElement> orderFeesInfo;

    private final String SHIPPING_METHOD_RADIO_BUTTON = "//ul[@class='method-list']/li//input[contains(@value,'%s')]";
    private final String PAYMENT_METHOD_RADIO_BUTTON = "//ul[@class='method-list']/li//label[contains(text(),'%s')]/preceding-sibling::input";

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForPageToLoad() {
        waitElementToBeVisible(checkoutPageTitle);
    }

    public void waitForBillingAddressStep() {
        waitElementToBeVisible(billingAddressStepTitle);
    }

    public void waitForShippingAddressStep() {
        waitElementToBeVisible(shippingAddressStepTitle);
    }

    public void waitForShippingMethodStep() {
        waitElementToBeVisible(shippingMethodStepTitle);
    }

    public void waitForPaymentMethodStep() {
        waitElementToBeVisible(paymentMethodStepTitle);
    }

    public void waitForPaymentInfoStep() {
        waitElementToBeVisible(paymentInfoStepTitle);
    }

    public void waitForConfirmOrderStep() {
        waitElementToBeVisible(confirmOrderStepTitle);
    }

    public void selectNewBillingAddress() {
        selectOptionByVisibleText(billingAddressSelectDropdown, "New Address");
    }

    public void selectNewShippingAddress() {
        selectOptionByVisibleText(shippingAddressSelectDropdown, "New Address");
    }

    public void populateBillingAddress(Address billingAddress) {
        selectOptionByVisibleText(billingAddressCountryDropdown, billingAddress.getCountry());
        selectOptionByVisibleText(billingAddressStateDropdown, billingAddress.getState());
        typeText(billingAddressCityTextBox, billingAddress.getCity());
        typeText(billingAddressAddress1TextBox, billingAddress.getAddress());
        typeText(billingAddressZipPostalCodeTextBox, billingAddress.getZipCode());
        typeText(billingAddressPhoneNumberTextBox, billingAddress.getPhoneNumber());
    }

    public void populateShippingAddress(Address shippingAddress) {
        selectOptionByVisibleText(shippingAddressCountryDropdown, shippingAddress.getCountry());
        selectOptionByVisibleText(shippingAddressStateDropdown, shippingAddress.getState());
        typeText(shippingAddressCityTextBox, shippingAddress.getCity());
        typeText(shippingAddressAddress1TextBox, shippingAddress.getAddress());
        typeText(shippingAddressZipPostalCodeTextBox, shippingAddress.getZipCode());
        typeText(shippingAddressPhoneNumberTextBox, shippingAddress.getPhoneNumber());
    }

    public void completeBillingAddressStep(Address billingAddress) {
        waitForBillingAddressStep();
        selectNewBillingAddress();
        populateBillingAddress(billingAddress);
        click(billingAddressContinueButton);
    }

    public void completeShippingAddressStep(Map<String, Object> shippingInfo) {
        waitForShippingAddressStep();
        if ((boolean) shippingInfo.get("isInStorePickup")) {
            click(inStorePickupCheckbox);
        } else {
            selectNewShippingAddress();
            Map<String, String> shippingAddressData = (Map<String, String>) shippingInfo.get("shippingAddress");
            Address shippingAddress = new Address(shippingAddressData.get("country"), shippingAddressData.get("state"), shippingAddressData.get("city"), shippingAddressData.get("address1"), shippingAddressData.get("zipPostalCode"), shippingAddressData.get("phoneNumber"));
            populateShippingAddress(shippingAddress);
        }
        click(shippingAddressContinueButton);
    }

    public void selectShippingMethod(ShippingMethod shippingMethod) {
        String formattedXpath = String.format(SHIPPING_METHOD_RADIO_BUTTON, shippingMethod.getName());
        click(driver.findElement(By.xpath(formattedXpath)));
    }

    public void completeShippingMethodStepIfRequired(Map<String, Object> shippingInfo) {
        if (!((boolean) shippingInfo.get("isInStorePickup"))) {
            ShippingMethod shippingMethod = ShippingMethod.valueOf((String) shippingInfo.get("shippingMethod"));
            waitForShippingMethodStep();
            selectShippingMethod(shippingMethod);
            click(shippingMethodContinueButton);
        }
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        String formattedXpath = String.format(PAYMENT_METHOD_RADIO_BUTTON, paymentMethod.getName());
        click(driver.findElement(By.xpath(formattedXpath)));
    }

    public void completePaymentMethodStep(PaymentMethod paymentMethod) {
        waitForPaymentMethodStep();
        selectPaymentMethod(paymentMethod);
        click(paymentMethodContinueButton);
    }

    public void populateCreditCardInfo(CreditCardInfo creditCardInfo) {
        selectOptionByVisibleText(creditCardSelectDropdown, creditCardInfo.getCardType().getName());
        selectOptionByVisibleText(expireMonthSelectDropdown, creditCardInfo.getExpirationMonth());
        selectOptionByVisibleText(expireYearSelectDropdown, creditCardInfo.getExpirationYear());
        typeText(cardHolderNameTextBox, creditCardInfo.getCardholderName());
        typeText(cardNumberTextBox, creditCardInfo.getCardNumber());
        typeText(cardCodeTextBox, creditCardInfo.getCardCode());
    }

    public void populatePurchaseOrderInfo(String purchaseOrderNumber) {
        typeText(purchaseOrderNumberTextBox, purchaseOrderNumber);
    }

    public void completePaymentInfoStep(Map<String,Object> paymentDetails) {
        waitForPaymentInfoStep();
        String paymentMethod = PaymentMethod.valueOf((String) paymentDetails.get("paymentMethod")).getName();
        switch (paymentMethod) {
            case "Credit Card":
                Map<String, String> creditCardDetails = (Map<String, String>) paymentDetails.get("creditCardInfo");
                CreditCardInfo creditCardInfo = new CreditCardInfo(CreditCardType.valueOf(creditCardDetails.get("cardType")), creditCardDetails.get("cardholderName"), creditCardDetails.get("cardNumber"), creditCardDetails.get("expirationMonth"), creditCardDetails.get("expirationYear"), creditCardDetails.get("cardCode"));
                populateCreditCardInfo(creditCardInfo);
                break;
            case "Cash On Delivery":
            case "Check / Money Order":
                break;
            case "Purchase Order":
                String purchaseOrderNumber = (String) paymentDetails.get("purchaseOrderNumber");
                populatePurchaseOrderInfo(purchaseOrderNumber);
                break;
            default:
                throw new RuntimeException("Unsupported payment method: " + paymentMethod);
        }
        click(paymentInfoContinueButton);
    }

    public boolean isBillingAddressCorrectlyDisplayed(Address billingAddress) {
        return billingInfo.stream().anyMatch(e -> e.getText().contains(billingAddress.getCountry()))
                && billingInfo.stream().anyMatch(e -> e.getText().contains(billingAddress.getCity()))
                && billingInfo.stream().anyMatch(e -> e.getText().contains(billingAddress.getAddress()))
                && billingInfo.stream().anyMatch(e -> e.getText().contains(billingAddress.getZipCode()))
                && billingInfo.stream().anyMatch(e -> e.getText().contains(billingAddress.getPhoneNumber()));
    }

    public boolean isShippingAddressCorrectlyDisplayed(Address shippingAddress) {
        return shippingInfo.stream().anyMatch(e -> e.getText().contains(shippingAddress.getCountry()))
                && shippingInfo.stream().anyMatch(e -> e.getText().contains(shippingAddress.getCity()))
                && shippingInfo.stream().anyMatch(e -> e.getText().contains(shippingAddress.getAddress()))
                && shippingInfo.stream().anyMatch(e -> e.getText().contains(shippingAddress.getZipCode()))
                && shippingInfo.stream().anyMatch(e -> e.getText().contains(shippingAddress.getPhoneNumber()));
    }

    public boolean isPaymentMethodCorrectlyDisplayed(PaymentMethod paymentMethod) {
        return billingInfo.stream().anyMatch(e -> e.getText().contains(paymentMethod.getName()));
    }

    public boolean isShippingMethodCorrectlyDisplayed(ShippingMethod shippingMethod) {
        return shippingInfo.stream().anyMatch(e -> e.getText().contains(shippingMethod.getName()));
    }

    public boolean areProductCostDetailsCorrect(List<Product> products, double tax, ShippingMethod shippingMethod, PaymentMethod paymentMethod) {
        double orderSubTotal;
        double orderShippingCost = shippingMethod.getCost();
        double paymentMethodAdditionalFee = paymentMethod.getPaymentMethodAdditionalFee();
        double orderTotal;
        orderSubTotal = products.stream().mapToDouble(product -> product.getPrice() * product.getQuantity()).sum();
        orderTotal = orderSubTotal + orderShippingCost + paymentMethodAdditionalFee + tax;
        if (paymentMethod.getName().equalsIgnoreCase("Cash On Delivery") || paymentMethod.getName().equalsIgnoreCase("Check / Money Order")) {
            return orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Sub-Total')]/parent::td/following-sibling::td//span[@class='product-price']")).getText().trim().equalsIgnoreCase(String.format("%.2f", orderSubTotal)))
                    && orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Shipping')]/parent::td/following-sibling::td//span[@class='product-price']")).getText().trim().equalsIgnoreCase(String.format("%.2f", orderShippingCost)))
                    && orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Payment method additional fee')]/parent::td/following-sibling::td//span[@class='product-price']")).getText().trim().equalsIgnoreCase(String.format("%.2f", paymentMethodAdditionalFee)))
                    && orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Tax')]/parent::td/following-sibling::td//span[@class='product-price']")).getText().trim().equalsIgnoreCase(String.format("%.2f", tax)))
                    && orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Total')]/parent::td/following-sibling::td//span[@class='product-price order-total']")).getText().trim().equalsIgnoreCase(String.format("%.2f", orderTotal)));
        } else {
            return orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Sub-Total')]/parent::td/following-sibling::td//span[@class='product-price']")).getText().trim().equalsIgnoreCase(String.format("%.2f", orderSubTotal)))
                    && orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Shipping')]/parent::td/following-sibling::td//span[@class='product-price']")).getText().trim().equalsIgnoreCase(String.format("%.2f", orderShippingCost)))
                    && orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Tax')]/parent::td/following-sibling::td//span[@class='product-price']")).getText().trim().equalsIgnoreCase(String.format("%.2f", tax)))
                    && orderFeesInfo.stream().anyMatch(e -> e.findElement(By.xpath("//span[contains(text(),'Total')]/parent::td/following-sibling::td//span[@class='product-price order-total']")).getText().trim().equalsIgnoreCase(String.format("%.2f", orderTotal)));
        }
    }

    public boolean areConfirmOrderDetailsCorrect(Address billingAddress, Map<String, Object> shippingInfo, double tax, PaymentMethod paymentMethod, ShippingMethod shippingMethod, List<Product> products) {
        if ((boolean) shippingInfo.get("isInStorePickup")) {
            return isBillingAddressCorrectlyDisplayed(billingAddress)
                    && isPaymentMethodCorrectlyDisplayed(paymentMethod)
                    && isShippingMethodCorrectlyDisplayed(shippingMethod)
                    && areProductCostDetailsCorrect(products, tax, shippingMethod, paymentMethod);
        } else {
            Map<String, String> shippingAddressData = (Map<String, String>) shippingInfo.get("shippingAddress");
            Address shippingAddress = new Address(shippingAddressData.get("country"), shippingAddressData.get("state"), shippingAddressData.get("city"), shippingAddressData.get("address1"), shippingAddressData.get("zipPostalCode"), shippingAddressData.get("phoneNumber"));
            return isBillingAddressCorrectlyDisplayed(billingAddress)
                    && isShippingAddressCorrectlyDisplayed(shippingAddress)
                    && isPaymentMethodCorrectlyDisplayed(paymentMethod)
                    && isShippingMethodCorrectlyDisplayed(shippingMethod)
                    && areProductCostDetailsCorrect(products, tax, shippingMethod, paymentMethod);
        }
    }

    public boolean isConfirmOrderSuccessful() {
        waitForConfirmOrderStep();
        click(confirmOrderButton);
        return orderSuccessMessage.isDisplayed();
    }
}
