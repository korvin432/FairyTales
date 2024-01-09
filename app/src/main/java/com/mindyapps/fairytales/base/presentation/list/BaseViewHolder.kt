package com.mindyapps.fairytales.base.presentation.list


import android.content.Context
import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

abstract class BaseViewHolder<DATA : Any>(
    private val _view: ViewBinding,
    private val _selectItem: (item: DATA, position: Int) -> Unit,
    private val _longSelectItem: (item: DATA, position: Int) -> Unit,
    private val _selectItemTransaction: (item: DATA, transitions: FragmentNavigator.Extras) -> Unit
) : RecyclerView.ViewHolder(_view.root) {

    abstract val context: Context
//
//    constructor(view: ViewBinding, selectItem: (item: DATA, position: Int) -> Unit) : this(
//        view,
//        selectItem,
//        Companion::longCLick,
//        Companion::itemCLickTransaction
//    )

    constructor(
        view: ViewBinding,
        selectItemTransaction: (item: DATA, transaction: FragmentNavigator.Extras) -> Unit
    ) : this(
        view,
        Companion::itemCLick,
        Companion::longCLick,
        selectItemTransaction
    )

    constructor(view: ViewBinding) : this(
        view, Companion::itemCLick, Companion::longCLick,
        Companion::itemCLickTransaction
    )

    open fun bindData(item: DATA, transaction: FragmentNavigator.Extras? = null) {
        if (transaction != null){
            _view.root.setOnClickListener {
                _selectItemTransaction.invoke(item, transaction)
            }
        } else {
            _view.root.setOnClickListener {
                _selectItem.invoke(item, adapterPosition)
            }
        }

        _view.root.setOnLongClickListener {
            _longSelectItem.invoke(item, adapterPosition)
            return@setOnLongClickListener true
        }
    }

    open fun loadImage(linkImage: Any, view: ImageView) {
        val requestOptions = RequestOptions().apply {
            // centerCrop()
        }
        Glide.with(context).load(linkImage).apply(requestOptions).into(view)
    }

    companion object {
        fun longCLick(item: Any, position: Int) {

        }

        fun itemCLick(item: Any, position: Int) {

        }

        fun itemCLickTransaction(item: Any, transaction: FragmentNavigator.Extras) {

        }
    }
}