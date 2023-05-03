package pl.kalisz.ak.pup.apkaarkadiusz28879;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private ShareActionProvider shareActionProvider;
    private Snackbar snackbar;
    private CharSequence kominikat;
    private int czas = Snackbar.LENGTH_LONG;
    private int kolor;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView wersja = (TextView) findViewById(R.id.wersja);
        wersja.setText(getAndroidVersion());

        ImageView imageView = findViewById(R.id.imageView);
        int image = R.drawable.logo;
        imageView.setImageResource(image);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.nav_open_drawer,
                R.string.nav_close_drawer);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        shareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Zapraszamy na stronę uczelni");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send:
                findViewById(R.id.wersja).setBackgroundColor(Color.parseColor("#FFFF00"));
                StringBuilder s = new StringBuilder();
                SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
                boolean gravitySensor = sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
                if(gravitySensor) {
                    s.append("Telefon posiada czujnik grawitacyjny");
                } else {
                    s.append("Telefon nie posiada czujnika grawitacyjnego");
                }
                sensorManager.unregisterListener(this);
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();
                return true;
            case R.id.share:
                Toast.makeText(this, "Udostępnij", Toast.LENGTH_LONG).show();
                return true;
            case R.id.settings:
                Toast.makeText(this, "Ustawienia", Toast.LENGTH_LONG).show();
                Intent intent11 = new Intent(this, Ustawienia.class);
                startActivity(intent11);
                return true;
            case R.id.version:
                Toast.makeText(this, getAndroidVersion(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.about:
                //Toast.makeText(this, "Autorem programu jest Arkadiusz", Toast.LENGTH_LONG).show();
                kominikat = "Autorem programu jest Arkadiusz";
                snackbar = Snackbar.make(findViewById(R.id.snackbar),kominikat,czas);
                snackbar.setAction("wersja API", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, getAndroidVersion(), Toast.LENGTH_LONG).show();
                    }
                });
                snackbar.show();
                return true;
            case R.id.komunikator:
                Intent intent = new Intent(this, WyslijWiadomosc.class);
                startActivity(intent);
                return true;
            case R.id.sensors:
                Intent intent2 = new Intent(this, Czujniki.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    public String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " ("+ release +")";
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.nav_16:
                kominikat = "Lab. 6";
                Toast.makeText(this, kominikat, Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(this, Kierunki.class);
                startActivity(intent3);
                return true;
//            break;
            case R.id.nav_17:
                kominikat = "Lab. 7";
                Toast.makeText(this, kominikat, Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(this, Formularz.class);
                startActivity(intent4);
                break;
            case R.id.nav_18:
                kominikat = "Lab. 8";
                Toast.makeText(this, kominikat, Toast.LENGTH_LONG).show();
                Intent intent5 = new Intent(this, ObiektyUczelni.class);
                startActivity(intent5);
                break;
            case R.id.nav_19:
                kominikat = "Lab. 9";
                Toast.makeText(this, kominikat, Toast.LENGTH_LONG).show();
                Intent intent6 = new Intent(this, ObiektyLista.class);
                startActivity(intent6);
                break;
            case R.id.nav_110:
                kominikat = "Lab. 10";
                Toast.makeText(this, kominikat, Toast.LENGTH_LONG).show();
                Intent intent7 = new Intent(this, BazaDanych.class);
                startActivity(intent7);
                break;
            case R.id.nav_111:
                kominikat = "Lab. 11";
                Toast.makeText(this, kominikat, Toast.LENGTH_LONG).show();
                Intent intent8 = new Intent(this, SprawdzajSiec.class);
                startActivity(intent8);
                break;
            case R.id.nav_112:
                kominikat = "Lab. 12";
                Toast.makeText(this, kominikat, Toast.LENGTH_LONG).show();
                Intent intent9 = new Intent(this, Widget28879.class);
                startActivity(intent9);
                break;

            case R.id.temat:
                kominikat = "Tematem projektu jest aplikacja do zarządzania kolekcjami zdjęć";
                snackbar = Snackbar.make(findViewById(R.id.snackbar),kominikat, czas);
                snackbar.show();
                break;
            case R.id.ikona:
                kominikat = "A tu wyświetla się ikona projektu";
                snackbar = Snackbar.make(findViewById(R.id.snackbar),kominikat, czas);
                snackbar.show();
                break;

            default:
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(findViewById(R.id.wersja).getBackground() instanceof ColorDrawable) {
            outState.putInt("kolor", ((ColorDrawable) findViewById(R.id.wersja).getBackground()).getColor());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            kolor = savedInstanceState.getInt("kolor");
            findViewById(R.id.wersja).setBackgroundColor(kolor);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
