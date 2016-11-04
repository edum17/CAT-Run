package com.pes.catrun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class CreateAccountActivity extends AppCompatActivity {

    private Button b_create_account;
    private Thread thread;
    private Toast toast;
    private Cursor cursor;
    private DBManager manager;
    private EditText username;
    private EditText password;
    private EditText repeated_password;
    private EditText mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        b_create_account = (Button) findViewById(R.id.CA_register_button);
        repeated_password = (EditText) findViewById(R.id.CA_input_password2);
        username = (EditText) findViewById(R.id.CA_input_username);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        manager = new DBManager(this);

        repeated_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                repeated_password.setError(null);
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                username.setError(null);
            }
        });

        b_create_account.setOnClickListener(new View.OnClickListener() {
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
                                        password.setText(null);
                                        repeated_password.setText(null);
                                        thread = null;
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                mail = (EditText)findViewById(R.id.CA_input_email);
                password = (EditText)findViewById(R.id.CA_input_password);
                repeated_password = (EditText) findViewById(R.id.CA_input_password2);
                username = (EditText) findViewById(R.id.CA_input_username);

                String passwd = password.getText().toString();
                String rpasswd = repeated_password.getText().toString();
                String uname = username.getText().toString();
                String email = mail.getText().toString();
                String faccio = "R";
                if(passwd.isEmpty() || passwd.equals(null) || rpasswd.isEmpty() || rpasswd.equals(null) ||
                uname.isEmpty() || uname.equals(null) || email.isEmpty() || email.equals(null)){
                    toast.setText("Tots els camps són obligatoris");
                    toast.show();
                }
                else {
                    if (!passwd.equals(rpasswd)) {
                        repeated_password.setError("Les contrasenyes no coincideixen", null);
                        thread.start();
                    } else {
                        //Log.d("LoginActivity-> ", "Username: " + uname + "     Password: " + passwd);
                        //cursor = manager.ComprobarUsernamePassword(uname, passwd);

                        CtlUsuaris ctlUsuaris = new CtlUsuaris();
                        ctlUsuaris.init();
/*                        String nombre;
                        nombre = ctlUsuaris.getUser(uname);

                        if (nombre.length() > 2)
                        {
                            toast.setText("Aquest usuari ja existeix.");
                            toast.show();
                        }
                        else {
  */                        try {
                                ctlUsuaris.postRegister(uname, passwd, email, faccio);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                           //if (!cursor.moveToFirst() || cursor.getCount() == 0) {
                           //  manager.insertarValoresCreateAccount(uname, passwd, email);

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("alies", uname);
                            editor.putString("email", email);
                            editor.putString("contrasenya", passwd);
                            editor.putInt("nivell", 1);
                            editor.putInt("experiencia", 0);
                            editor.putString("faccio", faccio);
                            // value to store
                            editor.commit();
                            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            /*} else {
                            Log.d("LoginActivity->", "Entro aqui");
                            repeated_password.setError("L'usuari ja existeix", null);
                            thread.start();
                        }
*///                  }
                    }
                }

               /* PsswdET = (EditText)findViewById(R.id.input_password);
                String pass = PsswdET.getText().toString();
                cursor = manager.ComprobarUsuarioPassword(nom,pass);

                if (!cursor.moveToFirst() || cursor.getCount() == 0) {
                    PsswdET.setError("Los datos no son correctos");
                    thread.start();
                    NameET.setText(null);
                }
                else {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }*/
            }
        });
    }
}
