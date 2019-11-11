package com.example.mooncascade

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.mooncascade.data.ForecastWeather
import com.example.mooncascade.ui.MainActivity
import junit.framework.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkisnetconnect(){
        assert(activityRule.activity.mainPresenter.isInternetExist())
    }

    @Test
    fun checklocalcache(){
        activityRule.activity.mainPresenter.loadDatafromCache()
    }

    @Test
    fun checkinetresponse(){
        activityRule.activity.mainPresenter.loadDatafromInternet(
                                            activityRule.activity.retrofitCall.getforecastweather())
        activityRule.activity.mainPresenter.loadDatafromInternet(null)
    }

}