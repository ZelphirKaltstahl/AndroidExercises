package com.example.xiaolong.exercises.a7;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.widget.Toast;

import com.example.xiaolong.exercises.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class a7_intents extends Activity {

    private static final String LOG_TAG = "Intents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a7_intents);
    }

    public void on_google(View view) {
        Intent open_google_intent = new Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com")
        );
        startActivity(open_google_intent);
    }

    public void on_google_with_query(View view) {
        Intent open_google_with_query_intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.de/search?q=th+wildau")
        );
        startActivity(open_google_with_query_intent);
    }

    public void on_google_maps_with_query(View view) {
        Intent open_google_maps_with_query_intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:52.318,13.631558")
        );
        startActivity(open_google_maps_with_query_intent);
    }

    public void on_show_contact(View view) {
        List<String> contact_ids = new ArrayList<>();
        List<String> contact_names = new ArrayList<>();
        List<String> contact_lookup_keys = new ArrayList<>();

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                contact_ids.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID)));
                contact_names.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                contact_lookup_keys.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)));
            }
        }

        for(int i = 0; i < contact_ids.size(); i++) {
            Log.d(a7_intents.LOG_TAG,
                "Contact Name: " + contact_names.get(i) +
                " Contact ID: " + contact_ids.get(i) +
                " LookupKey " + contact_lookup_keys.get(i)
            );
        }

        Intent show_contact_intent = new Intent(
            Intent.ACTION_VIEW,
            Uri.parse("content://contacts/people/" + contact_ids.get(1))
        );

        startActivity(show_contact_intent);
    }

    public void on_show_contact_number(View view) {
        Intent show_contact_intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("content://contacts/people/#2")
        );
        if (show_contact_intent.resolveActivity(getPackageManager()) != null) {
            startActivity(show_contact_intent);
        }
    }

//    public void list_contacts() {
//        final List<String> contact_ids = new ArrayList<>();
//        final List<String> contact_names = new ArrayList<>();
//        final List<String> contact_lookup_keys = new ArrayList<>();
//
//        ContentResolver cr = getContentResolver();
//        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//
//        if (cur.getCount() > 0) {
//            while (cur.moveToNext()) {
//                contact_ids.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID)));
//                contact_names.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
//                contact_lookup_keys.add(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)));
//            }
//        }
//    }

//    public void add_contact(View view) {
//        ContentResolver cr = getContentResolver();
//        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
//
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
//                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,"accountname@gmail.com")
//                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "com.google")
//                .build());
//
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
//                .build());
//
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE,
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
//                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
//                .build());
//
//
//        try {
//            cr.applyBatch(ContactsContract.AUTHORITY, ops);
//        } catch (OperationApplicationException ex) {
//            ex.printStackTrace();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }

    public void on_edit_contact(View view) {
        final int EDITED_CONTACT_INDEX = 1;

        List<String> contact_ids = new ArrayList<>();
        List<String> contact_names = new ArrayList<>();
        List<String> contact_lookup_keys = new ArrayList<>();

        ContentResolver content_resolver = getContentResolver();
        Cursor contacts_cursor = content_resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (contacts_cursor.getCount() > 0) {
            while (contacts_cursor.moveToNext()) {
                contact_ids.add(contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts._ID)));
                contact_names.add(contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                contact_lookup_keys.add(contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)));
            }
        }

//        if (contacts_cursor.getCount() > 0) { // if there are any contacts
//            while (contacts_cursor.moveToNext()) {
//
//                String id = contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                String name = contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//                if (Integer.parseInt(contacts_cursor.getString(contacts_cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) { // if the contact has a phone number
//
//                    Cursor phone_cursor = content_resolver.query(
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                        null,
//                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
//                        new String[]{id},
//                        null
//                    );
//
//                    while (phone_cursor.moveToNext()) {
//                        String phoneNo = phone_cursor.getString(phone_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        Toast.makeText(getApplicationContext(), "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
//                    }
//                    phone_cursor.close();
//                }
//
//            }
//        }

        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        final String WHERE = ContactsContract.Data.DISPLAY_NAME + " = ? AND " +
                ContactsContract.Data.MIMETYPE + " = ? AND " +
                String.valueOf(ContactsContract.Contacts.LOOKUP_KEY) + " = " + contact_lookup_keys.get(EDITED_CONTACT_INDEX);
        final String[] PARAMS = new String[] {
                contact_names.get(EDITED_CONTACT_INDEX),
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
                String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
        };

        ops.add(
            ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(WHERE, PARAMS)
                .withValue(Email.ADDRESS, "tohuwabohu@th.de")
                .build()
        );

