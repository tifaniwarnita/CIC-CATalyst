package com.tifaniwarnita.ciccatalyst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.tifaniwarnita.ciccatalyst.controllers.PreferencesController;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ReservasiFragment.ReservationFragmentListener,
        DetailReservasiFragment.ReservationDetailFragmentListener,
        TambahReservasiDialogFragment.TambahReservasiDialogFragmentListener{

    private NavigationView navigationView;
    static final int REQUEST_TOKEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // TODO: Tulisan Hi! di nav drawer diganti sama Hi, <<NAMA>>! kalau udah login
        // TODO: Hilangin menu Login di nav drawer kalo pengguna udah login
        if (PreferencesController.getName(getApplicationContext()) != null) {
            navigationView.getMenu().getItem(1).setVisible(false).setEnabled(false);
            View headerView = navigationView.getHeaderView(0);
            TextView hiTextView = (TextView) headerView.findViewById(R.id.text_view_hi);
            String fullName = PreferencesController.getName(getApplicationContext());
            String[] name = fullName.split("\\s+");
            hiTextView.setText("Hi, " + name[0] + "!");
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, new BerandaFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                navigationView.getMenu().getItem(0).setChecked(true);
                //additional code
            } else {
                getSupportFragmentManager().popBackStack();
                if (count == 1) {
                    navigationView.getMenu().getItem(0).setChecked(true);
                } else {
                    FragmentManager.BackStackEntry backEntry =
                            getSupportFragmentManager().getBackStackEntryAt(
                                    getSupportFragmentManager().getBackStackEntryCount()-2);
                    String tag = backEntry.getName();
                    Log.d("TAG BACKSTACK: ", tag);
                    if (tag.equals(getResources().getString(R.string.event))) {
                        navigationView.getMenu().getItem(2).setChecked(true);
                    } else if (tag.equals(getResources().getString(R.string.our_cats))) {
                        navigationView.getMenu().getItem(3).setChecked(true);
                    } else if (tag.equals(getResources().getString(R.string.our_menu))) {
                        navigationView.getMenu().getItem(4).setChecked(true);
                    } else if (tag.equals(getResources().getString(R.string.reservation))) {
                        navigationView.getMenu().getItem(5).setChecked(true);
                    } else if (tag.equals(getResources().getString(R.string.contacts))) {
                        navigationView.getMenu().getItem(6).setChecked(true);
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // TODO: change page
        if (id == R.id.nav_home) {
            // Clear back stack
            FragmentManager fm = getSupportFragmentManager();
            /* for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            } */
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new BerandaFragment())
                    .commit();
        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivity.this, AutentikasiActivity.class);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            startActivityForResult(intent, REQUEST_TOKEN);
        } else if (id == R.id.nav_event) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new PromosiFragment())
                    .addToBackStack(getResources().getString(R.string.event))
                    .commit();
        } else if (id == R.id.nav_our_cats) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new DaftarKucingFragment())
                    .addToBackStack(getResources().getString(R.string.our_cats))
                    .commit();
        } else if (id == R.id.nav_our_menu) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new DaftarMenuFragment())
                    .addToBackStack(getResources().getString(R.string.our_menu))
                    .commit();
        } else if (id == R.id.nav_reservation) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new ReservasiFragment())
                    .addToBackStack(getResources().getString(R.string.reservation))
                    .commit();
        } else if (id == R.id.nav_contacts) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new KontakFragment())
                    .addToBackStack(getResources().getString(R.string.contacts))
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TOKEN) {
            // Make sure the request was successful
            navigationView.getMenu().getItem(0).setChecked(true);

            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
        }
    }

    @Override
    public void onSelectDate(Date date) {
        FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, DetailReservasiFragment.newInstance(
                            DetailReservasiFragment.convertDateToString(date)
                    ))
                    .addToBackStack(getResources().getString(R.string.reservation_detail))
                    .commit();
    }

    @Override
    public void onReservasiButtonClick(Date date) {
        TambahReservasiDialogFragment tambahReservasiDialog = TambahReservasiDialogFragment.newInstance(
                DetailReservasiFragment.convertDateToString(date)
        );
        tambahReservasiDialog.show(getSupportFragmentManager(), null);
    }
}
