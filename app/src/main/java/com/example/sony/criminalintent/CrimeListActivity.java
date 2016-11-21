package com.example.sony.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by sony on 11-11-2016.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CrimeListFragment();
    }
}
