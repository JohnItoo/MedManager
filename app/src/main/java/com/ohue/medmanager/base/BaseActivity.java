package com.ohue.medmanager.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;
import butterknife.Unbinder;


/**
 * Created by OHUE JOHN on 3/31/2018.
 */

public abstract class BaseActivity <T extends BasePresenter> extends MyThemeActivity implements BaseView<T>  {
    private ProgressDialog dialog;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this);
    }

    public void showProgressDialog(String message){

        dialog.setIndeterminate(true);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public void dismissProgressDialog(){
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }

    protected void setUpUnbinder(Unbinder unbinder){
        this.unbinder = unbinder;
    }
    public void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if(unbinder!= null){
            unbinder.unbind();
        }
        dismissProgressDialog();
        super.onDestroy();
    }


}
