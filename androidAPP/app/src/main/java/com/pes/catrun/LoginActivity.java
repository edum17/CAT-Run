package com.pes.catrun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    //private Button NewB;
    private Button LogB;
    private EditText usernameET;
    private EditText PsswdET;
    //private DBManager manager;
    //private Cursor cursor;
    private Toast toast;
    private TextView NAccount;
    private Thread thread;
    private String JSONUser;


    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(preferences.getString("alies", "").length() > 0)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        //GOOGLE
      //  mStatusTextView = (TextView) findViewById(R.id.status);

        // Button listeners
        //TODO modificar listener
       // findViewById(R.id.LA_google_sign_in_button).setOnClickListener(this);
       // findViewById(R.id.sign_out_button).setOnClickListener(this);
       // findViewById(R.id.disconnect_button).setOnClickListener(this);

/*
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity , this /* OnConnectionFailedListener )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.LA_google_sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());


*/











        //GOOGLE
        final LoginActivity myActivity = this;

        setContentView(R.layout.activity_login);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        //manager = new DBManager(this);

        PsswdET = (EditText)findViewById(R.id.input_password);
        SignInButton signInButton = (SignInButton) findViewById(R.id.LA_google_sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.setText("Has iniciado sesión con Google");
                toast.show();
            }
        });


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

                usernameET = (EditText)findViewById(R.id.LA_input_username);
                PsswdET = (EditText)findViewById(R.id.input_password);

                String uname = usernameET.getText().toString();
                String pass = PsswdET.getText().toString();

                //Log.d("LoginActivity-> ", "Username: " + uname + "     Password: " + pass);

                //cursor = manager.ComprobarUsernamePassword(uname,pass);

                //LOCAL
                /*
                if (!cursor.moveToFirst() || cursor.getCount() == 0) {
                    PsswdET.setError("Los datos no son correctos",null);
                    thread.start();
                    usernameET.setText(null);
                }
                else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }*/
                //Log.e("RAFA_PAYASO","RAFA_PAYASO");
                CtlUsuaris ctlUsuaris = new CtlUsuaris();

                ctlUsuaris.init();

                JSONUser = ctlUsuaris.getLogin(uname,pass);

                //String ExisteUser = ctlUsuaris.getUser(uname);

                //Log.e("LoginActivity-> ", "Username: " + uname + "     Password: " + pass);
                //Log.e("LoginActivity-> ", "ExisteUser: " + JSONUser );
//                if (!cursor.moveToFirst() || cursor.getCount() == 0) {

                //Log.e("JSON derulo ----> ","HOLA"+JSONUser);
                if (JSONUser.isEmpty() || JSONUser.contains("refused")) {
                    PsswdET.setError("No se puede conectar con el servidor", null);
                    //toast.show();
                    thread.start();
                }
                else if(JSONUser.length() < 3)
                {
                    //toast.setText("Los datos no son correctos");
                    PsswdET.setError("Los datos no son correctos", null);
                    //toast.show();
                    thread.start();
                }
                else {
                    try {
                        JSONArray dadesUser = new JSONArray(JSONUser);
                        JSONObject jsonUser = dadesUser.getJSONObject(0);

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("alies", jsonUser.getString("alies"));
                        editor.putString("email", jsonUser.getString("email"));
                        editor.putString("contrasenya", jsonUser.getString("contrasenya"));
                        editor.putString("nivell", jsonUser.getString("nivell"));
                        editor.putString("experiencia", jsonUser.getString("experiencia"));
                        editor.putString("faccio", jsonUser.getString("faccio"));
                        // value to store
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LA_google_sign_in_button:
                signIn();
                break;
            /*case R.id.sign_out_button:
                signOut();
                break;
            case R.id.disconnect_button:
                revokeAccess();
                break;*/
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}

