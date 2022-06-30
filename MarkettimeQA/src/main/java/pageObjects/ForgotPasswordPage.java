package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import action.Action;
import base.ReptimeBase;

public class ForgotPasswordPage extends ReptimeBase{

	Action<?> action = new Action<Object>();

	@FindBy(xpath = "//input[@id='username']")
	WebElement enterUserName;

	@FindBy(xpath = "//span[text()='Send Reset Password Code']")
	WebElement clickOnSendCodeButton;

	@FindBy(xpath = "//a[text()=' Go Back to Login']")
	WebElement backToLoginbutton;

	public ForgotPasswordPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public ResetPasswordPage enterUsername(String username) {
		action.type(enterUserName, username);
		action.click(getDriver(), clickOnSendCodeButton);
		
		return new ResetPasswordPage();
	}

	public LoginPage backToLogin() {
		action.click(getDriver(), backToLoginbutton);
		return new LoginPage();
	}

}
