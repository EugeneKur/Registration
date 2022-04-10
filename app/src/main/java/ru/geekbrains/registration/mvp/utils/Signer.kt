package ru.geekbrains.registration.mvp.utils

import android.os.Handler

private class SubscriberCustom<T>(
    private val handler: Handler,
    private val callback: (T?) -> Unit
) {
    fun invoke(value: T?) {
        handler.post {
            callback.invoke(value)
        }
    }
}

class Signer<T>(private val isSingle: Boolean = false) {

    private val subscribers: MutableSet<SubscriberCustom<T?>> = mutableSetOf()
    public var value: T? = null
        private set
    private var hasFirstValue = false

    fun subscribe(handler: Handler, callback: (T?) -> Unit) {
        val subscriber = SubscriberCustom(handler, callback)
        subscribers.add(subscriber)
        if (hasFirstValue) {
            subscriber.invoke(value)
        }
    }

    fun unsubscribeAll() {
        subscribers.clear()
    }

    fun post(value: T) {
        if (!isSingle) {
            hasFirstValue = true
            this.value = value

        }
        subscribers.forEach { it.invoke(value) }
    }

}