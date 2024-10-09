package com.simplesoft.myapplication

import android.os.Handler
import android.os.Looper
import android.util.Log
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
        /*val handler = Mockito.mock(Handler::class.java)
        val result = withTimeoutOrNull(1000L) {
            func2(handler)
        }
        println(result)*/
        val job1 = launch {
            testTryCatch2()
        }
        delay(200)
        job1.cancel()
        println("End")
    }

    suspend fun func1(handler: Handler): Boolean = suspendCancellableCoroutine {
        handler.postDelayed({
            it.resume(true)
        }, 2000)
    }

    suspend fun func2(handler: Handler): Boolean = suspendCoroutine {
        handler.postDelayed({
            println("func2: 11")
            it.resume(true)
        }, 2000)
    }

    suspend fun testTryCatch1() {
        try {
            repeat(5) {
                delay(100)
                println("first-$it")
            }
        } catch (e: Exception) {

        }
        delay(200)
        repeat(5) {
            println("second-$it")
        }
    }

    suspend fun testTryCatch2() {
        repeat(5) {
            delay(100)
            println("first-$it")
        }
        delay(200)
        repeat(5) {
            println("second-$it")
        }
    }
}