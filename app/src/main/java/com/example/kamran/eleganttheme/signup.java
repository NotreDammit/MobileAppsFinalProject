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

public class signup extends AppCompatActivity {
    DBHelper dbHelper;
    EditText passwordd,mobphone,mail,usrusr;
    TextView login,signup;
    public static final String EXTRA_MESSAGE = "com.example.kamran.eleganttheme.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new DBHelper(getApplicationContext());
        //dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

        usrusr = (EditText) findViewById(R.id.usrusr);
        passwordd = (EditText)findViewById(R.id.passwrd);
        mail = (EditText) findViewById(R.id.mail);
        mobphone = (EditText) findViewById(R.id.mobphone);

        usrusr.setText("");
        passwordd.setText("");
        mail.setText("");
        mobphone.setText("");

        login = (TextView)findViewById(R.id.logiin);
        signup = (TextView)findViewById(R.id.sup);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Lato-Light.ttf");
        signup.setTypeface(custom_font);
        mail.setTypeface(custom_font);
        mobphone.setTypeface(custom_font);
        passwordd.setTypeface(custom_font);
        usrusr.setTypeface(custom_font);
        login.setTypeface(custom_font);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signup.this,login.class);
                String username = usrusr.getText().toString();
                String password = passwordd.getText().toString();

                it.putExtra(EXTRA_MESSAGE, username);
                it.putExtra(EXTRA_MESSAGE, password);

                startActivity(it);
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String noti = "Sign up successfully";
                Toast.makeText(com.example.kamran.eleganttheme.signup.this,noti,Toast.LENGTH_SHORT ).show();
            }
        });
    }

    public void fn_insert(View view) {
        String username = String.valueOf(usrusr.getText());
        String password = String.valueOf(passwordd.getText());
        String email = String.valueOf(mail.getText());
        String phone = String.valueOf(mobphone.getText());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_NAME, username);
        contentValues.put(DBHelper.COL_NAME_2, password);
        contentValues.put(DBHelper.COL_NAME_3, email);
        contentValues.put(DBHelper.COL_NAME_4, phone);
        dbHelper.insertData(DBHelper.TABLE_BOOK, contentValues);
        usrusr.setText("");
        passwordd.setText("");
        mail.setText("");
        mobphone.setText("");
    }
}