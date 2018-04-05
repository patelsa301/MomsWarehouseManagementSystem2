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

public class InventoryTest {

    @Rule
    public final ActivityRule<Inventory> main = new ActivityRule<>(Inventory.class);

    @Test
    public void testAddingItem()
    {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        appCompatEditText.perform(replaceText("cheese"), closeSoftKeyboard());
    }

    @Test
    public void testAddItemButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bAddItem), withText("Add Item To Inventory"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test
    public void testViewItemsButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bViewInv), withText("View Inventory"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test
    public void testAddingCategory()
    {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.TFcategory), isDisplayed()));
        appCompatEditText.perform(replaceText("dairy"), closeSoftKeyboard());
    }

    @Test
    public void testAddCategoryButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bAddCat), withText("Create Category"), isDisplayed()));
        appCompatButton.perform(click());
    }




}
