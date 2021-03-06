package com.bioshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class LoginActivity extends CustomActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private LoginButton fb_loginButton;
    private SignInButton gmail_signInButton;
    private CallbackManager callbackManager;
    private TextView newUserTextView, signUpTextView;
    private  TextView forgetpassword;
    private EditText userName, password;
    private Button Login_btn;
    private ImageButton see_password;
    private boolean isMainLobbyStarted = false;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        fb_loginButton= (LoginButton)findViewById(R.id.fbLogin_button);
        callbackManager=CallbackManager.Factory.create();
        LoginManager.getInstance().logOut();
        fb_loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                // keygraphRequest(loginResult.getAccessToken());
                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent fbIntent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(fbIntent);
               /* if(!isMainLobbyStarted) {
                    startActivity(fbIntent);
                    isMainLobbyStarted = true;
                    //finish();

                }*/
            }
            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }


        });

        setupuiElement();




    }


    private void setupuiElement() {
        setTouchNClick(R.id.btn_login);

        setTouchNClick(R.id.gmail_sign_in_button);


        setTouchNClick(R.id.signUpTextView);
        setTouchNClick(R.id.forgotPwdTextView);


        setTouchNClick(R.id.img_btn_hide_paswd);




        gmail_signInButton=(SignInButton)findViewById(R.id.gmail_sign_in_button);
        Login_btn=(Button)findViewById(R.id.btn_login) ;

        newUserTextView = (TextView) findViewById(R.id.newUserTextView);
        newUserTextView.setText(Html.fromHtml(getString(R.string.new_user_sign_up_here)));
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        signUpTextView.setText(Html.fromHtml(getString(R.string.signUp_textView)));
        forgetpassword=(TextView)findViewById(R.id.forgotPwdTextView);

        see_password=(ImageButton)findViewById(R.id.img_btn_hide_paswd);







        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

       /* gmail_signInButton = (SignInButton) findViewById(R.id.gmail_sign_in_button);
        gmail_signInButton.setOnClickListener(this);*/

        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.adityavats.splashscree",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
























    }



    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_login) {
           // startActivity(new Intent(new Intent(AddAddressActivity.this, SavedAddressActivity.class)));
        }else if(v.getId() == R.id.gmail_sign_in_button){
            signIn();
        }else if(v.getId() == R.id.signUpTextView){
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        }else if(v.getId() == R.id.forgotPwdTextView){
            startActivity(new Intent(LoginActivity.this, ResetActivity.class));
        }else if(v.getId() == R.id.img_btn_hide_paswd){

        }
    }




    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }*/
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }*/
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            //GoogleSignInAccount acct = result.getSignInAccount();
            // mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            Intent googleIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(googleIntent);
//            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
//            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Fail to sign in", Toast.LENGTH_SHORT).show();
            /*mStatusTextView.setText(R.string.signed_out);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);*/
        }
    }

}