//        // insert a new contact
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
//                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,"accountname@gmail.com")
//                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "com.google")
//                .build());
//
//        //
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
//                .build());
//        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                .withValue(ContactsContract.Data.MIMETYPE,
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
//                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
//                .build());

        try {
            content_resolver.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }

    }

    public void on_show_phone_number(View view) {
        final String TELEPHONE_NUMBER = "015125965178";
        Intent call_number_intent = new Intent();
        call_number_intent.setAction(Intent.ACTION_DIAL);
        call_number_intent.setData(Uri.parse("tel:" + TELEPHONE_NUMBER));

        if (call_number_intent.resolveActivity(getPackageManager()) != null) {
            startActivity(call_number_intent);
        }
    }

    public void on_call_phone_number(View view) {
        final String TELEPHONE_NUMBER = "015125965178";
        Intent call_number_intent = new Intent();
        call_number_intent.setAction(Intent.ACTION_CALL);
        call_number_intent.setData(Uri.parse("tel:" + TELEPHONE_NUMBER));

        if (call_number_intent.resolveActivity(getPackageManager()) != null) {
            startActivity(call_number_intent);
        }
    }

    public void on_set_alarm(View view) {
        Intent set_alarm_intent = new Intent();
        set_alarm_intent.setAction(AlarmClock.ACTION_SET_ALARM);
        set_alarm_intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Hello World!");
        set_alarm_intent.putExtra(AlarmClock.EXTRA_HOUR, 17);
        set_alarm_intent.putExtra(AlarmClock.EXTRA_MINUTES, 17);
        set_alarm_intent.putExtra(AlarmClock.EXTRA_SKIP_UI, false);

        if (set_alarm_intent.resolveActivity(getPackageManager()) != null) {
            startActivity(set_alarm_intent);
        }
    }

    public void on_create_calendar_event(View view) {
        // TODO
        final String TITLE = "Schachturnier";
        final String LOCATION = "TH Wildau";
        // final Calendar BEGIN_TIME = new Calendar(Time.getCurrentTimezone(), Locale.getDefault());
        final String BEGIN_TIME = "Fuenwe!";
        final String END_TIME = "Achte!";

        Intent create_calendar_event_intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, TITLE)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, LOCATION)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, BEGIN_TIME)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, END_TIME);

        if(create_calendar_event_intent.resolveActivity(getPackageManager()) != null) {
            startActivity(create_calendar_event_intent);
        }
    }

    public void on_send_email(View view) {
        // TODO
    }

    public void on_show_email(View view) {
        Intent email_intent = new Intent();

        final String ACTION = "Hello World!";
        final String ADDRESS = "hwroitzsch@gmail.com";
        final String SUBJECT = "This is a subject.";
        final String[] ADDRESSES = {ADDRESS};

        email_intent.setAction(Intent.ACTION_SEND);
        email_intent.putExtra(Intent.EXTRA_EMAIL, ADDRESSES);
        email_intent.putExtra(android.content.Intent.EXTRA_SUBJECT, SUBJECT);
        email_intent.putExtra(Intent.EXTRA_TEXT, ACTION);
        email_intent.setType("message/rfc822");
        //email_intent.setType("text/plain");

        startActivity(email_intent);
    }

    public void on_show_sms(View view) {
        final String TELEPHONE_NUMBER = "015125965178";
        String messageText = "Hi , Just SMSed to say hello";

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:" + TELEPHONE_NUMBER));
        sendIntent.putExtra("sms_body", messageText);
        startActivity(sendIntent);
    }

    public void on_send_sms(View view) {
        final String TELEPHONE_NUMBER = "015125965178";
        String messageText = "Hi , Just SMSed to say hello";

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(TELEPHONE_NUMBER, null, messageText, null, null);
    }

    public void on_inspect_other_activity(View view) {
        // TODO: ???
    }
}
