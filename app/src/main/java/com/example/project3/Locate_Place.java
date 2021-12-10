package com.example.project3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.IOException;
import java.util.List;

public class Locate_Place extends AppCompatActivity
     implements OnMapReadyCallback,
     BottomNavigationView.OnNavigationItemSelectedListener

{
    private BottomNavigationView nav;

    GoogleMap map;
    SupportMapFragment supMap;
    SearchView search;

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
        Log.w ("LOCATE_PLACEActivity", "onSupportNavigateUp is calll");
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
        Log.w ("LOCATE_PLACEActivity", "this onbackpress is calll");

        AlertDialog.Builder   x= new AlertDialog.Builder ( this );
        x.setMessage ( "DO YOU WANT TO LEAVE " ).setTitle ( "leave LOCATE_PLACEActivity" )

                .setPositiveButton ( "YES_LEAVE", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.w ("LOCATE_PLACEActivity", "end");
                        Toast.makeText(getApplicationContext(), "BACK FROM LOCATE_PLACEActivity...", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_locate__place);

        Log.w ("LOCATE_PLACEActivity", "start");
        Toast.makeText(getApplicationContext(), "WELCOME...LOCATE_PLACEActivity", Toast.LENGTH_SHORT).show();

        ActionBar bar = getSupportActionBar ();
        bar.setHomeButtonEnabled ( true );
        bar.setDisplayHomeAsUpEnabled ( true );
        bar.setTitle("LOCATE_PLACEActivity");
        bar.setHomeAsUpIndicator ( R.drawable.pt3);

        nav = (BottomNavigationView)findViewById ( R.id.nb );
        nav.setItemIconTintList ( null );
        nav.setOnNavigationItemSelectedListener (this);
        clearSelection(nav);
        final BottomNavigationMenuView menuView;
        menuView = (BottomNavigationMenuView) nav.getChildAt(0);
        BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(2);
        item.setChecked(true);

        search = findViewById(R.id.sv_location);
        supMap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        Toast.makeText ( this," please enter the place name(عربي | English) such as Nablus-نابلس-, Jenin-جنين-, Ramallah-رام الله-,An-Najah National University-جامعة النجاح الوطنية-, Palestine-فلسطين-, Jordan-الاردن-, Turkey-تركيا-  etc.... , please press Search in keyboard to locate a place " , Toast.LENGTH_LONG ).show();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                try {
                    String loc = search.getQuery().toString();
                    List<Address> addressList = null;

                    if (loc != null || !loc.equals("")) {
                        Geocoder geocoder = new Geocoder(Locate_Place.this);
                        try {
                            addressList = geocoder.getFromLocationName(loc, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        map.addMarker(new MarkerOptions().position(latLng).title(loc));
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    }
                }
                catch(Exception e) {
                    search.setQuery("",false);
                    Toast.makeText(getApplicationContext(), "Error locationٌٌُ ,please enter the place name(عربي | English) such as Nablus-نابلس-, Jenin-جنين-, Ramallah-رام الله-,An-Najah National University-جامعة النجاح الوطنية-, Palestine-فلسطين-, Jordan-الاردن-, Turkey-تركيا-  etc.... , please press Search in keyboard to locate a place ", Toast.LENGTH_LONG).show();
                    return false;
                    //Toast.makeText (  Toast.makeText(getApplicationContext()," please enter the place name(عربي | English) such as Nablus-نابلس-, Jenin-جنين-, Ramallah-رام الله-,An-Najah National University-جامعة النجاح الوطنية-, Palestine-فلسطين-, Jordan-الاردن-, Turkey-تركيا-  etc....  " , Toast.LENGTH_LONG ).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        supMap.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
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
            Intent  i  = new Intent( this, Email.class);
            startActivity ( i );
        }

        if (item.getItemId ()==R.id.it3) {
            Log.w ("onNavigationItemSelected", "3");
           // Intent  i  = new Intent( this, Locate_Place.class);
           // startActivity ( i );
        }

        if (item.getItemId ()==R.id.it4) {
            Log.w ("onNavigationItemSelected", "4");
            Intent  i  = new Intent( this, Take_a_picture.class);
            startActivity ( i );
        }
        Log.w ("onNavigationItemSelected", "down");
        return false;
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

}