package com.mindyapps.fairytales.feature.fairytales.presentation.list

import android.content.Context
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.mindyapps.fairytales.base.presentation.list.BaseViewHolder
import com.mindyapps.fairytales.databinding.ItemFairyTaleBinding
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData

class FairyTaleHolder(
    override val context: Context,
    private val binding: ItemFairyTaleBinding,
    selectItemTransaction: (item: FairyTaleViewData, transitions: FragmentNavigator.Extras) -> Unit
) : BaseViewHolder<FairyTaleViewData>(binding, selectItemTransaction) {

    override fun bindData(item: FairyTaleViewData, transaction: FragmentNavigator.Extras?) =
        with(binding) {
            ViewCompat.setTransitionName(tvTitle, "title_${item.id}")
            ViewCompat.setTransitionName(tvDescription, "description_${item.id}")


            val extras = FragmentNavigatorExtras(
                tvTitle to "title_${item.id}",
                tvDescription to "description_${item.id}"
            )
            super.bindData(item, extras)

            tvTitle.text = item.title
            tvDescription.text = item.description
        }

}