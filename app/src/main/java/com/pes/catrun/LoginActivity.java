package com.pes.catrun;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button NewB;
    private Button LogB;
    private EditText NameET;
    private EditText PsswdET;
    private DBManager manager;
    private Cursor cursor;
    private Toast toast;
    private TextView NAccount;
    private Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LoginActivity myActivity = this;

        setContentView(R.layout.activity_login);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        manager = new DBManager(this);





        /*NewB = (Button)findViewById(R.id.BNewDBEntry);
        NewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameET = (EditText)findViewById(R.id.ETNombre);
                String nom = NameET.getText().toString();
                PsswdET = (EditText)findViewById(R.id.ETPassword);
                String pass = PsswdET.getText().toString();
                manager.insertar(nom,pass);
                cursor = manager.cargarCursorUsuarios();
                cursor.moveToFirst();
                    while (cursor.moveToNext()) {
                        TextView Et1 = (TextView)findViewById(R.id.TVMensaje);
                        Et1.setText(cursor.getString(cursor.getColumnIndex("contrasenya")));
                        Log.d("N: ", cursor.getString(cursor.getColumnIndex("nom")));
                        Log.d("C: ", cursor.getString(cursor.getColumnIndex("contrasenya")));




                        //Toast.makeText(this, "Result " cursor.getString(cursor.getColumnIndex("nom")).toString(), Toast.LENGTH_LONG).show();
                    }

            }
        }); */
        PsswdET = (EditText)findViewById(R.id.input_password);
        PsswdET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            @Override
            public void afterTextChanged(Editable s) {
                PsswdET.setError(null);
            }
        });
        NAccount = (TextView)findViewById(R.id.link_signup);
        NAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(i);
            }
        });

        LogB = (Button)findViewById(R.id.btn_login);
       LogB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this){
                                wait(1500);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        PsswdET.setText(null);
                                        thread = null;
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }


                };

                NameET = (EditText)findViewById(R.id.input_email);
                String nom = NameET.getText().toString();
                PsswdET = (EditText)findViewById(R.id.input_password);
                String pass = PsswdET.getText().toString();
                cursor = manager.ComprobarUsuarioPassword(nom,pass);



                if (!cursor.moveToFirst() || cursor.getCount() == 0) {
                    //toast.setText("Los datos no son correctos");
                    PsswdET.setError("Los datos no son correctos");
                    //toast.show();


                    thread.start();






                    NameET.setText(null);







                }
                else {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
