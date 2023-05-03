package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ObiektyUczelni extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obiekty_uczelni);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        //layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        String[] nazwa_ = new String[Obiekt.obiekty.length];
        for(int i=0; i<nazwa_.length; i++){
            nazwa_[i] = Obiekt.obiekty[i].getNazwa();
        }

        String[] opis_ = new String[Obiekt.obiekty.length];
        for(int i=0; i<opis_.length; i++){
            opis_[i] = Obiekt.obiekty[i].getOpis();
        }

        int[] fotoId_ = new int[Obiekt.obiekty.length];
        for(int i=0; i<fotoId_.length; i++){
            fotoId_[i] = Obiekt.obiekty[i].getFotoId();
        }

        adapter = new RVAdapter(nazwa_, opis_, fotoId_);
        rv.setAdapter(adapter);
    }
}