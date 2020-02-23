package com.example.adrianmichalak.fragment_michalak.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.adrianmichalak.fragment_michalak.navigations.NavigationsListener;

public class baseFragment extends android.app.Fragment {
    NavigationsListener navigationListener;
    public NavigationsListener getNavigationInteractions() {return navigationListener;}
    public void OnDetach()
    {
        super.onDetach();
        navigationListener=null;
    }
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof NavigationsListener){
            navigationListener = (NavigationsListener) context;
        }
    }
}
