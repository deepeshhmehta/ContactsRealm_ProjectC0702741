package com.example.deepeshmehta.contactsrealm.controllers_adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deepeshmehta.contactsrealm.MainListActivity;
import com.example.deepeshmehta.contactsrealm.R;
import com.example.deepeshmehta.contactsrealm.models.Contact;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by deepeshmehta on 2017-12-13.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {

    private Context context;
    private List<Contact> contacts;
    private OnUserItemClicked onUserItemClicked;
    private ShowDeleteDialogueData showDeleteDialogueData;

    public ContactListAdapter(Context context, RealmResults<Contact> contacts, OnUserItemClicked onUserItemClicked, ShowDeleteDialogueData showDeleteDialogueData) {
        this.context = context;
        this.contacts = contacts;
        this.onUserItemClicked = onUserItemClicked;
        this.showDeleteDialogueData = showDeleteDialogueData;
    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_list, parent, false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, final int position) {
        holder.contact = contacts.get(position);
        holder.txtContactName.setText(holder.contact.getName());
        holder.txtContactName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserItemClicked.onUserItemClicked(contacts.get(position));
            }
        });
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDeleteDialogueData.showDeleteDialog(contacts.get(position));
                contacts = Realm.getDefaultInstance().where(Contact.class).findAll();
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactListViewHolder extends RecyclerView.ViewHolder{
        TextView txtContactName;
        Contact contact;
        Button delete_button;

        public ContactListViewHolder(View itemView) {
            super(itemView);
             txtContactName = itemView.findViewById(R.id.contact_name);
             delete_button = itemView.findViewById(R.id.delete_button);
        }
    }


    public interface OnUserItemClicked{
        void onUserItemClicked(Contact user);
    }

    public interface ShowDeleteDialogueData{
        public void showDeleteDialog(Contact con) throws Resources.NotFoundException;
    }
}
