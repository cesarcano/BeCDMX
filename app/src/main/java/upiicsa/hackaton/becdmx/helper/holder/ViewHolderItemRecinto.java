package upiicsa.hackaton.becdmx.helper.holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upiicsa.hackaton.becdmx.R;
import upiicsa.hackaton.becdmx.model.ItemRecinto;

public class ViewHolderItemRecinto extends RecyclerView.ViewHolder {

    public static ViewHolderItemRecinto viewHolderItemRecinto(ViewGroup viewGroup) {
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final View view = inflater.inflate(R.layout.card_recinto, viewGroup, false);
        return new ViewHolderItemRecinto(view);
    }

    ItemRecinto itemRecinto;
    private Context context;
    CardView cardView;
    @BindView(R.id.tv_nombre) TextView tv_nombre;
    @BindView(R.id.tv_direccion) TextView tv_direccion ;
    @BindView(R.id.tv_distancia) TextView tv_distancia ;
    @BindView(R.id.tv_llegar) TextView tv_llegar ;


    public ViewHolderItemRecinto(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        context = itemView.getContext();

        tv_llegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri uri;
                final double lat = itemRecinto.getLat();
                final double lon = itemRecinto.getLng();

                if(lat != 0 || lon != 0 ) {
                    uri = Uri.parse("geo:0,0?q=" + lat + "," + lon);
                } else {
                    uri = Uri.parse("geo:0,0?q=" + itemRecinto.getDireccion());
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                itemView.getContext().startActivity(intent);
            }
        });
    }
    public void setItem(ItemRecinto item) {
        this.itemRecinto = item;
        String d = "";
        tv_nombre.setText(item.getNombre());
        tv_direccion.setText(item.getDireccion());
        float ds =  ((int) item.getDistancia() * 100) / 100 ;
        if (item.getDistancia() > 1000) {
            d = Float.toString(ds / 1000) + " km";
        } else {
            d = Float.toString(ds / 1000) + " m";
        }

        tv_distancia.setText(d);
    }

}
