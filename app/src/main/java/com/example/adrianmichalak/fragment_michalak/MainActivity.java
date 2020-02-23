package com.example.adrianmichalak.fragment_michalak;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adrianmichalak.fragment_michalak.fragments.Posts;
import com.example.adrianmichalak.fragment_michalak.fragments.Users;
import com.example.adrianmichalak.fragment_michalak.navigations.NavigationsListener;

public class MainActivity extends AppCompatActivity implements NavigationsListener {

    Button firstFragment, secondFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFragment = findViewById(R.id.firstFragmentBtn);
        secondFragment = findViewById(R.id.secondFragmentBtn);

        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment( new Posts());
            }
        });
        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(  new Users());
            }
        });

    }

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.root,fragment);
        fragmentTransaction.commit();
    }
}
