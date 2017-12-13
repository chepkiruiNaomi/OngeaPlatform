package com.dasayantist.ongeaplatform;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.Counsellor;


public class AddCounsellorActivity extends AppCompatActivity {
    EditText inputNames, inputPhone, inputLocation, inputArea;
    Button buttonAdd, buttonView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counsellor);
        inputNames= (EditText) findViewById(R.id.inputNames);
        inputPhone= (EditText) findViewById(R.id.inputPhone);
        inputArea= (EditText) findViewById(R.id.inputSpec);
        inputLocation= (EditText) findViewById(R.id.inputLocation);
        buttonAdd= (Button) findViewById(R.id.buttonAdd);
        buttonView= (Button) findViewById(R.id.buttonView);
        progressDialog=new ProgressDialog(this);

        progressDialog.setMessage("Saving ......");
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCounsellorActivity.this, CounsellorActivity.class));
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names=inputNames.getText().toString().trim();
                String phone=inputPhone.getText().toString().trim();
                String location=inputLocation.getText().toString().trim();
                String area=inputArea.getText().toString().trim();
                if (names.isEmpty()|| phone.isEmpty()|| location.isEmpty()|| area.isEmpty()){
                    Toast.makeText(AddCounsellorActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.show();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("counsellors").child(""+System.currentTimeMillis());
                Counsellor counsellor=new Counsellor(names,phone,location,area);
                ref.setValue(counsellor).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            inputNames.setText("");
                            inputPhone.setText("");
                            inputLocation.setText("");
                            inputArea.setText("");
                            Toast.makeText(AddCounsellorActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
