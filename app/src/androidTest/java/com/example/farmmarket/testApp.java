package com.example.farmmarket;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

public class testApp {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Before
    public void setup() {
        mActivityRule.getActivity();
    }

    @Test
    public  void testThatErrorMessageDoesNotShow(){
        onView(withId(R.id.logPass)).check(matches(not(isDisplayed())));
    }


    @Test
    public void testPasswordLenght() {
        onView(withId(R.id.password)).perform(typeText("abcde"));
        onView(withId(R.id.btnSignIn)).perform(click());

        onView(withId(R.id.logPass))
                .check(matches(isDisplayed()))
                .check(matches(withText("password can not be less than 6")));
    }
    @Test
    public void testValidPassword(){
        onView(withId(R.id.password)).perform(typeText("a valid password"));
        onView(withId(R.id.btnSignIn)).perform(click());

        onView(withId(R.id.logPass)).check(matches(not(isDisplayed())));
    }
}
