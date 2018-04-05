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

public class RegisterTest {
    @Rule
    public final ActivityRule<Register> main = new ActivityRule<>(Register.class);
    @Test
    public void testEnteringEmail()
    {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.TFemailreg), isDisplayed()));
        appCompatEditText.perform(replaceText("email@gmail.com"), closeSoftKeyboard());
    }

    @Test
    public void testEnteringPassword1()
    {
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.TFpass), isDisplayed()));
        appCompatEditText2.perform(replaceText("12345"), closeSoftKeyboard());
    }

    @Test
    public void testEnteringPassword2()
    {
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.TFpass2), isDisplayed()));
        appCompatEditText3.perform(replaceText("12345"), closeSoftKeyboard());
    }

    @Test
    public void testRegisterButtonExists()
    {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bCreateAccount), withText("Register"), isDisplayed()));
        appCompatButton.perform(click());
    }

    @Test
    public void validRegistration()
    {
        testEnteringEmail();
        testEnteringPassword1();
        testEnteringPassword2();
        testRegisterButtonExists();
    }

    @Test
    public void invalidRegistration()
    {
        testEnteringEmail();
        testEnteringPassword1();
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.TFpass2), isDisplayed()));
        appCompatEditText3.perform(replaceText("12345678901023345"), closeSoftKeyboard());
        testRegisterButtonExists();
    }

}
