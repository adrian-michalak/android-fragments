package com.example.adrianmichalak.fragment_michalak.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.adrianmichalak.fragment_michalak.R;
import com.example.adrianmichalak.fragment_michalak.adapters.UsersAdapters;
import com.example.adrianmichalak.fragment_michalak.models.UsersModel;
import com.example.adrianmichalak.fragment_michalak.retrofit.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Users extends Fragment {
    private RecyclerView recyclerView;
    private UsersAdapters adapter;
    private List<UsersModel> usersList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display_users, container, false);

        findView(rootView);
        setAdapter(container.getContext());

        if (usersList.size() == 0) {
            Toast.makeText(getContext(), "Pobieranie danych z API ", Toast.LENGTH_SHORT).show();
            fetchData();
        } else {
            Toast.makeText(getContext(), "Pobrano z bazy danych", Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }

    private void fetchData() {
        Rest.getRest().getUsers().enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Call<List<UsersModel>> call, Response<List<UsersModel>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    for (UsersModel usersModel : response.body()) {
                        UsersModel um = new UsersModel();
                        um.setId(usersModel.getId());
                        um.setName(usersModel.getName());
                        um.setEmail(usersModel.getEmail());

                        //p.save();
                    }
                    usersList.addAll(response.body());  //lista naszych postow, przekazywanie przy pierwszym uruchomineiu do bazy
                    adapter.notifyDataSetChanged(); // informuje ze dane ulegly zmianie
                }
            }

            @Override
            public void onFailure(Call<List<UsersModel>> call, Throwable t) {
                Log.e("TEST", t.toString());
            }
        });
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.rv_display);
    }

    private void setAdapter(Context context) {
        adapter = new UsersAdapters(usersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
}
