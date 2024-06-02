package com.school21.handlerlooper

import android.os.Looper
import android.util.Log
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

val TAG = "SCHOOL_21: "

//Каждое сообщение закреплено за хендлером
class CustomLooper(val thread: LooperThread) {

    private val messageQueue: BlockingQueue<Message> = LinkedBlockingQueue()

    fun loop() {
        while (true) {
            try {
                if (!thread.isInterrupted) {
                    synchronized(lock = this) {
                        Log.d(TAG, "Запустили поток")
                        (this as? Object)?.wait(1000L)
                        val msg = messageQueue.take()
                        msg.runnable.run()
                        msg.handler.handleMessage(msg)
                    }
                }
            } catch (e: InterruptedException) {
                messageQueue.clear()
                thread.interrupt()
            }
        }
    }

    fun addMessage(message: Message) {
        messageQueue.put(message)
    }

    fun quit() {
        thread.interrupt()
    }
}

class Message(val handler: CustomHandler, val description: String, val runnable: Runnable)

class CustomHandler(val looper: CustomLooper) {
    //Вызывается, когда поток обработал наше сообщение
    fun handleMessage(msg: Message) {
        Log.d(TAG, "Обработали сообщение ${msg.description}")
    }

    fun post(message: Message) {
        looper.addMessage(message)
    }
}

class LooperThread : Thread("LooperThread") {

    val looper = CustomLooper(this)

    override fun run() {
        looper.loop()
    }
}