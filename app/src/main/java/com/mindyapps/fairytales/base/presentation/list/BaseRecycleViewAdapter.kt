package com.mindyapps.fairytales.base.presentation.list


import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleViewAdapter<DATA : Any>(
    val _items: MutableList<DATA>,
    val _selectItem: (item: DATA, position: Int) -> Unit,
    val _longSelectItem: (item: DATA, position: Int) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<DATA>>() {

    constructor() : this(mutableListOf<DATA>(), Companion::itemCLick, Companion::longCLick)
    constructor(_items: MutableList<DATA>, selectItem: (item: DATA, position: Int) -> Unit) : this(
        _items,
        selectItem,
        Companion::longCLick
    )

    constructor(selectItem: (item: DATA, position: Int) -> Unit) : this(
        mutableListOf<DATA>(),
        selectItem,
        Companion::longCLick
    )

    constructor(_items: MutableList<DATA>) : this(_items, Companion::itemCLick, Companion::longCLick)

    override fun onBindViewHolder(holder: BaseViewHolder<DATA>, position: Int) {
        holder.bindData(_items[position])
    }


    open fun addData(newItems: MutableList<DATA>, useDiffUtil: Boolean = false) {
        _items.clear()
        _items.addAll(newItems)
        if (!useDiffUtil) {
            notifyDataSetChanged()
        }
    }

    open fun getItem(position: Int): DATA {
        return _items[position]
    }

    open fun getItems(): MutableList<DATA> {
        return _items
    }


    override fun getItemCount(): Int = _items.size


    companion object {
        open fun longCLick(item: Any, position: Int) {

        }

        open fun itemCLick(item: Any, position: Int) {

        }
    }
}