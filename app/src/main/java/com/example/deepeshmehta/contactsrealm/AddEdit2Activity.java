package com.example.deepeshmehta.contactsrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deepeshmehta.contactsrealm.models.Contact;

import io.realm.Realm;

public class AddEdit2Activity extends AppCompatActivity {

    Contact current_contact;
    TextView userName, userAge,userGender, userMajor;
    String user_id;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit2);

        Log.d("Tag","addeditactivity");

        this.user_id = (getIntent().getStringExtra("user_id"));

        if(Integer.parseInt(user_id) == 0){
            Log.d("id","newContact");
            current_contact = new Contact();
        }else{
            Log.d("id",this.user_id);
            current_contact = Realm.getDefaultInstance().where(Contact.class).equalTo("id",1).findFirst();
        }

        userName = findViewById(R.id.user_name);
        userName.setText(current_contact.getName());

        userAge = findViewById(R.id.user_age);
        userAge.setText(Integer.toString(current_contact.getAge()));

        userGender = findViewById(R.id.user_gender);
        userGender.setText(current_contact.getGender());

        userMajor = findViewById(R.id.user_major);
        userMajor.setText(current_contact.getMajor());

        save = findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm.getDefaultInstance().beginTransaction();
                if(Integer.parseInt(user_id) == 0){
                    Realm.getDefaultInstance().copyToRealm(current_contact);
                }
                current_contact.setName(String.valueOf(userName.getText()));
                current_contact.setGender(String.valueOf(userAge.getText()));
                current_contact.setAge(Integer.parseInt(String.valueOf(userAge.getText())));
                current_contact.setMajor(String.valueOf(userAge.getText()));
                Realm.getDefaultInstance().commitTransaction();
                finish();
            }
        });
    }
}
