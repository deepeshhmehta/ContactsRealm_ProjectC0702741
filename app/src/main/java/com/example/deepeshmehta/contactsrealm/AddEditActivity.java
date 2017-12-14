package com.example.deepeshmehta.contactsrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.deepeshmehta.contactsrealm.models.Contact;

import org.w3c.dom.Text;

import io.realm.Realm;

public class AddEditActivity extends AppCompatActivity {

    Contact current_contact;
    TextView userName, userAge, userMajor;
    SeekBar userAgeSeek;
    RadioGroup userGenderRadio;
    Spinner userMajorSpinner;
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

        userAge = (TextView) findViewById(R.id.user_age_value);
        userAge.setText(Integer.toString(current_contact.getAge()));

        userAgeSeek = findViewById(R.id.user_age_seekbar);
        userAgeSeek.setMax(200);
        userAgeSeek.setProgress(current_contact.getAge());
        userAgeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                userAge.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        userGenderRadio = (RadioGroup) findViewById(R.id.user_gender_radio);

        if(current_contact.getGender() == 1){
            userGenderRadio.check(R.id.user_gender_male);
        }else{
            userGenderRadio.check(R.id.user_gender_female);
        }


        userMajor = findViewById(R.id.user_major);
        userMajor.setText(current_contact.getMajor());

        userMajorSpinner = findViewById(R.id.user_major_spinner);

        save = findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm.getDefaultInstance().beginTransaction();
//                current_contact = Realm.getDefaultInstance().where(Contact.class).equalTo("id",user_id).findFirst();
                current_contact.setName(String.valueOf(userName.getText()));
                current_contact.setAge(Integer.parseInt(String.valueOf(userAge.getText())));
                current_contact.setMajor(String.valueOf(userMajor.getText()));

                int gen;
                switch (userGenderRadio.getCheckedRadioButtonId()){
                    case R.id.user_gender_male :{
                       gen = 1;
                       break;
                    }
                    case R.id.user_gender_female:{
                        gen = 0;
                        break;
                    }
                    default:{
                        gen = 2;
                        break;
                    }
                }
                current_contact.setGender(gen);

                if(user_id == -1){
                    Realm.getDefaultInstance().copyToRealm(current_contact);
                }
                Realm.getDefaultInstance().commitTransaction();
                finish();
            }
        });
    }
}
