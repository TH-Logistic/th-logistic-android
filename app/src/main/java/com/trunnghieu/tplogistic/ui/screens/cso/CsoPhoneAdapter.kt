package com.trunnghieu.tplogistic.ui.screens.cso

import androidx.recyclerview.widget.DiffUtil
import com.trunnghieu.tplogistic.R
import com.trunnghieu.tplogistic.data.repository.remote.account.cso.CsoPhoneNumber
import com.trunnghieu.tplogistic.databinding.ItemCsoPhoneBinding
import com.trunnghieu.tplogistic.ui.base.adapter.BaseBindingListAdapter
import com.trunnghieu.tplogistic.ui.base.adapter.BaseBindingViewHolder
import com.trunnghieu.tplogistic.ui.base.adapter.BaseItemClickListener

class CsoPhoneAdapter(listener: BaseItemClickListener<CsoPhoneNumber>) :
    BaseBindingListAdapter<CsoPhoneNumber>(DiffCallback(), listener) {

    private class DiffCallback : DiffUtil.ItemCallback<CsoPhoneNumber>() {
        override fun areItemsTheSame(oldItem: CsoPhoneNumber, newItem: CsoPhoneNumber): Boolean {
            return oldItem.phoneNo == newItem.phoneNo
        }

        override fun areContentsTheSame(oldItem: CsoPhoneNumber, newItem: CsoPhoneNumber): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_cso_phone

    override fun onBindViewHolder(holder: BaseBindingViewHolder<CsoPhoneNumber>, position: Int) {
        super.onBindViewHolder(holder, position)

        (holder.binding as ItemCsoPhoneBinding).firstItem = position == 0
    }
}
