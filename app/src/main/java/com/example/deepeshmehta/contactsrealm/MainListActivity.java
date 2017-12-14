package com.example.deepeshmehta.contactsrealm;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.deepeshmehta.contactsrealm.controllers_adapters.ContactListAdapter;
import com.example.deepeshmehta.contactsrealm.models.Contact;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainListActivity extends AppCompatActivity implements ContactListAdapter.OnUserItemClicked , ContactListAdapter.ShowDeleteDialogueData {
    RecyclerView contacts_list;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        add_button = (Button) findViewById(R.id.add_button);
        contacts_list = findViewById(R.id.contact_list_recycler_view);
        contacts_list.setLayoutManager(new LinearLayoutManager(this));


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainListActivity.this, AddEditActivity.class);
                intent.putExtra("user_id", "-1");
                startActivity(intent);
            }
        });

        //Test
//        Contact temp = new Contact("Deepu",22,"Sci","male");
//        Realm.getDefaultInstance().beginTransaction();
//        Realm.getDefaultInstance().copyToRealm(temp);
//        Realm.getDefaultInstance().commitTransaction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag","resumed");
        contacts_list.setAdapter(new ContactListAdapter(this, Realm.getDefaultInstance().where(Contact.class).findAll(),MainListActivity.this, MainListActivity.this));
    }

    @Override
    public void onUserItemClicked(Contact user) {
//        Log.d(TAG, "onUserItemClicked: " + user.getDisplayName());

        Intent intent = new Intent(this, AddEditActivity.class);
        intent.putExtra("user_id",  Integer.toString(user.getId()));
        startActivity(intent);
    }

    public void showDeleteDialog(final Contact contact) throws Resources.NotFoundException {
        new AlertDialog.Builder(this)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete " + contact.getName())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Realm realm = Realm.getDefaultInstance();
                                realm.beginTransaction();
                                RealmResults<Contact> result = realm.where(Contact.class).equalTo("id",contact.getId()).findAll();
                                result.deleteAllFromRealm();
                                realm.commitTransaction();
                                onResume();

                            }
                        })
                .setNegativeButton(
                        "NO",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //Do Something Here
                            }
                        }).show();
    }
}
