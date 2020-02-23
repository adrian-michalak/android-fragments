package com.example.adrianmichalak.fragment_michalak.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrianmichalak.fragment_michalak.R;
import com.example.adrianmichalak.fragment_michalak.models.UsersModel;

import java.util.List;

public class UsersAdapters extends RecyclerView.Adapter<UsersAdapters.UserViewHolder> {
    private List<UsersModel> userDataList;
    public  UsersAdapters(List<UsersModel> dataList){
        this.userDataList = dataList;
    }
    @NonNull
    @Override
    public UsersAdapters.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_users,viewGroup,false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapters.UserViewHolder userViewHolder, int position ){
        UsersModel item = userDataList.get(position);

        userViewHolder.name.setText(item.getName());
        userViewHolder.email.setText(item.getEmail());
        userViewHolder.id.setText(String.valueOf(item.getId()));
    }

    @Override
    public int getItemCount(){
        if(userDataList != null){
            return  userDataList.size();
        } else {
            return 0;
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView name,id, email;
        UserViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.user_name);
            id = view.findViewById(R.id.user_id);
            email = view.findViewById(R.id.user_email);

        }

    }
}
