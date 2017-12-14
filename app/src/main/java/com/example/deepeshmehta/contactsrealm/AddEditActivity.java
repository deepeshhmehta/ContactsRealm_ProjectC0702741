package com.example.deepeshmehta.contactsrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deepeshmehta.contactsrealm.models.Contact;

import io.realm.Realm;

public class AddEditActivity extends AppCompatActivity {

    Contact current_contact;
    TextView userName, userAge,userGender, userMajor;
    int user_id;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        this.user_id = Integer.parseInt(getIntent().getStringExtra("user_id"));
        Log.d("Tag","addeditactivity for " + user_id);

        current_contact = Realm.getDefaultInstance().where(Contact.class).equalTo("id",user_id).findFirst();
        if(user_id == -1) {
            Log.d("new","contact created");
            current_contact = new Contact();
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
//                current_contact = Realm.getDefaultInstance().where(Contact.class).equalTo("id",user_id).findFirst();
                current_contact.setName(String.valueOf(userName.getText()));
                current_contact.setGender(String.valueOf(userGender.getText()));
                current_contact.setAge(Integer.parseInt(String.valueOf(userAge.getText())));
                current_contact.setMajor(String.valueOf(userMajor.getText()));
                if(user_id == -1){
                    Realm.getDefaultInstance().copyToRealm(current_contact);
                }
                Realm.getDefaultInstance().commitTransaction();
                finish();
            }
        });
    }
}
