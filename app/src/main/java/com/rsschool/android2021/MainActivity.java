package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Navigator {
    private int generatedNumber = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("SECOND_FRAGMENT");
        if (fragment != null && fragment.isVisible()) {
            openFirstFragment();
        }
    }

    public void openFirstFragment() {
        final FirstFragment firstFragment = FirstFragment.newInstance(generatedNumber);
        firstFragment.setNavigator((Navigator) this);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    public void openSecondFragment(int min, int max) {
        final SecondFragment secondFragment = SecondFragment.newInstance(min, max);
        secondFragment.setNavigator((Navigator) this);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment, "SECOND_FRAGMENT");
        transaction.commit();
    }

    public void saveGeneratedNumber(int number) {
        generatedNumber = number;
    }
}
