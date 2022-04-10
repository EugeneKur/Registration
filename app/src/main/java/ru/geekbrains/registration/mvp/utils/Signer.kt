package ru.geekbrains.registration.mvp.utils

private typealias SubscriberCustom<T> = (T?) -> Unit

class Signer<T> {
    private val subscribers: MutableSet<SubscriberCustom<T>> = mutableSetOf()
    private var value: T? = null
    private var hasFirstValue = false

    fun subscribe(subscriber: SubscriberCustom<T>) {
        subscribers.add(subscriber)
        if (hasFirstValue) {
            subscriber.invoke(value)
        }
    }

    fun unsubscribe(subscriber: SubscriberCustom<T>) {
        subscribers.remove(subscriber)
    }

    fun unsubscribeAll() {
        subscribers.clear()
    }

    fun post(value: T) {
        hasFirstValue = true
        this.value = value
        subscribers.forEach { it.invoke(value) }
    }

}