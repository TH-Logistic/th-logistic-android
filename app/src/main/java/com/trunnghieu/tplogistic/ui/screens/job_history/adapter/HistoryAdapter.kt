package com.trunnghieu.tplogistic.ui.screens.job_history.adapter

import androidx.recyclerview.widget.DiffUtil
import com.trunnghieu.tplogistic.R
import com.trunnghieu.tplogistic.data.repository.remote.delivery_workflow_service.Job
import com.trunnghieu.tplogistic.databinding.ItemHistoryBinding
import com.trunnghieu.tplogistic.ui.base.adapter.BaseBindingListAdapter
import com.trunnghieu.tplogistic.ui.base.adapter.BaseBindingViewHolder
import com.trunnghieu.tplogistic.ui.base.adapter.BaseItemClickListener

class HistoryAdapter(private val listener: BaseItemClickListener<Job>) :
    BaseBindingListAdapter<Job>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem.bookingNo == newItem.bookingNo
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_history

    override fun onBindViewHolder(holder: BaseBindingViewHolder<Job>, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = getItem(position)

        (holder.binding as ItemHistoryBinding).run {
            setShowDetailOnClick {
                item.showDetail = !(item.showDetail ?: false)
                notifyItemChanged(position)
            }
            setDownloadEdoOnClick {
                listener.onItemClick(item)
            }
        }
    }
}
