package com.sample.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 分页加载库
 */
public class ContactAdapter extends PagedListAdapter<ContactBean, ContactAdapter.ContactViewHolder> {

    public static final DiffUtil.ItemCallback<ContactBean> diffCallback = new ContactDiff();

    public ContactAdapter() {
        super(diffCallback);
    }

//    public ContactAdapter(@NonNull AsyncDifferConfig<ContactBean> config) {
//        super(config);
//    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.contact_content);
        }
    }

    static class ContactDiff extends DiffUtil.ItemCallback<ContactBean>{
        @Override
        public boolean areItemsTheSame(@NonNull ContactBean oldItem, @NonNull ContactBean newItem) {
            return oldItem.getContent().equals(newItem.getContent());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ContactBean oldItem, @NonNull ContactBean newItem) {
            return oldItem.equals(newItem);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactBean contactBean = getItem(position);
        holder.textView.setText(contactBean.getContent());
    }
}