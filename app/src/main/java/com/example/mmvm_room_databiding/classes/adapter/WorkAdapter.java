package com.example.mmvm_room_databiding.classes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mmvm_room_databiding.R;
import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;
import com.example.mmvm_room_databiding.databinding.ItemWorkBinding;

import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewholder> {

    private List<WorkEntity> lstWork;

    private LayoutInflater layoutInflater;

    private LifecycleOwner lifecycleOwner;

    public WorkAdapter(LifecycleOwner lifecycleOwner) {
        lstWork = new ArrayList<>();
        this.lifecycleOwner = lifecycleOwner;
    }

    public void updateListWork(List<WorkEntity> workEntityList) {
        if (lstWork != null || lstWork.size() > 0) {
            lstWork.clear();
        }

        lstWork.addAll(workEntityList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemWorkBinding itemWorkBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_work, parent, false);
        return new WorkViewholder(itemWorkBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewholder holder, int position) {
        holder.bind(lstWork.get(position));
    }

    @Override
    public int getItemCount() {
        if (lstWork != null && lstWork.size() > 0) {
            return lstWork.size();
        }
        return 0;

        //return lstWork!=null && lstWork.size()>0 ? lstWork.size():0;
    }

    class WorkViewholder extends RecyclerView.ViewHolder {
        ItemWorkBinding itemWorkBinding;

        public WorkViewholder(@NonNull ItemWorkBinding itemView) {
            super(itemView.getRoot());
            itemWorkBinding = itemView;
        }

        void bind(WorkEntity workEntity) {
            itemWorkBinding.setWork(workEntity);
            itemWorkBinding.setLifecycleOwner(lifecycleOwner);
        }
    }
}
