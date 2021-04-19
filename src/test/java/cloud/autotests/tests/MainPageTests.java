package cloud.autotests.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.*;
import static org.assertj.core.api.Assertions.assertThat;

@Story("Main page tests")
public class MainPageTests extends TestBase {

    @Test
    @Severity(NORMAL)
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

    @Test
    @Severity(NORMAL)
    @DisplayName("City of development can be selected")
    void cityOfDevelopmentCanBeSelected() {
        step("Open site", () ->  open("/"));
        step("Select city of development", () -> {
            $("#citySelect__title").shouldBe(visible).shouldHave(text("Центри розробки"));
            $("#citySelect__title").hover();
            $("[data-ga-label=Odessa]").click();
        });
        step("Selected city is displayed", () -> {
            $("#citySelect__title").shouldBe(visible).shouldHave(text("Україна, Одеса"));
        });
    }

    @Test
    @Severity(MINOR)
    @DisplayName("Social media icons and links are displayed")
    void socialMediaIconsAreDisplayed() {
        step("Open site", () ->  open("/"));
        step("Check social media icons are displayed", () -> {
            step("Facebook icon is displayed",() -> {
            $(".icon--facebook").shouldBe(visible).
                    shouldHave(attribute("href", "https://www.facebook.com/DataArt.Dev"));
            });
            step("Youtube icon is displayed",() -> {
                $(".icon--youtube").shouldBe(visible).
                        shouldHave(attribute("href", "https://www.youtube.com/user/DataArt"));
            });
            step("Instagram icon is displayed",() -> {
                $(".icon--instagram").shouldBe(visible).
                        shouldHave(attribute("href", "https://instagram.com/dataart/"));
            });
            step("Twitter icon is displayed",() -> {
                $(".icon--twitter").shouldBe(visible).
                        shouldHave(attribute("href", "https://twitter.com/DataArt"));
            });
            step("GitHub icon is displayed",() -> {
                $(".icon--Github").shouldBe(visible).
                        shouldHave(attribute("href", "https://github.com/Dataart"));
            });
        });
    }
}
