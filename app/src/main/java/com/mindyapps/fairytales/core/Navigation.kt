package com.mindyapps.fairytales.core


import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigator

/**
 * Безопасный переход по навигации.
 * Предотвращает краш при открытии более чем одного экрана (нажатие на несколько кнопок одновременно)
 * или если с текущего экрана нельзя попасть на запрашиваемый.
 */
fun NavController.navigateSafe(direction: NavDirections, extras: Navigator.Extras?) {
    when {
        currentDestination?.getAction(direction.actionId) == null -> {
            Log.d("Unexpected action", "'${direction.actionId}' for destination '$currentDestination'")
        }

        extras == null -> navigate(direction)
        else -> navigate(direction, extras)
    }
}

/**
 * Добавление коллбэка для нажатия "назад".
 *
 * Коллбэк нужно добавлять в [Fragment.onAttach]. Коллбэки привязаны к жизненному циклу фрагмента,
 * поэтому при уничтожении фрагмента перестанут работать.
 * ```
 *  class FormEntryFragment : Fragment() {
 *      override fun onAttach(context: Context) {
 *          super.onAttach(context)
 *          addOnBackPressCallback {
 *              showAreYouSureDialog()
 *          }
 *      }
 *  }
 * ```
 * @param enabled Состояние коллбэка по умолчанию. Если не задано - `true`.
 * @return Созданный коллбэк, который можно отключить при помощи [OnBackPressedCallback.setEnabled] или
 * удалить с помощью [OnBackPressedCallback.remove].
 * @see OnBackPressedCallback
 * @see androidx.activity.OnBackPressedDispatcher
 */
fun Fragment.addOnBackPressedCallback(
    enabled: Boolean = true,
    onBackPressed: OnBackPressedCallback.() -> Unit,
): OnBackPressedCallback {
    return object : OnBackPressedCallback(enabled) {
        override fun handleOnBackPressed() = onBackPressed()
    }.also { requireActivity().onBackPressedDispatcher.addCallback(this, it) }
}