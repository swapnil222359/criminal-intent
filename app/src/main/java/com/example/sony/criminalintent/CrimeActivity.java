package com.example.sony.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {


    private static final String EXTRA_CRIME_ID ="com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context context,UUID crimeid){
        Intent intent = new Intent(context,CrimeActivity.class);
        intent.putExtra( EXTRA_CRIME_ID,crimeid);
        return intent;
    }
    @Override
    public Fragment createFragment() {
        //return new CrimeFragment();
        UUID id= (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(id);
    }
}
