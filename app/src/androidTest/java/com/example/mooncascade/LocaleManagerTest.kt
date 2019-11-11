package com.example.mooncascade

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mooncascade.localizationsupport.LocaleManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class LocaleManagerTest {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext()  {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mooncascade", appContext.packageName)
    }

    @Test
    fun getLanguageTestNotNull(){
        assertNotNull(LocaleManager().getLanguage(appContext))
    }

    @Test
    fun setLocaleTest(){
        assertSame(appContext,LocaleManager().setLocale(appContext,"en"))
    }

}
