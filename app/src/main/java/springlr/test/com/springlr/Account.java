package springlr.test.com.springlr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseReference databaseReference;
    private TextView mname;
    private TextView mmail;
    private TextView mphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mname = (TextView) findViewById(R.id.acc_name_dynamic);
        mmail = (TextView) findViewById(R.id.acc_mail_dynamic);
        mphone = (TextView) findViewById(R.id.acc_phone_dynamic);
        toolbar = (Toolbar) findViewById(R.id.account_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Acount Details");
        String uid = FirebaseAuth.getInstance().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String mail = dataSnapshot.child("mail").getValue().toString();
                String phone = dataSnapshot.child("mobile").getValue().toString();
                mname.setText(name);
                mmail.setText(mail);
                mphone.setText(phone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
