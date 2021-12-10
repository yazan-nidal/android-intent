package com.example.project3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.StringTokenizer;

public class SMS extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener
{

    private BottomNavigationView nav;

    private EditText phone;
    private EditText mess;

    private Button clear;
    private Button send;

    private static final int smsCode= 11;

    //
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inf=getMenuInflater ();
        inf.inflate ( R.menu.menu2 ,menu);
        if (menu!=null && menu instanceof MenuBuilder)
            ((MenuBuilder)menu).setOptionalIconsVisible ( true );
        return super.onCreateOptionsMenu ( menu );
    }
    //
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) { return super.onPrepareOptionsMenu ( menu ); }
    //
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) { return super.onMenuOpened ( featureId, menu ); }
    //
    @Override
    public void onOptionsMenuClosed(Menu menu) { super.onOptionsMenuClosed ( menu ); }
    //
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        //getSupportActionBar ().setTitle ( item.getTitle ()+ "  is pressed" );
        if (item.getItemId ()==R.id.ex) { onBackPressed(); }
        return super.onOptionsItemSelected ( item );
    }
    //
    @Override
    public boolean onSupportNavigateUp()
    {
        Log.w ("SMSActivity", "onSupportNavigateUp is calll");
        onBackPressed ();
        return super.onSupportNavigateUp ();
    }
    //
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return super.onKeyDown(keyCode, event);
    }
    //
    @Override
    public void onBackPressed()
    {
        //super.onBackPressed ();
        Log.w ("SMSActivity", "this onbackpress is calll");

        AlertDialog.Builder   x= new AlertDialog.Builder ( this );
        x.setMessage ( "DO YOU WANT TO LEAVE " ).setTitle ( "leave SMSActivity" )

                .setPositiveButton ( "YES_LEAVE", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.w ("SMSActivity", "end");
                        Toast.makeText(getApplicationContext(), "BACK FROM SMSActivity...", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } )

                .setNegativeButton ( "CANCEL", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                })

                .setIcon(R.drawable.qus)
                .setPositiveButtonIcon (getDrawable ( R.drawable.yes))
                .setNegativeButtonIcon(getDrawable ( R.drawable.no))
                .show ();
        return;
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s);

        Log.w ("SMSActivity", "start");
        Toast.makeText(getApplicationContext(), "WELCOME...SMSActivity", Toast.LENGTH_SHORT).show();

        ActionBar bar = getSupportActionBar ();
        bar.setHomeButtonEnabled ( true );
        bar.setDisplayHomeAsUpEnabled ( true );
        bar.setTitle("SMSActivity");
        bar.setHomeAsUpIndicator ( R.drawable.pt1);

        nav = (BottomNavigationView)findViewById ( R.id.nb );
        nav.setItemIconTintList ( null );
        nav.setOnNavigationItemSelectedListener (this);
        clearSelection(nav);
        final BottomNavigationMenuView menuView;
        menuView = (BottomNavigationMenuView) nav.getChildAt(0);
        BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(0);
        item.setChecked(true);

        phone = findViewById (R.id.SMSphone);
        phone.setKeyListener(DigitsKeyListener.getInstance("0123456789,"));
        phone.setOnKeyListener ( new View.OnKeyListener () {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(keyCode == event.KEYCODE_DEL){ return false;}
                if (keyCode == KeyEvent.KEYCODE_BACK){return false;}
                if (keyCode == KeyEvent.KEYCODE_ENTER) {return false;}
                if (keyCode == KeyEvent.KEYCODE_COMMA) {return false;}

                     return !((keyCode >=event.KEYCODE_0) && (keyCode <=event.KEYCODE_9) );
            }
        } );

        mess = findViewById (R.id.SMSmess);
        clear = (Button)findViewById (R.id.SMSclear);
        send = (Button)findViewById (R.id.SMSsend);

            requestPermissions (new String[]{Manifest.permission.SEND_SMS} ,smsCode);

        clear.setOnClickListener ( this );
        send.setOnClickListener ( this );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.w ("onNavigationItemSelected", "up");
        if (item.getItemId ()==R.id.it1) {
            Log.w ("onNavigationItemSelected", "1");
           // Intent i  = new Intent( this, SMS.class);
          //  startActivity ( i );
        }

        if (item.getItemId ()==R.id.it2) {
            Log.w ("onNavigationItemSelected", "2");
            Intent  i  = new Intent( this, Email.class);
            startActivity ( i );
        }

        if (item.getItemId ()==R.id.it3) {
            Log.w ("onNavigationItemSelected", "3");
            Intent  i  = new Intent( this, Locate_Place.class);
            startActivity ( i );
        }

        if (item.getItemId ()==R.id.it4) {
            Log.w ("onNavigationItemSelected", "4");
            Intent  i  = new Intent( this, Take_a_picture.class);
            startActivity ( i );
        }
        Log.w ("onNavigationItemSelected", "down");
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==smsCode)
        {

            if (grantResults.length > 0  && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && permissions[0].equalsIgnoreCase ( Manifest.permission.SEND_SMS )) { }
            else {  /*requestPermissions (new String[]{Manifest.permission.SEND_SMS} ,smsCode);

               Toast.makeText(getApplicationContext(),
                    "please accept for sms send PERMISSION",
                    Toast.LENGTH_SHORT).show(); */
            }
        }

        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
    }

    public void smsSendMessage(String P,String M) {

        SmsManager  xx= SmsManager.getDefault ();

        if (checkSelfPermission ( Manifest.permission.SEND_SMS )==PackageManager.PERMISSION_DENIED){
            requestPermissions (new String[]{Manifest.permission.SEND_SMS} ,smsCode);
        }
        else {
            xx.sendTextMessage(P, null,
                    M, null, null);
            Toast.makeText(getApplicationContext(),
                    "done send message for tel:"+P,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.SMSclear) {phone.setText(""); mess.setText("");  return; }
        if (v.getId() == R.id.SMSsend) {

            String sms = mess.getText().toString();
            if(sms.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Empty message, please enter message", Toast.LENGTH_LONG).show();
                return;
            }
            String phoneNom = phone.getText().toString();
            if(phoneNom.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "no phone numbers, please enter  phone numbers", Toast.LENGTH_LONG).show();
                return;
            }

            if (checkSelfPermission ( Manifest.permission.SEND_SMS )==PackageManager.PERMISSION_DENIED){
                Toast.makeText(getApplicationContext(),
                        "please accept for sms send PERMISSION from settings , app , PERMISSION,sms accept",
                        Toast.LENGTH_SHORT).show();
                return;

            }

            StringTokenizer st=new StringTokenizer(phoneNom,",");
            while (st.hasMoreElements())
            {
                String tempMobileNumber = (String)st.nextElement();
                if(tempMobileNumber.length()>0 && sms.trim().length()>0) {
                    smsSendMessage(tempMobileNumber,sms);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Please enter both phone number and message.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            Toast.makeText(getApplicationContext(),
                    "finish send.",
                    Toast.LENGTH_SHORT).show();

            return; }
    }

    //
    @SuppressLint("RestrictedApi")
    public static void clearSelection(BottomNavigationView view)
    {
        final BottomNavigationMenuView menuView;
        menuView = (BottomNavigationMenuView) view.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++)
        {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            item.setChecked(false);
        }
    }
    //

    // <!-- Clear focus on touch outside for all EditText inputs. "Clear focus input"
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

// "Clear focus input" -->

}