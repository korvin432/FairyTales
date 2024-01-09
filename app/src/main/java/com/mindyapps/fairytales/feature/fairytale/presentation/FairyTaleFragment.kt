package com.mindyapps.fairytales.feature.fairytale.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mindyapps.fairytales.R
import com.mindyapps.fairytales.base.presentation.BaseFragment
import com.mindyapps.fairytales.core.observe
import com.mindyapps.fairytales.databinding.FragmentFairyTaleBinding
import com.mindyapps.fairytales.extension.parcelable
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData

class FairyTaleFragment : BaseFragment(R.layout.fragment_fairy_tale) {

    private val view: FragmentFairyTaleBinding by viewBinding()

    private val viewModel: FairyTaleViewModel by viewModels()

    private var fairyTaleViewData: FairyTaleViewData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        observe(viewModel.events, ::onEvent)
        fairyTaleViewData = arguments?.parcelable("fairyTale")
        fairyTaleViewData?.let { fairyTale ->
            ViewCompat.setTransitionName(view.tvTitle, "title_${fairyTale.id}")
            ViewCompat.setTransitionName(view.tvDescription, "description_${fairyTale.id}")

            view.tvTitle.text = fairyTale.title
            view.tvDescription.text = fairyTale.description
        }
    }


}