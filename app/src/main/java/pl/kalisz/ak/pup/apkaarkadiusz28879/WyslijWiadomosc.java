package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WyslijWiadomosc extends AppCompatActivity {
    private String tekstWiad;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyslij_wiadomosc);
        editText = (EditText) findViewById(R.id.wiadomosc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void wyslij(View view) {
        tekstWiad = editText.getText().toString();
        Intent intent = new Intent(this, WyswietlWiadomosc.class);
        intent.putExtra(WyswietlWiadomosc.EXTRA_MESSAGE, tekstWiad);
        startActivity(intent);
    }

    public void wyslijMail(View view){
        tekstWiad = editText.getText().toString();
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, tekstWiad);
        intent2.putExtra(Intent.EXTRA_SUBJECT, "wiadomosc z aplikacji mobilnej");
        startActivity(intent2);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences ustawienia = getSharedPreferences("PREFS",0);
        SharedPreferences.Editor edytor = ustawienia.edit();
        edytor.putString("wartosc",editText.getText().toString());
        edytor.commit();
    }

    @Override
    protected void onStart(){
        super.onStart();

        SharedPreferences ustawienia = getSharedPreferences("PREFS",0);
        editText.setText(ustawienia.getString("wartosc",""));
    }
}