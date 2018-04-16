package com.ohue.medmanager.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ohue.medmanager.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import itoo.ohue.medmanager.R;


/**
 * Created by OHUE JOHN on 4/2/2018.
 */

public class SignUpActivity extends BaseActivity<SignUpContract.Presenter> implements
        SignUpContract.View, View.OnClickListener {
    @BindView(R.id.sign_up_in_btn)
    Button signUpInBtn;
    @BindView(R.id.btn_text_sign)
    TextView signText;
    @BindView(R.id.email_et)
    EditText emailEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpUnbinder(ButterKnife.bind(this));
        new SignUpPresenter(this, this);
        signUpInBtn.setOnClickListener(this);

    }


    @Override
    public void setPresenter(SignUpContract.Presenter presenter) { }

    @Override
    public void makeToast(int messageRes) { }

    @Override
    public void showEditTextError(String message) {
        if(TextUtils.equals(message, getResources().getString(R.string.email_error))){
            emailEt.setError(message);
        }
        else if ( TextUtils.equals(message,getResources().getString(R.string.password_error))){
            passwordEt.setError(message);
        }

    }

    @Override
    public void showEditTextError(int message) {
        showEditTextError(getString(message));
    }

    @Override
    public void setToolbarTitle(int message) { }

    @Override
    public void onClick(View view) {
      if(view.getId() == R.id.sign_up_in_btn){
        presenter.proceed( emailEt.getText().toString(), passwordEt.getText().toString());
      }
    }
}
