package com.ohue.medmanager.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ohue.medmanager.base.BaseFragment;
import com.ohue.medmanager.miscellanous.Utils;
import com.ohue.medmanager.models.Medication;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import itoo.ohue.medmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {
    @BindView(R.id.med_name_edit)
    EditText medNameEt;
    @BindView(R.id.med_note_edit)
    EditText medNoteEt;
    @BindView(R.id.med_date)
    TextView medDateTxt;
    @BindView(R.id.med_time)
    TextView medTimeTxt;


    HomePresenter presenter;
    Calendar calendar;



    public static  HomeFragment homeFragment = null;
    public  static HomeFragment getInstance () {

        if(homeFragment == null) {
           homeFragment = new HomeFragment();
        }
        return homeFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      presenter =  new HomePresenter(this, getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this , view);
         calendar = Calendar.getInstance();
        presenter.provideCalendar(calendar);
        initViews();


        return view;
    }

    @OnClick(R.id.med_time)
        public void timeAct() {
        presenter.createDialog(true);
    }

    @OnClick(R.id.med_date)
    public void dateAct() {
        presenter.createDialog(false);
    }

    @OnClick(R.id.med_add)
    public void addMed() {
        String name = medNameEt.getText().toString();
        String dtlIFAny= medNoteEt.getText().toString();
        String calendar = Utils.databaseDateFormat(presenter.getCalendar().getTime());
        presenter.addMedication(new Medication(name , calendar , dtlIFAny));
    }

    private void initViews() {
        setTimeText(Utils.formatDate(calendar.getTime(), false));
        setDateText(Utils.formatDate(calendar.getTime(), true));
        setToolbarTitle(R.string.home_nav);
    }
    @Override
    public void medicationAdded( boolean isAdded) {
        if(isAdded) {
            makeToast(R.string.medication_added);
            resetViews();
            }
        else{
            makeToast(R.string.generic_error_message);
        }
    }

    @Override
    public void setTimeText(String time) {
        medTimeTxt.setText(time);
    }

    @Override
    public void setDateText(String date) {
        medDateTxt.setText(date);
    }

    public void resetViews() {
        initViews();
        medNameEt.setText("");
        medNoteEt.setText("");
    }

}
