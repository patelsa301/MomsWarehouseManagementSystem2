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

public class ExpensesTest{

    @Rule
    public final ActivityRule<Expenses> main = new ActivityRule<>(Expenses.class);


    @Test
    public void testRefreshExp()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnExp), withText("Refresh Expenses"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test
    public void testHomepageButtonExistsExp()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnReturnExp), withText("Return to Homepage"), isDisplayed()));
        appCompatButton.perform(click());
    }
}