package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WyswietlWiadomosc extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietl_wiadomosc);

        TextView wyswietl = (TextView) findViewById(R.id.wiadomosc2);
        Intent intent = getIntent();
        String tekst = intent.getStringExtra(EXTRA_MESSAGE);
        wyswietl.setTextSize(30);
        wyswietl.setText(tekst);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}