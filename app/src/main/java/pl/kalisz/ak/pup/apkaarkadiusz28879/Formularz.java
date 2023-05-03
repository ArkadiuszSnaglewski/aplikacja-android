package pl.kalisz.ak.pup.apkaarkadiusz28879;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class Formularz extends AppCompatActivity {
    private CharSequence komunikat="";
    private Button accept;
    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;
    private StrukturaUczelni strukturaUczelni = new StrukturaUczelni();
    ArrayAdapter<String> arrayAdapter;
    List <String> listaKierunkow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accept = findViewById(R.id.from_button);
        setContentView(R.layout.activity_formularz);
        spinner = (Spinner) findViewById(R.id.spinner);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Formularz.this,
                        android.R.layout.simple_dropdown_item_1line,
                        listaKierunkow = strukturaUczelni.getKierunki(String.valueOf(spinner.getSelectedItem())));
                autoCompleteTextView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }
        });
    }


    public void akceptujDane(View view) {
        komunikat="";
        TextView name = (TextView) findViewById(R.id.name);
        RadioButton radio = (RadioButton)findViewById(R.id.radioButton);
        RadioButton radio2 = (RadioButton)findViewById(R.id.radioButton2);
        CheckBox checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        DatePicker datePicker= (DatePicker)findViewById(R.id.calendar);
        TextView email = (TextView) findViewById(R.id.email);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);


        komunikat="Nick "+name.getText()+" jest \n";
        if(radio.isChecked())
            komunikat=komunikat+"kobietą, która rozpoczeła studia";
        else
            komunikat=komunikat+"meżczyzną, który rozpoczął studia";
        komunikat=komunikat+"\n"+datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear();
        komunikat=komunikat+"\nAdres email "+email.getText();
        if(toggleButton.isChecked())
            komunikat=komunikat+"\nAdres email aktywny\n";
        else
            komunikat=komunikat+"\nAdres email nieaktywny\n";
        komunikat=komunikat+"Wydział "+(String)spinner.getSelectedItem();
        komunikat=komunikat+"\nkierunek "+ autoCompleteTextView.getText();
        komunikat=komunikat+"\ndodatkowe informacje:";
        if(checkBox1.isChecked())
            komunikat=komunikat+"\ninformacje z akademika";
        if(checkBox2.isChecked())
            komunikat=komunikat+"\nwydarzenia uczelniane";
        Toast.makeText(Formularz.this, komunikat, Toast.LENGTH_LONG).show();

        int maksymalna_dlugosc_wiadomosci = 70;
        String numer_Tel = "28879";
        SmsManager manager_sms = SmsManager.getDefault();
//        manager_sms.sendTextMessage(numer_Tel,null,komunikat.toString(),null,null);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",numer_Tel, null));
        intent.putExtra("sms_body",komunikat.toString());
        startActivity(intent);
        int smsLenght = komunikat.length();
        SmsManager manager = SmsManager.getDefault();
        if(smsLenght > maksymalna_dlugosc_wiadomosci){
            ArrayList<String> smsLista = manager.divideMessage(komunikat.toString());
            manager.sendMultipartTextMessage(numer_Tel, null,smsLista,null,null);
        } else {
            manager.sendTextMessage(numer_Tel,null,komunikat.toString(),null, null);
        }

    }
}