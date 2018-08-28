package com.shumidub.googleplaytester;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GooglePlayTest {

    Context appContext;
    UiDevice mDevice;
    UiObject editText;
    UiObject button;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void installApp() {
        appContext = InstrumentationRegistry.getTargetContext();
        mDevice = UiDevice.getInstance(getInstrumentation());

        UiObject installButton = mDevice
                .findObject(new UiSelector().text(appContext.getString(R.string.install)));

        editText = mDevice.findObject(new UiSelector().description("editText"));
        button = mDevice.findObject(new UiSelector().description("button"));

        try {
            editText.setText("artemshumidub.ru.news");
            mDevice.pressBack();
            button.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            installButton.click();

            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mDevice.swipe(400,1000, 400,600,8);


        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
