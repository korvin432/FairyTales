package com.mindyapps.fairytales.base.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindyapps.fairytales.core.EventQueue
import com.mindyapps.fairytales.core.EventsDispatcher
import com.mindyapps.fairytales.core.getValue
import com.mindyapps.fairytales.core.setValue

abstract class BaseViewModel<T : ViewState>(initialState: T? = null) : ViewModel(),
    EventsDispatcher {


    protected val liveState = MutableLiveData<T>().apply {
        initialState?.let(::setValue)
    }

    protected var state: T by liveState

    override val events = EventQueue()

}