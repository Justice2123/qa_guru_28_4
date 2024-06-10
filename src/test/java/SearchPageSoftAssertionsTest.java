import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SearchPageSoftAssertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

    }

    @Test
    void searchPageOnGithubTest() {
        open("https://github.com/");
        $("div.search-input-container").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("div.kXssRI div").first().$("a").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();
        $("#wiki-body").shouldHave(text("Welcome to the selenide wiki!"));
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(byText("SoftAssertions")).shouldBe(visible).click();
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class}) " +
                "class Tests { " +
                "  @Test " +
                "  void test() { " +
                "    Configuration.assertionMode = SOFT; " +
                "    open(\"page.html\"); " +
                " " +
                "    $(\"#first\").should(visible).click(); " +
                "    $(\"#second\").should(visible).click(); " +
                "  } " +
                "}"));
        

    }



}
