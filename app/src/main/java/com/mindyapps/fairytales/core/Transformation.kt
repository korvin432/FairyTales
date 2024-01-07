package com.mindyapps.fairytales.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map

inline fun <X, Y> LiveData<X>.mapDistinct(noinline transform: (X) -> Y): LiveData<Y> {
    return map(transform).distinctUntilChanged()
}