package upiicsa.hackaton.becdmx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import upiicsa.hackaton.becdmx.helper.BaseActivity;
import upiicsa.hackaton.becdmx.helper.adapter.ItemRecintoAdapter;
import upiicsa.hackaton.becdmx.model.Geopunto;
import upiicsa.hackaton.becdmx.model.ItemRecinto;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DatabaseReference mDataBase;
    private ItemRecintoAdapter recintoAdapter;
    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_buscar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_sabiasque:

            break;
            case R.id.nav_festivales:
            setListaRecintos("Antropología", "museo");
            break;
            case R.id.nav_ferias:
                setListaRecintos("Arte", "museo");
            break;
            case R.id.nav_festividades:
                setListaRecintos("Arqueología", "museo");
            break;
            case R.id.nav_museos:
            setListaRecintos("Ciencia y tecnología", "museo");
            break;
            case R.id.nav_auditorios:
            setListaRecintos("Especializado", "museo");
            break;
            case R.id.nav_bibliotecas:
            setListaRecintos("Historia", "museo");
            break;
            case R.id.nav_centros:
            setListaRecintos("TND", "museo");
            break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void setListaRecintos(final String tematica, final String nombre) {
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child("/museos/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<ItemRecinto> recintos = new ArrayList<>();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Geopunto geopunto1 = new Geopunto(Double.parseDouble(snapshot.child("gmaps_latitud").getValue().toString())
                            , Double.parseDouble(snapshot.child("gmaps_longitud").getValue().toString()));
                    double distancia = geopunto1.getDistancia(new Geopunto(19.3961407, -99.0913228));
                    if (distancia < 10000 && snapshot.child("museo_tematica_n1").getValue().toString().equals(tematica)) {
                        Log.e("", "onDataChange: " + Double.toString(distancia));
                        ItemRecinto recinto = new ItemRecinto(snapshot.getKey(),
                                snapshot.child( nombre +"_nombre").getValue().toString(),
                                snapshot.child(nombre + "_calle_numero").getValue().toString(),
                                Double.parseDouble(snapshot.child("gmaps_latitud").getValue().toString()),
                                Double.parseDouble(snapshot.child("gmaps_longitud").getValue().toString()),
                                distancia);
                        Log.e("", "onDataChange: "+ recinto.toString());
                        recintos.add(recinto);
                    }
                }
                Collections.sort(recintos, new Comparator<ItemRecinto>() {
                    @Override
                    public int compare(ItemRecinto c1, ItemRecinto c2) {
                        return Double.compare(c1.getDistancia(), c2.getDistancia());
                    }
                });

                if (recintoAdapter == null) {
                    recintoAdapter = new ItemRecintoAdapter();
                }
                recintoAdapter.setList(recintos);
                recyclerView.setAdapter(recintoAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
