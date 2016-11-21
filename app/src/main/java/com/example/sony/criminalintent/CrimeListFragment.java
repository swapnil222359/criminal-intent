package com.example.sony.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sony on 11-11-2016.
 */

public class CrimeListFragment extends Fragment  {

    private static int REQUEST_CRIME=1;
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private Crime mCrime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView= (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab= CrimeLab.get(getActivity());
        List<Crime>crimes=crimeLab.getCrimes();
        if(mAdapter==null){

            mAdapter=new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else
            mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }



    public class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder( View itemview)
        {
            super(itemview);
            itemview.setOnClickListener(this);
            mTitleTextView= (TextView) itemview.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView= (TextView) itemview.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox= (CheckBox) itemview.findViewById(R.id.list_item_crime_solved_check_box);
        }
        public void bindCrime(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
            mSolvedCheckBox.setEnabled(false);
        }

        @Override
        public void onClick(View v) {
           // Log.d("dsaf", String.valueOf(5));
            Toast.makeText(getActivity(),mCrime.getTitle() + " clicked!",Toast.LENGTH_SHORT).show();
            Intent intent= CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
            //startActivityForResult(intent,REQUEST_CRIME);
        }

    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CRIME){

        }

    }*/

    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes=crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime,parent,false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime =mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
