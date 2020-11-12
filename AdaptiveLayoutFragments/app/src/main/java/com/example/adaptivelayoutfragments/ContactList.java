package com.example.adaptivelayoutfragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class ContactList {

    private ArrayList<Contact> contacts;

    public ContactList() {
        contacts.add(new Contact("Giuseppe1", "3460262828-1", "g.cardaropoli1@gmai.com", "via pippo 1"));
        contacts.add(new Contact("Giuseppe2", "3460262828-2", "g.cardaropoli2@gmai.com", "via pippo 2"));
        contacts.add(new Contact("Giuseppe3", "3460262828-3", "g.cardaropoli3@gmai.com", "via pippo 3"));
        contacts.add(new Contact("Giuseppe4", "3460262828-4", "g.cardaropoli4@gmai.com", "via pippo 4"));
        contacts.add(new Contact("Giuseppe5", "3460262828-5", "g.cardaropoli5@gmai.com", "via pippo 5"));
        contacts.add(new Contact("Giuseppe6", "3460262828-6", "g.cardaropoli6@gmai.com", "via pippo 6"));
        contacts.add(new Contact("Giuseppe7", "3460262828-7", "g.cardaropoli7@gmai.com", "via pippo 7"));
        contacts.add(new Contact("Giuseppe8", "3460262828-8", "g.cardaropoli8@gmai.com", "via pippo 8"));
        contacts.add(new Contact("Giuseppe9", "3460262828-9", "g.cardaropoli9@gmai.com", "via pippo 9"));
        contacts.add(new Contact("Giuseppe10", "3460262828-10", "g.cardaropoli10@gmai.com", "via pippo 10"));
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
