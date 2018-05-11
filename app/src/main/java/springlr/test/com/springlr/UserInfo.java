package springlr.test.com.springlr;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserInfo extends AppCompatActivity {

    private TextInputLayout mname;
    private TextInputLayout mmail;
    private TextInputLayout mmobile;
    private Button msubmit;
    private DatabaseReference databaseReference;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mname = (TextInputLayout) findViewById(R.id.info_name);
        mmail = (TextInputLayout) findViewById(R.id.info_mail);
        mmobile = (TextInputLayout) findViewById(R.id.info_mobile);
        msubmit = (Button) findViewById(R.id.info_submit);
        toolbar = (Toolbar) findViewById(R.id.info_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Basic Details");
        mAuth = FirebaseAuth.getInstance();
        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = mname.getEditText().getText().toString();
                String mail = mmail.getEditText().getText().toString();
                String mobile = mmobile.getEditText().getText().toString();
                String uid = FirebaseAuth.getInstance().getUid();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(mobile)){
                    Toast.makeText(UserInfo.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("mail", mail);
                    userMap.put("mobile", mobile);
                    databaseReference.setValue(userMap);
                    Intent mainIntent = new Intent(UserInfo.this, MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                }
            }
        });
    }

}
