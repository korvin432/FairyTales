package com.mindyapps.fairytales.core


import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import com.mindyapps.fairytales.core.ErrorMessageEvent
import com.mindyapps.fairytales.core.EventQueue
import com.mindyapps.fairytales.core.MessageEvent
import com.mindyapps.fairytales.core.NavigationEvent

interface EventsDispatcher {

    val events: EventQueue

    fun showMessage(message: String) {
        events.offerEvent(MessageEvent(message))
    }

    fun showError(message: String) {
        events.offerEvent(ErrorMessageEvent(message))
    }

    fun navigateTo(direction: NavDirections, extras: Navigator.Extras? = null, rootGraph: Boolean = false) {
        events.offerEvent(NavigationEvent.ToDirection(direction, extras, rootGraph))
    }

    fun navigateUp(rootGraph: Boolean = false) {
        events.offerEvent(NavigationEvent.Up(rootGraph))
    }
}