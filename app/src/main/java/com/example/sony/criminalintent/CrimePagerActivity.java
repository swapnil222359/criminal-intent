package com.example.sony.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by sony on 15-11-2016.
 */

public class CrimePagerActivity extends FragmentActivity {

    private static final String EXTRA_CRIME_ID ="com.bignerdranch.android.criminalintent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packagecontext, UUID crimeId)
    {
        Intent intent= new Intent(packagecontext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId= (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager= (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes=CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager= getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime=mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for(int i=0;i<mCrimes.size();i++)
        {
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

}
