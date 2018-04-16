package com.ohue.medmanager;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ohue.medmanager.base.BaseDrawerActivity;
import com.ohue.medmanager.helper.Instantiater;
import com.ohue.medmanager.helper.SharedPreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import itoo.ohue.medmanager.R;


public class EntryActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.gg_sign_in_button)
    SignInButton ggSignInButton;

    GoogleSignInOptions gso;
  static  GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount account;
    int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        ButterKnife.bind(this);
        googleSetup();
        ggSignInButton.setOnClickListener(this);


    }

    @OnClick(R.id.app_sign_in_btn)
    public void enter() {
        Intent intent = new Intent(EntryActivity.this, BaseDrawerActivity.class);
        startActivity(intent);
    }

    private void googleSetup() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        account = GoogleSignIn.getLastSignedInAccount(this);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //account null if no previously logged in user
        if (account != null)
            progress(account);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gg_sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                progress(account);
            } else {
                Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show();
            }
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
            Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show();
        }
    }

    private void progress(GoogleSignInAccount account) {
        Instantiater.getSharedPreferenceHelper().saveName(this, account.getDisplayName(), account.getFamilyName());
        Intent intent = new Intent(EntryActivity.this, BaseDrawerActivity.class);
        startActivity(intent);
    }
    public static void signOutNow() {
        signOut();
    }

    private  static void signOut() {
        mGoogleSignInClient.signOut();

    }
}
