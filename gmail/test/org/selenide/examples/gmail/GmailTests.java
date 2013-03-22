package org.selenide.examples.gmail;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public abstract class GmailTests {
  @Rule
  public ScreenShooter screenShooter = ScreenShooter.failedTests();

  @BeforeClass
  public static void openInbox() {
    timeout = 10000;
    baseUrl = "http://gmail.com";
    open("/");
    login();

    $(byText("Inbox (11)")).shouldBe(visible);
  }

  @AfterClass
  public static void logout() {
    //$(byText("Sign out")).click();
    closeWebDriver();
  }

  private static void login() {
    $("#Email").val("drusha49@gmail.com");
    $("#Passwd").val(System.getProperty("password", "selenide"));
    $("#signIn").click();
  }
}
