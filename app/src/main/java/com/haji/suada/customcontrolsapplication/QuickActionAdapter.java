package com.haji.suada.customcontrolsapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haji.suada.customcontrolsapplication.model.QuickAction;

import java.util.ArrayList;
import java.util.List;

public class QuickActionAdapter extends RecyclerView.Adapter<QuickActionAdapter.ViewHolder> {
    private ArrayList<QuickAction> quickActions;
    private ItemSelectedListener listener;

    public QuickActionAdapter() {
        quickActions = new ArrayList<>();
    }

    public void setQuickActions(List<QuickAction> quickActions) {
        this.quickActions.clear();
        this.quickActions.addAll(quickActions);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ViewDataBinding binding;
        private final ItemSelectedListener listener;

        public ViewHolder(ViewDataBinding binding, ItemSelectedListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
            binding.getRoot().setOnClickListener(this);
        }

        void bind(QuickAction action) {
            binding.setVariable(BR.quickaction, action);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemSelected(v, quickActions.get(getAdapterPosition()));
                notifyDataSetChanged();
            }
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.quick_action_item, parent, false);
        return new ViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(quickActions.get(position));

    }

    @Override
    public int getItemCount() {
        return quickActions.size();
    }

    public interface ItemSelectedListener {
        void onItemSelected(View view, QuickAction quickAction);
    }

    public void setOnItemClickListener(ItemSelectedListener listener) {
        this.listener = listener;
    }


}
