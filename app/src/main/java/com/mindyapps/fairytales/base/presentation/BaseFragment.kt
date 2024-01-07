package com.mindyapps.fairytales.base.presentation


import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mindyapps.fairytales.R
import dagger.android.support.DaggerFragment
import com.mindyapps.fairytales.app.di.InjectingSavedStateViewModelFactory
import com.mindyapps.fairytales.core.Event
import com.mindyapps.fairytales.core.NavigationEvent
import com.mindyapps.fairytales.core.navigateSafe
import com.mindyapps.fairytales.extension.hideKeyboard
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutId: Int) : DaggerFragment(layoutId) {

    @Inject
    lateinit var abstractFactory: dagger.Lazy<InjectingSavedStateViewModelFactory>


    override fun onPause() {
        activity?.hideKeyboard()
        super.onPause()
    }


    /** Функция для обработки событий, которые прилетают из ViewModel. */
    @CallSuper
    protected open fun onEvent(event: Event) {
        when (event) {
            is NavigationEvent -> handleNavigationEvent(event)
        }
    }


    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = abstractFactory.get().create(this, arguments)

    protected open fun handleNavigationEvent(event: NavigationEvent) {
        val controller = if (event.rootGraph) getRootNavController() else getNavController()
        when (event) {
            is NavigationEvent.ToDirection -> controller.navigateSafe(event.direction, extras = event.extras)
            is NavigationEvent.Up -> if (!controller.navigateUp()) getRootNavController().navigateUp()
        }
    }

    private fun getRootNavController(): NavController = requireActivity().findNavController(R.id.nav_host_fragment)

    protected open fun getNavController(): NavController = findNavController()

}