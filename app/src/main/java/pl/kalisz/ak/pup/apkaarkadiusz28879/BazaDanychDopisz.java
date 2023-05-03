package pl.kalisz.ak.pup.apkaarkadiusz28879;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BazaDanychDopisz extends AppCompatActivity {
    private EditText nazwa2, rodzaj2;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baza_danych_dopisz);

        try {
            SQLiteOpenHelper organizacjaSQLiteOpenHelper = new OrganizacjaSQLiteOpenHelper(this);
            db = organizacjaSQLiteOpenHelper.getWritableDatabase();
            nazwa2 = (EditText) findViewById(R.id.editTextNazwa2);
            rodzaj2 = (EditText) findViewById(R.id.editTextRodzaj2);
        } catch(SQLException e) {
            Toast toast = Toast.makeText(this,"Baza danych jest niedostepna", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
    }

    public void zapiszNowaPozycje(View view) {
        ContentValues obiektValues = new ContentValues();
        obiektValues.put("NAZWA", nazwa2.getText().toString());
        obiektValues.put("RODZAJ", rodzaj2.getText().toString());
        db.insert("ORGANIZACJA", null, obiektValues);
        finish();
    }
}
