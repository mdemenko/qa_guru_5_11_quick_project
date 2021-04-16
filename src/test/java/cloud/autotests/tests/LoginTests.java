package cloud.autotests.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Login tests")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Successful authorization to some demowebshop")
    void loginTest() {
        step("Open login page", () -> {
            open("/login");
            $(".page-title").shouldHave(text("Welcome, Please Sign In!"));
        });

        step("Fill in login form", () -> {
            $("#Email").val(TestData.getUserLogin());
            $("#Password").val(TestData.getUserPassword())
                    .pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(TestData.getUserLogin())));
    }

}
