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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GooglePlayTest {

    private Context appContext;
    private UiDevice mDevice;
    private UiObject editText;
    private UiObject goToMarketButton;
    private UiObject installButton;
    private UiObject fiveStars;
    private UiObject submitButton;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void installApp() {
        appContext = InstrumentationRegistry.getTargetContext();
        mDevice = UiDevice.getInstance(getInstrumentation());
        UiObject installButton = mDevice
                .findObject(new UiSelector().text(appContext.getString(R.string.install)));
        editText = mDevice.findObject(new UiSelector().description("editText"));
        goToMarketButton = mDevice.findObject(new UiSelector().description("button"));
        try {
            editText.setText("artemshumidub.ru.news");
            mDevice.pressBack();
            sleep(2);
            goToMarketButton.click();
            sleep(3);
            installButton.click();
            sleep(15);
            mDevice.swipe(400,1000, 400,600,8);
            fiveStars = mDevice.findObject(new UiSelector().description("5"));
            goToMarketButton = mDevice.findObject(new UiSelector().resourceId("com.android.vending:id/review_continue_button"));
            fiveStars.click();
            submitButton.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void sleep(long sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
