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

public class ShoppingListTest {

    @Rule
    public final ActivityRule<ShoppingList> main = new ActivityRule<>(ShoppingList.class);

    @Test
    public void testEnteringShoppingList()
    {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.TFAddList), isDisplayed()));
        appCompatEditText.perform(replaceText("toilet paper"), closeSoftKeyboard());
    }

    @Test
    public void testAddListButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bAddList), withText("Add Item To Shopping List"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test
    public void testViewListButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bViewList), withText("View Shopping List"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test
    public void testList()
    {
        testEnteringShoppingList();
        testAddListButtonExists();
        testViewListButtonExists();
    }
}
