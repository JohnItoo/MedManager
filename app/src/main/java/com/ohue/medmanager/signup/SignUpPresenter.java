package com.ohue.medmanager.signup;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by OHUE JOHN on 4/2/2018.
 */

public class SignUpPresenter implements SignUpContract.Presenter {
    SignUpContract.View view;
    Context context;

    public SignUpPresenter(Context context, SignUpContract.View view){
        this.view = view;
        this.context = context;
    }
    @Override
    public void proceed(String email, String password) {
      validateFields(email, password);

    }

    private void validateFields(String email, String password) {
        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            view.showEditTextError("Please enter a valid email");
        }
        if(TextUtils.isEmpty(password) || password.length()<=5 ){
            view.showEditTextError("Please enter a valid password");
            if(password.length()<=5)
                view.makeToast("Your password should be more than five characters");
        }


    }

}
