package com.mindyapps.fairytales.core


import androidx.navigation.NavDirections
import androidx.navigation.Navigator

data class MessageEvent(val message: String) : Event
data class ErrorMessageEvent(val message: String) : Event

sealed class NavigationEvent : Event {

    abstract val rootGraph: Boolean

    data class Up(override val rootGraph: Boolean) : NavigationEvent()

    data class ToDirection(
        val direction: NavDirections,
        val extras: Navigator.Extras?,
        override val rootGraph: Boolean,
    ) : NavigationEvent()
}