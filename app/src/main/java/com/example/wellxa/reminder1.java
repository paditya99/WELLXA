package com.example.wellxa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class reminder1 extends AppCompatActivity {
    String value1;
    String value2;
    int position=0;
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder1);
        createExampleList();
        buildRecyclerView();
        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("Value1");
        value2 = extras.getString("Value2");
        insertItem(position);


        FloatingActionButton f1=(FloatingActionButton)findViewById(R.id.floatingActionButton);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),reminder2.class);
                startActivity(i);
            }
        });

    }

    public void insertItem(int position) {
        this.position = position;
        mExampleList.add(position, new ExampleItem(R.drawable.notify,value1,value2));
        mAdapter.notifyItemInserted(position);
        position++;
    }
    public void removeItem(int position) {
        position=0;
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
        position--;
    }

    public void createExampleList(){

        mExampleList=new ArrayList<>();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
