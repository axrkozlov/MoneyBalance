package com.axfex.moneybalance

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.concurrent.CompletableFuture
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

//fun main() {
//    GlobalScope.launch { // launch a new coroutine in background and continue
//        delay(2000L)
//        println("World!")
//    }
//    println("Hello,") // main thread continues here immediately
//    runBlocking {     // but this expression blocks the main thread
//
//        delay(6000L)  // ... while we delay for 2 seconds to keep JVM alive
//
//    }
//    println("mad")
//}
//
//fun main() = runBlocking<Unit> { // start main coroutine
//    GlobalScope.launch { // launch a new coroutine in background and continue
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,") // main coroutine continues here immediately
//    delay(2000L)      // delaying for 2 seconds to keep JVM alive
//}

//fun main() = runBlocking {
//    //sampleStart
//    val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
////    job.join() // wait until child coroutine completes
////sampleEnd
//}

//fun main() = runBlocking<Unit> {
//    val chan = Channel<Int>()
//    launch {
//        print(coroutineContext)
//        repeat(12) { i ->
//            delay(1000)
//            chan.send(i)
//            if (i>5) chan.cancel()
//        }
//        chan.close()
//    }
//    launch {
//        for (i in chan) {
//            println(i)
//        }
//    }
//}

//
//runBlocking { // this: CoroutineScope
//
//        val jobs=List(100_000){
//            thread {
//                Thread.sleep(1000L)
//                print(".")
//            }
//        }
//
//        jobs.forEach{it.join()}
//
//
//}

//fun main() = runBlocking { // this: CoroutineScope
//    launch() {
//        delay(5000L)
//        println("Task from runBlocking $coroutineContext")
//    }
//
//    runBlocking { // Creates a coroutine scope
//        launch {
//            delay(3000L)
//            println("Task from nested launch")
//        }
//
//        delay(2000L)
//        println("Task from coroutine scope") // This line will be printed before the nested launch
//    }
//
//    println("Coroutine scope is over") // This line is not printed until the nested launch completes
//    println("Task from runBlocking $coroutineContext")
//}
//fun main() = runBlocking { // this: CoroutineScope
//    launch {
//        delay(200L)
//        println("Task from runBlocking")
//    }
//
//    coroutineScope { // Creates a coroutine scope
//        launch {
//            delay(500L)
//            println("Task from nested launch")
//        }
//
//        delay(100L)
//        println("Task from coroutine scope $coroutineContext.") // This line will be printed before the nested launch
//    }
//
//    println("Coroutine scope is over") // This line is not printed until the nested launch completes
//}

