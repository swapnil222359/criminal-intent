package com.example.sony.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by sony on 10-11-2016.
 */

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckbox;
    public static CrimeFragment newInstance(UUID id){
        Bundle args= new Bundle();
        args.putSerializable(ARG_CRIME_ID,id);
        CrimeFragment fragment=new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       // mCrime=new Crime();
        //UUID id=getActivity().getIntent().getSerializableExtra("com.bignerdranch.android.criminalintent.crime_id",);
        UUID id= (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime=CrimeLab.get(getActivity()).getCrime(id);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime,container,false);

        mTitleField= (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton= (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckbox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckbox.setChecked(mCrime.isSolved());
        mSolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
       // getActivity().setResult(Activity.RESULT_OK,null);
        return v;
    }
}
