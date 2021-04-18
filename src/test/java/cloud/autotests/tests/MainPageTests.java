package cloud.autotests.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Story("Login tests")
public class MainPageTests extends TestBase {

    @Test
    @DisplayName("User can search on the site")
    void userCanSearchOnTheSite() {
        step("Open site", () -> {
            open("/");
            $("div.nav__logo a").shouldHave(attribute("title","DataArt"));
        });
        step("Enter search string", () -> {
            $(".icon--svg__search").shouldBe(visible).click();
            $("[name=search]").setValue("qa").pressEnter();
        });
        step("Search results are displayed", () -> {
            $(byText("Пошук за запитом «qa»")).shouldBe(visible);
            $(withText("Знайдено")).shouldBe(visible);

            String[] searchResult = $(withText("Знайдено")).getText().split(" ");
            int result = Integer.parseInt(searchResult[1]);
            assertThat(result).isGreaterThan(0);
        });
    }
}
