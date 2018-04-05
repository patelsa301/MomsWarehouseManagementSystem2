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

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityRule<MainActivity> main = new ActivityRule<>(MainActivity.class);
    @Test
    public void testEnteringEmail()
    {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.TFemail), isDisplayed()));
        appCompatEditText.perform(replaceText("email@gmail.com"), closeSoftKeyboard());
    }

    @Test
    public void testEnteringPassword()
    {
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.TFpassword), isDisplayed()));
        appCompatEditText2.perform(replaceText("12345"), closeSoftKeyboard());
    }

    @Test
    public void testLoginButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bLogin), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test
    public void testRegisterButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bRegister), withText("Register"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test
    public void testValidLogin()
    {
        testEnteringEmail();
        testEnteringPassword();
        testLoginButtonExists();
    }

    @Test
    public void testInvalidLogin()
    {
        testEnteringEmail();
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.TFpassword), isDisplayed()));
        appCompatEditText2.perform(replaceText("incorrectPassword"), closeSoftKeyboard());
        testLoginButtonExists();
    }


}
