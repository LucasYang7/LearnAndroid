package com.example.yangzhe.learnfragment;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangzhe.learnactivity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    private static final String TAG = "AddFragment";
    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onCreateView");
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onDetach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onStop");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e(TAG,getClass().getSimpleName() + "\t" + "onViewStateRestored");
    }
}
