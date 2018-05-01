package e.shreypatel.momswarehousemanagementsystem;

import android.support.test.espresso.ViewInteraction;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;//*
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class ViewShoppingListTest {

    @Rule
    public final ActivityRule<ShoppingList> main = new ActivityRule<>(ShoppingList.class);

    @Test
    public void testSearchList() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.TFAddList), isDisplayed()));
        appCompatEditText.perform(replaceText("cheese"), closeSoftKeyboard());
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.quant_List), isDisplayed()));
        appCompatEditText2.perform(replaceText("5"), closeSoftKeyboard());
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bAddList), withText("Add Item To Shopping List"), isDisplayed()));
        appCompatButton.perform(click());
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.bViewList), withText("View Shopping List"), isDisplayed()));
        appCompatButton2.perform(click());
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.searchFilter), isDisplayed()));
        appCompatEditText3.perform(replaceText("cheese"), closeSoftKeyboard());
    }

    @Test
    public void testHomepageButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnHomepage), withText("Return to Homepage"), isDisplayed()));
        appCompatButton.perform(click());
    }

}