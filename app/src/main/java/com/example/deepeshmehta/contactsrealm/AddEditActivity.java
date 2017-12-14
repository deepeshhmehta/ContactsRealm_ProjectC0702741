package com.example.deepeshmehta.contactsrealm;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deepeshmehta.contactsrealm.models.Contact;

import io.realm.Realm;

/**
 * Created by deepeshmehta on 2017-12-13.
 */

public class AddEditActivity extends AppCompatActivity {
    Contact current_contact;
    TextView userName, userAge,userGender, userMajor;
    int user_id;
    Button save;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.d("Tag","addeditactivity");
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_add_edit);

        this.user_id = Integer.parseInt(getIntent().getStringExtra("user_id"));
        if(user_id != 0){
            current_contact = Realm.getDefaultInstance().where(Contact.class).equalTo("id",1).findFirst();
        }else{
            current_contact = new Contact();
        }

//        userName = findViewById(R.id.user_name);
//        userName.setText(current_contact.getName());
//
//        userAge = findViewById(R.id.user_age);
//        userAge.setText(current_contact.getAge());
//
//        userGender = findViewById(R.id.user_gender);
//        userGender.setText(current_contact.getGender());
//
//        userMajor = findViewById(R.id.user_major);
//        userMajor.setText(current_contact.getMajor());

//        save = findViewById(R.id.save_button);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Realm.getDefaultInstance().beginTransaction();
//                current_contact.setName((String) userName.getText());
//                Realm.getDefaultInstance().commitTransaction();
//            }
//        });
    }
}
