package com.mindyapps.fairytales.feature.fairytales.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mindyapps.fairytales.base.presentation.list.BaseRecycleViewAdapter
import com.mindyapps.fairytales.base.presentation.list.BaseViewHolder
import com.mindyapps.fairytales.databinding.ItemFairyTaleBinding
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTalesViewData

class FairyTalesAdapter(private val selectItem: (item: FairyTalesViewData, position: Int) -> Unit) :
    BaseRecycleViewAdapter<FairyTalesViewData>(selectItem) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<FairyTalesViewData> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPersonBinding = ItemFairyTaleBinding.inflate(layoutInflater, parent, false)
        return FairyTaleHolder(parent.context, itemPersonBinding, selectItem)
    }
}