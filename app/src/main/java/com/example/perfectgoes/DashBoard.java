package com.example.perfectgoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {


    private Bundle data;
    private String name,username,email,password,phoneNo;
    private Helper helper;
    //Firebase tool
    FirebaseDatabase database;
    DatabaseReference reference;
    private ArrayList<Helper> ListOfChart;
    private  ArrayList arrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        ListOfChart= new ArrayList<Helper>();
        recyclerView=findViewById(R.id.RecyclerViewAllChart);
        setRecyclerView();
        arrayList=new ArrayList();
        data=getIntent().getExtras();
//        filldata();
        database =FirebaseDatabase.getInstance();
        reference =database.getReference();
        reference.child("chart").child(username).setValue(helper)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        ListOfChart.add(helper);
                        Toast.makeText(DashBoard.this, "Sent", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DashBoard.this, "connection Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void filldata(){
        
        if(data !=null)
        {
            Toast.makeText(this, "Filled", Toast.LENGTH_SHORT).show();
            name =data.getString("name");
            username =data.getString("username");
            password =data.getString("password");
            phoneNo =data.getString("phoneNo");
            email =data.getString("email");
            helper = new Helper(name,username,password,email,phoneNo,0,null);
        }else
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
    }

    private void setRecyclerView(){

        //Toast.makeText(this, ListOfChart.size(), Toast.LENGTH_SHORT).show();
        showAllChat Adapter = new showAllChat(ListOfChart,this);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
