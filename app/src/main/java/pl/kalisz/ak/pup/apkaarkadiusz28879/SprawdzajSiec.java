package pl.kalisz.ak.pup.apkaarkadiusz28879;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SprawdzajSiec extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStart, buttonStop;
    private Toast myToast;
    private final BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                String wartosc = bundle.getString(Usluga.MSG_FIELD);
                myToast = Toast.makeText(getApplicationContext(), wartosc, Toast.LENGTH_SHORT);
                myToast.show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprawdzaj_siec);
        //Toolbar toolbar = findViewById(R.id.toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonStart:
                Intent intent = new Intent(this, Usluga.class);
                intent.putExtra(Usluga.EXTRA_MESSAGE, "Akademia Kaliska OK!");
                startService(intent);
                break;
            case R.id.buttonStop:
                Intent intent2 = new Intent(this, Usluga.class);
                stopService(intent2);
                break;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        registerReceiver(myReceiver, new IntentFilter(Usluga.NEW_MESSAGE));
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(myReceiver);
    }
}