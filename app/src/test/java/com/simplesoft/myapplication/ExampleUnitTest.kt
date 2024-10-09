package com.simplesoft.myapplication

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() = runTest {
        val time = measureTimeMillis {
            val handler = Mockito.mock(Handler::class.java)
            val result = withTimeoutOrNull(1000L) {
                func1(handler)
            }
            println(result)
        }
        println(time)
    }

    suspend fun func1(handler: Handler): Boolean = suspendCancellableCoroutine {
        handler.postDelayed({
            it.resume(true)
        }, 2000)
    }

    suspend fun func2(handler: Handler): Boolean = suspendCoroutine {
        handler.postDelayed({
            it.resume(true)
        }, 2000)
    }
}