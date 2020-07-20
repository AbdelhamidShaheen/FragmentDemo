package com.example.fragmentdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AFragment.Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void set_message(String msg) {


        BFragment bFragment = (BFragment) getSupportFragmentManager().findFragmentById(R.id.fragment4);
        if ((bFragment != null) && (bFragment.isAdded())) {
            bFragment.send_message(msg);
        } else {
            BFragment bFragment1 = new BFragment();
            Bundle bundle = new Bundle();
            bundle.putString("MSG", msg);
            bFragment1.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.AF_Container, bFragment1);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        TrackLifeCycle.Commit("Main1 Star");

    }

    @Override
    protected void onPause() {
        super.onPause();
        TrackLifeCycle.Commit("Main1 Pause");

    }

    @Override
    protected void onResume() {
        super.onResume();
        TrackLifeCycle.Commit("Main1 Reusme");

    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof AFragment) {
            AFragment aFragment = (AFragment) fragment;
            aFragment.setOnCommunicator(this);
        }
        TrackLifeCycle.Commit(" Attach From Activity" + fragment.getTag());

    }

    @Override
    protected void onStop() {
        super.onStop();
        TrackLifeCycle.Commit("Main1 Stop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        TrackLifeCycle.Commit("Main1 Restar");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TrackLifeCycle.Commit("Main1 Destroy");
        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


}