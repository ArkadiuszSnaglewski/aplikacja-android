package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ObiektyLista extends AppCompatActivity implements ObiektyListaFragment.Listener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obiekty_lista);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if(fragmentContainer != null)
        {
            ObiektyInfoFragment info = new ObiektyInfoFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            info.setObiekt(id);
            ft.replace(R.id.fragment_container, info);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, ObiektyInfo.class);
            intent.putExtra(ObiektyInfo.EXTRA_OBIEKT_ID, (int) id);
            startActivity(intent);
        }
    }
}
