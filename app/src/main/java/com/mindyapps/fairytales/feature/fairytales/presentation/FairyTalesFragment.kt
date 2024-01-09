package com.mindyapps.fairytales.feature.fairytales.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mindyapps.fairytales.R
import com.mindyapps.fairytales.base.presentation.BaseFragment
import com.mindyapps.fairytales.core.observe
import com.mindyapps.fairytales.databinding.FragmentFairyTalesBinding
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData
import com.mindyapps.fairytales.feature.fairytales.presentation.list.FairyTalesAdapter

class FairyTalesFragment : BaseFragment(R.layout.fragment_fairy_tales) {

    private val view: FragmentFairyTalesBinding by viewBinding()

    private val viewModel: FairyTalesViewModel by viewModels()

    private var adapter: FairyTalesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.events, ::onEvent)
        observe(viewModel.data, ::addDataInList)
        createAdapter()
        renderList()
        viewModel.onViewCreated()
    }

    private fun renderList() = with(view) {
        val decorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        AppCompatResources.getDrawable(requireContext(), R.drawable.item_decoration_vertical)?.let {
            decorator.setDrawable(it)
            list.addItemDecoration(decorator)
        }
    }

    private fun createAdapter() {
        adapter = FairyTalesAdapter(::renderOnClick)
        setAdapterList()
    }

    private fun addDataInList(data: List<FairyTaleViewData>) {
        adapter?.addData(data.toMutableList())
    }

    private fun setAdapterList() {
        view.list.adapter = adapter
    }

    private fun renderOnClick(item: FairyTaleViewData, extras: FragmentNavigator.Extras) {
        viewModel.onItemSelect(item, extras)
    }

}