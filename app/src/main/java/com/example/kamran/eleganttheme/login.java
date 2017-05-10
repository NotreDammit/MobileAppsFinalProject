package com.example.kamran.eleganttheme;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class login extends AppCompatActivity {
    DBHelper dbHelper;
    TextView bytes,sup,lin;
    EditText usr,pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(getApplicationContext());
        //dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

        usr = (EditText) findViewById(R.id.usrusr);
        pswd = (EditText)findViewById(R.id.passwrd);

        usr.setText("");
        pswd.setText("");

        lin = (TextView)findViewById(R.id.logiin);
        sup = (TextView)findViewById(R.id.sup);
        bytes = (TextView)findViewById(R.id.bytes);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Lato-Light.ttf");
        bytes.setTypeface(custom_font);
        pswd.setTypeface(custom_font);
        sup.setTypeface(custom_font);
        lin.setTypeface(custom_font);
        usr.setTypeface(custom_font);

        Intent intent = getIntent();
        String username = intent.getStringExtra(signup.EXTRA_MESSAGE);
        String password = intent.getStringExtra(signup.EXTRA_MESSAGE);
        usr.setText(username);
        pswd.setText(password);

        sup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(login.this,signup.class);
                startActivity(it);
            }
        });
    }

    public void fn_check(View view) {
        String username = String.valueOf(usr.getText());
        String password = String.valueOf(pswd.getText());
        if(dbHelper.checkUP(DBHelper.TABLE_BOOK, username, password) == 1) {
            Intent it = new Intent(login.this, MainActivity.class);
            startActivity(it);
        }
        else {
            Toast.makeText(login.this, "Incorrect Password", Toast.LENGTH_LONG).show();
        }
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DBHelper.COL_NAME, username);
//        contentValues.put(DBHelper.COL_NAME_2, password);
       // dbHelper.insertData(DBHelper.TABLE_BOOK, contentValues);
        usr.setText("");
        pswd.setText("");
    }
}
