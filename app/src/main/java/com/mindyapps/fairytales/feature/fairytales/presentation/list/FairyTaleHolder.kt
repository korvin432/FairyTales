package com.mindyapps.fairytales.feature.fairytales.presentation.list

import android.content.Context
import com.mindyapps.fairytales.base.presentation.list.BaseViewHolder
import com.mindyapps.fairytales.databinding.ItemFairyTaleBinding
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTalesViewData

class FairyTaleHolder(
    override val context: Context,
    private val binding: ItemFairyTaleBinding,
    selectItem: (item: FairyTalesViewData, position: Int) -> Unit
) : BaseViewHolder<FairyTalesViewData>(binding, selectItem) {

    override fun bindData(item: FairyTalesViewData) = with(binding) {
        super.bindData(item)
        tvTitle.text = item.title
        tvDescription.text = item.description
    }

}