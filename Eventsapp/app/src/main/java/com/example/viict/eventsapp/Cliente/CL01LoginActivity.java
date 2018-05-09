package com.example.viict.eventsapp.Cliente;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viict.eventsapp.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CL01LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG ="";

    //INTERFAZ
    private TextView txvWelcome1;
    private EditText emailField, passwordField;
    private Button btnLogin;
    private LoginButton lbFacebook;
    private SignInButton signInButton;
    private TextView nuevoUsr;
    private Button recuperarPass, btnRegistrar;

    //FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //FACEBOOK
    private CallbackManager mCallbackManager;

    //GOOGLE
    private GoogleApiClient googleApiClient;
    public static final int SIGN_IN_CODE=777;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_cl01_login);

        //CONEXION ENTRE INTERFAZ Y VARIABLES
        emailField = (EditText) findViewById(R.id.direccion);
        passwordField = (EditText) findViewById(R.id.password2);
        btnLogin = (Button) findViewById(R.id.login);
        lbFacebook = (LoginButton) findViewById(R.id.login_button);
        signInButton = (SignInButton) findViewById(R.id.signInButton);
        recuperarPass = (Button) findViewById(R.id.recuperarPass) ;
        txvWelcome1 = (TextView) findViewById(R.id.txvWelcome1);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        Typeface fuente = Typeface.createFromAsset(getAssets(),"fonts/Magettas Regular.otf");
        txvWelcome1.setTypeface(fuente);


        //INSTANCIA LA CONEXION CON FIREBASE
        mAuth = FirebaseAuth.getInstance();

        //INSTANCIA LA CONEXION A FACEBOOK
        mCallbackManager = CallbackManager.Factory.create();

        //GOOGLE
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //ACCESO A LA SEGUNDA PANTALLA
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!= null){
                    startActivity(new Intent(CL01LoginActivity.this, CL04PrincipalClienteActivity.class));
                }
            }
        };

        //Olvide contraseña
        recuperarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(CL01LoginActivity.this,CL03RecuperarPasswordActivity.class);
                startActivity(intent);
            }
        });

        //Create cuenta por formulario
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CL01LoginActivity.this, CL02FormularioActivity.class));
            }
        });
        //ACCIÓN DE CLICK AL BOTON INGRESAR
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogearUsuario();
            }
        });

        //Accion del boton google
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });

        //FACEBOOK
        lbFacebook.setReadPermissions("email", "public_profile");

        lbFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                startActivity(new Intent(CL01LoginActivity.this, CL04PrincipalClienteActivity.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),R.string.cancel_login,Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),R.string.error_login,Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        //google
        if(requestCode == SIGN_IN_CODE){
            GoogleSignInResult  result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }
    //gOOGLE
    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){

            startActivity(new Intent(CL01LoginActivity.this, CL04PrincipalClienteActivity.class));

        }else{
            Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show();
        }
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Bienvenido a la Aplicación EventsAPP");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(CL01LoginActivity.this, "Usuario no registrado en el sistema",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }



    private void LogearUsuario(){

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(emailField.getText().toString().length()==0 && passwordField.getText().toString().length()==0){
                            Toast.makeText(CL01LoginActivity.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                        }else{
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Bievenido a EventsApp" + task.isSuccessful()+"\nDisfrutalo!");
                            }else{
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(CL01LoginActivity.this, "Usuario no registrado en el sistema, \nVuelve a intentarlo!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //autenticacion google
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

       /* AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(CL01LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });*/
    /*    AuthCredential credential = GoogleAuthProvider.getCredential(googleIdToken, null);

        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "linkWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "linkWithCredential:failure", task.getException());
                            Toast.makeText(AnonymousAuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });*/
    }

}
