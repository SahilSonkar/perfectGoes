package com.example.perfectgoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class showAllChat extends RecyclerView.Adapter<showAllChat.ViewHolder>{

    ArrayList<Helper> AllChat;
    Context context;
    ArrayList arrayList;

    public showAllChat(ArrayList allchart, Context context) {
//        this.arrayList=arrayList;
        this.AllChat =allchart;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.chatitem, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Helper mHelper = AllChat.get(position);
        holder.Username.setText(mHelper.getUsername());
    }

    @Override
    public int getItemCount() {
        return AllChat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView Username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Username=itemView.findViewById(R.id.AllChartUsername);
        }
    }

}

