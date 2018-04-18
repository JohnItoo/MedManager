package com.ohue.medmanager;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ohue.medmanager.base.BaseFragment;
import com.ohue.medmanager.helper.Instantiater;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import itoo.ohue.medmanager.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {
    @BindView(R.id.first_name_edit)
    EditText firstNameEt;
    @BindView(R.id.last_name_edit)
    EditText lastNameEt;
    private static ProfileFragment instance = null;

    public static ProfileFragment getInstance() {
        if(instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
              HashMap<String,  String> mapNames = Instantiater.getSharedPreferenceHelper().getNames(getActivity());
              if(!TextUtils.isEmpty(mapNames.get("firstName")) || (!TextUtils.isEmpty(mapNames.get("lastName")))) {
                        firstNameEt.setText(mapNames.get("firstName"));
                        lastNameEt.setText(mapNames.get("lastName"));
              }
        return view;
    }

    @OnClick(R.id.sign_out_google)
    public void signOutGoogle() {
              EntryActivity.signOutNow();
    }

    @OnClick(R.id.save_profile)
    public void saveProfile() {
        validate();
    }

    public void validate() {
        String firstName = firstNameEt.getText().toString();
        String lastName = lastNameEt.getText().toString();
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
            Toast.makeText(getActivity(), R.string.field_error, Toast.LENGTH_SHORT).show();
        } else {
            Instantiater.getSharedPreferenceHelper().saveName(getActivity(), firstName, lastName);
            Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
        }

    }

}
