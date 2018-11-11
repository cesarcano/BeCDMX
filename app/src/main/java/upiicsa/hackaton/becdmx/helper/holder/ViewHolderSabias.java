package upiicsa.hackaton.becdmx.helper.holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import upiicsa.hackaton.becdmx.R;
import upiicsa.hackaton.becdmx.model.ItemSabias;

public class ViewHolderSabias extends RecyclerView.ViewHolder {

    public static ViewHolderSabias viewHolderSabias(ViewGroup viewGroup) {
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final View view = inflater.inflate(R.layout.card_sabias, viewGroup, false);
        return new ViewHolderSabias(view);
    }

    ItemSabias itemSabias;
    private Context context;
    @BindView(R.id.imgView) ImageView imgView;
    @BindView(R.id.tvImageName)
    TextView tvImageName ;


    public ViewHolderSabias(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }
    public void setItem(ItemSabias item) {
        this.itemSabias = item;
        tvImageName.setText(item.getNombre());
        Glide.with(context)
                .load(item.getImg_url())
                .apply(new RequestOptions().override(700  , 400).centerCrop())
                .into(imgView);
    }
}
