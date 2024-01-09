package com.mindyapps.fairytales.feature.fairytales.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import com.mindyapps.fairytales.base.presentation.list.BaseRecycleViewAdapter
import com.mindyapps.fairytales.base.presentation.list.BaseViewHolder
import com.mindyapps.fairytales.databinding.ItemFairyTaleBinding
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData

class FairyTalesAdapter(private val selectItem: (item: FairyTaleViewData, extras: FragmentNavigator.Extras) -> Unit) :
    BaseRecycleViewAdapter<FairyTaleViewData>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<FairyTaleViewData> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPersonBinding = ItemFairyTaleBinding.inflate(layoutInflater, parent, false)
        return FairyTaleHolder(parent.context, itemPersonBinding, selectItem)
    }
}