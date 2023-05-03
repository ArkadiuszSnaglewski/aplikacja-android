package pl.kalisz.ak.pup.apkaarkadiusz28879;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class Kierunki extends AppCompatActivity {

    private StrukturaUczelni strukturaUczelni = new StrukturaUczelni();
    private Spinner wydzial;
    private TextView kierunek;
    String kierunki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kierunki);
        wydzial = (Spinner) findViewById(R.id.spinner);
        kierunek = (TextView) findViewById (R.id.textView);
    }

    public void wyswKierunki (View view) {
        List<String> listaKierunkow = strukturaUczelni.getKierunki
                (String.valueOf(wydzial.getSelectedItem()));
        StringBuilder stringBuilder = new StringBuilder();
        for (String zm : listaKierunkow) {
            stringBuilder.append(zm).append('\n');
        }
        kierunek.setText(stringBuilder);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        List<String> listaKierunkow = strukturaUczelni.getKierunki
                (String.valueOf(wydzial.getSelectedItem()));
        StringBuilder stringBuilder = new StringBuilder();
        for (String zm : listaKierunkow) {
            stringBuilder.append(zm).append('\n');
        }
        kierunek.setText(stringBuilder);
        outState.putString("kierunki",  stringBuilder.toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        kierunki = savedInstanceState.getString("kierunki");
        kierunek.setText(kierunki);
    }
}
