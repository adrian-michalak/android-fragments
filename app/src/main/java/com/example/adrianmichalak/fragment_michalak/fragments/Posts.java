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
import com.example.adrianmichalak.fragment_michalak.adapters.PostsAdapters;
import com.example.adrianmichalak.fragment_michalak.models.PostsModel;
import com.example.adrianmichalak.fragment_michalak.retrofit.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Posts extends Fragment {
    private RecyclerView recyclerView;
    private PostsAdapters adapter;
    private List<PostsModel> postList = new ArrayList<>();
    public static Posts newInstance() {
        return new Posts();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display_posts, container, false);

        findView(rootView);

        setAdapter(container.getContext());
        //getDataFromDataBase();

        if(postList.size()==0){
            Toast.makeText(getContext(),"Pobieranie danych z API ",Toast.LENGTH_SHORT).show();
            fetchData();
        } else {
            Toast.makeText(getContext(),"Pobrano z bazy danych",Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

       /* private void getDataFromDataBase() {
            postList.addAll(SQLite.select().from(PostModel.class).queryList());
            adapter.notifyDataSetChanged(); //info ze dane zostaly zmienione
        }*/

    private void fetchData() {
        Rest.getRest().getPost().enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                if(response.isSuccessful() && response.body() != null)
                {

                    for(PostsModel post: response.body()){
                        PostsModel p = new PostsModel();
                        p.setUserId(post.getUserId());
                        p.setId(post.getId());
                        p.setTitle(post.getTitle());
                        p.setBody(post.getBody());
                        //p.save();
                    }
                    postList.addAll(response.body());  //lista naszych postow, przekazywanie przy pierwszym uruchomineiu do bazy
                    adapter.notifyDataSetChanged(); // informuje ze dane ulegly zmianie
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Log.e("TEST", t.toString());
            }
        });
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.rv_displayPosts);
    }

    private void setAdapter(Context context)
    {
        adapter= new PostsAdapters(postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

}
