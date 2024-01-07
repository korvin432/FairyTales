package com.mindyapps.fairytales.core


import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.reflect.KProperty

/**
 * Returns value from [LiveData] or throws [IllegalStateException].
 * @see LiveData.getValue
 */
@Suppress("NOTHING_TO_INLINE")
public inline fun <T : Any> LiveData<T>.requireValue(): T = checkNotNull(value)

/**
 * Shorter way to observe [LiveData] changes in a fragment using view lifecycle owner.
 * @see LiveData.observe
 */
public inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline onChanged: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner) { onChanged(it) }
}

/**
 * Shorter way to observe [LiveData] changes in an activity.
 * @see LiveData.observe
 */
public inline fun <T> ComponentActivity.observe(liveData: LiveData<T>, crossinline onChanged: (T) -> Unit) {
    liveData.observe(this) { onChanged(it) }
}

/**
 * Returns the value from this [LiveData].
 * @throws IllegalStateException when the `LiveData` doesn't contain value ot it is `null`.
 * @see requireValue
 */
@Suppress("NOTHING_TO_INLINE")
public inline operator fun <T : Any> LiveData<T>.getValue(thisRef: Any?, property: KProperty<*>): T = requireValue()

/**
 * Stores the value in this [LiveData].
 * @see LiveData.setValue
 */
@Suppress("NOTHING_TO_INLINE")
public inline operator fun <T : Any> MutableLiveData<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    this.value = value
}