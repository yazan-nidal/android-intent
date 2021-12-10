package com.example.project3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Email extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener
{
    private BottomNavigationView nav;

    private Button clear;
    private Button send;

    private EditText sub;
    private EditText Email;
    private EditText Ecc;
    private EditText Ebcc;
    private EditText mess;

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
        Log.w ("EMAILActivity", "onSupportNavigateUp is calll");
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
        Log.w ("EMAILActivity", "this onbackpress is calll");

        AlertDialog.Builder   x= new AlertDialog.Builder ( this );
        x.setMessage ( "DO YOU WANT TO LEAVE " ).setTitle ( "leave EMAILActivity" )

                .setPositiveButton ( "YES_LEAVE", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.w ("EMAILActivity", "end");
                        Toast.makeText(getApplicationContext(), "BACK FROM EMAILActivity...", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_email);

        Log.w ("EMAILActivity", "start");
        Toast.makeText(getApplicationContext(), "WELCOME...EMAILActivity", Toast.LENGTH_SHORT).show();

        ActionBar bar = getSupportActionBar ();
        bar.setHomeButtonEnabled ( true );
        bar.setDisplayHomeAsUpEnabled ( true );
        bar.setTitle("EMAILActivity");
        bar.setHomeAsUpIndicator ( R.drawable.pt2);

        nav = (BottomNavigationView)findViewById ( R.id.nb );
        nav.setItemIconTintList ( null );
        nav.setOnNavigationItemSelectedListener (this);
        clearSelection(nav);
        final BottomNavigationMenuView menuView;
        menuView = (BottomNavigationMenuView) nav.getChildAt(0);
        BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(1);
        item.setChecked(true);

        sub = findViewById (R.id.EMAILsubject);
        Email = findViewById (R.id.EMAILemail);
        Ecc = findViewById (R.id.EMAILemail2);
        Ebcc = findViewById (R.id.EMAILemail3);
        mess = findViewById (R.id.EMAILmess);

        clear = (Button)findViewById (R.id.EMAILclear);
        send = (Button)findViewById (R.id.EMAILsend);

        clear.setOnClickListener ( this );
        send.setOnClickListener ( this );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.w ("onNavigationItemSelected", "up");
        if (item.getItemId ()==R.id.it1) {
            Log.w ("onNavigationItemSelected", "1");
            Intent i  = new Intent( this, SMS.class);
            startActivity ( i );
        }

        if (item.getItemId ()==R.id.it2) {
            Log.w ("onNavigationItemSelected", "2");
           // Intent i  = new Intent( this, Email.class);
          //  startActivity ( i );
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

    String[] f(StringTokenizer st)
    {
        ArrayList<String> ste= new ArrayList<String>();
        while (st.hasMoreElements()) { ste.add((String)st.nextElement()); }
        String[] se = new String[ste.size()];
       se = ste.toArray(se);
        /*for( sString s : se)
            System.out.println(s);*/
        return se;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.EMAILclear) {
           sub.setText(""); Email.setText(""); Ecc.setText(""); Ebcc.setText(""); mess.setText("");
           return;
        }

        if (v.getId() == R.id.EMAILsend)
        {
            String text = mess.getText().toString();
            if(text.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Empty message, please enter message", Toast.LENGTH_LONG).show();
                return;
            }

            String subb=sub.getText().toString();
            if(subb.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Empty Subject,the default value : null", Toast.LENGTH_LONG).show();
            }


            String e1 = Email.getText().toString();
            String e2 = Ecc.getText().toString();
            String e3 = Ebcc.getText().toString();

            if(e1.isEmpty() && e2.isEmpty() && e3.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Empty Email, please enter Emai(TO | CC | BCC)", Toast.LENGTH_LONG).show();
                return;
            }


            String []TO;
            if(e1.isEmpty())
            { TO=new String[]{""}; }
            else
            {
                StringTokenizer st=new StringTokenizer(e1,",");
                TO=f(st);
            }

            String []CC;
            if(e2.isEmpty())
            { CC=new String[]{""}; }
            else
            {
                StringTokenizer st=new StringTokenizer(e2,",");
                CC=f(st);
            }
            String []BCC;
            if(e3.isEmpty())
            { BCC=new String[]{""}; }
            else
            {
                StringTokenizer st=new StringTokenizer(e3,",");
                BCC=f(st);
            }

            Intent    i = new Intent ( Intent.ACTION_SENDTO )  ; //Intent    i = new Intent ( Intent.ACTION_SEND )  ;
            i.setData ( Uri.parse ( "mailto:" ) );
            i.putExtra(Intent.EXTRA_EMAIL,TO);
            i.putExtra ( Intent.EXTRA_SUBJECT,subb);
            i.putExtra ( Intent.EXTRA_CC,CC);
            i.putExtra ( Intent.EXTRA_BCC, BCC );
            i.putExtra ( Intent.EXTRA_TEXT,text );
           // i.setType("message/rfc822"); //for Intent.ACTION_SEND
            startActivity(i);
          //startActivity(Intent.createChooser(i, "Choose an Email client :"));
        }

        return;
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