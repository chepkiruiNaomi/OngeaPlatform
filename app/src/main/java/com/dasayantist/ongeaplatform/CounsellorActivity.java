package com.dasayantist.ongeaplatform;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Map;

import adapters.CustomListAdapter;
import models.Counsellor;

public class CounsellorActivity extends AppCompatActivity {
    ListView list;
    CustomListAdapter adapter;
    ArrayList<Counsellor> data;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellor);
        list= (ListView) findViewById(R.id.counsellorsList);
        data=new ArrayList<>();
        adapter=new CustomListAdapter(this,data);
        list.setAdapter(adapter);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading ....");
        progressDialog.show();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("counsellors");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item:dataSnapshot.getChildren()){
                    Map<String, Object> map= (Map<String, Object>) item.getValue();
                    Counsellor counsellor=new Counsellor(map.get("name").toString(),map.get("phone").toString(),map.get("location").toString(),map.get("area").toString());
                    data.add(counsellor);
                }
                adapter.notifyDataSetChanged();
                adapter.refresh(data);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_counsllors, menu);
        SearchView searchView =(SearchView) menu.findItem(R.id.menu_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SEARCH", newText);
                adapter.filter(newText);
                return false;
            }
        });
        return  true;

    }
}