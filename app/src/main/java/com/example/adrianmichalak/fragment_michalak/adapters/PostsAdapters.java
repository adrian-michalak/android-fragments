package com.example.adrianmichalak.fragment_michalak.adapters;


import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrianmichalak.fragment_michalak.R;
import com.example.adrianmichalak.fragment_michalak.models.PostsModel;

import java.util.List;

public class PostsAdapters extends RecyclerView.Adapter<PostsAdapters.PostViewHolder> {
    private List<PostsModel> dataList;
    public  PostsAdapters(List<PostsModel> dataList){
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_posts,viewGroup,false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int position ){
        PostsModel item = dataList.get(position);

        postViewHolder.title.setText(item.getTitle());
        postViewHolder.id.setText(String.valueOf(item.getId()));
    }

    @Override
    public int getItemCount(){
        if(dataList != null){
            return  dataList.size();
        } else {
            return 0;
        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        public TextView title,id;
        PostViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.post_title);
            id = view.findViewById(R.id.post_id);

        }

    }
}
