package upiicsa.hackaton.becdmx.helper.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import upiicsa.hackaton.becdmx.helper.holder.ViewHolderSabias;
import upiicsa.hackaton.becdmx.model.ItemSabias;

public class ItemSabiasAdapter extends RecyclerView.Adapter<ViewHolderSabias> {

        ArrayList<ItemSabias> recintos = new ArrayList<>();

    public void setList(ArrayList<ItemSabias> recintos) {
        this.recintos = recintos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderSabias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolderSabias.viewHolderSabias(parent);
    }

@Override
public void onBindViewHolder(@NonNull ViewHolderSabias holder, int position) {
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
