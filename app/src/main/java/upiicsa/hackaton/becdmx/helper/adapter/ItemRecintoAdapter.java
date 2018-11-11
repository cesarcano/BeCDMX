package upiicsa.hackaton.becdmx.helper.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import upiicsa.hackaton.becdmx.helper.holder.ViewHolderItemRecinto;
import upiicsa.hackaton.becdmx.model.ItemRecinto;

public class ItemRecintoAdapter extends RecyclerView.Adapter<ViewHolderItemRecinto>{

    ArrayList<ItemRecinto> recintos = new ArrayList<>();

    public void setList(ArrayList<ItemRecinto> recintos) {
        this.recintos = recintos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderItemRecinto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolderItemRecinto.viewHolderItemRecinto(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemRecinto holder, int position) {
        holder.setItem(recintos == null? null: recintos.get(position));
    }

    @Override
    public int getItemCount() {
        return recintos == null? 0: recintos.size();
    }

    public void clear() {
        final int size = recintos.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                recintos.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }
}
