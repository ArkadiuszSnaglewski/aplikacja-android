package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ObiektyInfo extends AppCompatActivity {
    public static final String EXTRA_OBIEKT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obiekty_info);
        ObiektyInfoFragment frag = (ObiektyInfoFragment) getSupportFragmentManager().findFragmentById(R.id.info_frag);
//        frag.setObiekt(1);
//        setContentView(R.layout.activity_obiekty_info);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//
//        super.onCreate(savedInstanceState);
//

        int obiektId = (int) getIntent().getExtras().get(EXTRA_OBIEKT_ID);
        frag.setObiekt(obiektId);
    }
}
