package edu.ucsb.cs.cs184.asoto00;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployerProfile extends AppCompatActivity implements View.OnClickListener {

    private TextView CEName;
    private TextView CECompany;
    private TextView CEPhone;
    private Button SignOutButton2;
    protected RecyclerView recyclerView;
    protected StudentListAdapter mAdapter;
    protected ArrayList<String> allStudents;
    protected RecyclerView.LayoutManager mLayoutManger;
    protected FloatingActionButton addStudentButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private EmployerUser employerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile);
        initDataSet();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        if(user == null){
            startActivity( new Intent(this, Sign_In.class));
            finish();
        }else{
            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    employerUser = dataSnapshot.getValue(EmployerUser.class);
                    updateEmployerInfo();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        recyclerView = findViewById(R.id.recyclerView);

        mAdapter = new StudentListAdapter(allStudents);
        mLayoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManger);
        recyclerView.setAdapter(mAdapter);
        addStudentButton = findViewById(R.id.addStudentButton);
        addStudentButton.setOnClickListener(this);
        CEName = (TextView) findViewById(R.id.CEName);
        CECompany = (TextView) findViewById(R.id.CECompany);
        CEPhone = (TextView) findViewById(R.id.CEPhone);

        SignOutButton2 = (Button) findViewById(R.id.SignOutButton2);

        SignOutButton2.setOnClickListener(this);


    }

    private void initDataSet() {
        allStudents = new ArrayList<>();
        allStudents.add("Yessenia Valencia");
        allStudents.add("Alex Soto");
        allStudents.add("Arturo Something");

    }

    public void updateEmployerInfo(){
        CEName.setText(employerUser.Name);
        CECompany.setText(employerUser.Company);
        CEPhone.setText(employerUser.PhoneNumber);

    }

    @Override
    public void onClick(View v) {
        if( v == SignOutButton2){
            firebaseAuth.signOut();

            startActivity(new Intent(this, Sign_In.class));
            finish();
        }
        if(v == addStudentButton) {
            startActivity(new Intent(this, ScannerActivity.class));
            finish();
        }

    }
}
