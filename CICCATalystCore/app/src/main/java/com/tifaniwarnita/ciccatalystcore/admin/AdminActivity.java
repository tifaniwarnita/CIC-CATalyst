package com.tifaniwarnita.ciccatalystcore.admin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.tifaniwarnita.ciccatalystcore.AutentikasiActivity;
import com.tifaniwarnita.ciccatalystcore.PilihanAksesFragment;
import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.kasir.ReservasiFragment;
import com.tifaniwarnita.ciccatalystcore.kasir.TambahReservasiDialogFragment;
import com.tifaniwarnita.ciccatalystcore.model.Event;

public class AdminActivity extends AppCompatActivity  implements
        ActionBar.TabListener, SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener,
        TambahEventDialogFragment.TambahEventDialogFragmentListener {

    private int actionBarActiveIndex = 0;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private FloatingActionButton fab;
    private PromosiFragment promosiFragment = new PromosiFragment();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        createTab(0, getResources().getDrawable(R.drawable.icon_statistik));
        createTab(1, getResources().getDrawable(R.drawable.icon_promosi));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (actionBarActiveIndex) {
                    case 0:
                        // Statistik do nothing
                        break;
                    case 1:
                        //TODO: TAMBAH EVENT
                        TambahEventDialogFragment tambahPelangganDialogFragment = new TambahEventDialogFragment();
                        tambahPelangganDialogFragment.show(getSupportFragmentManager(), null);
                        break;
                }
            }
        });

        String application_id = getResources().getString(R.string.application_id);
        String secret_key = getResources().getString(R.string.secret_key);
        Backendless.initApp(this, application_id, secret_key, "v1");
    }

    public void createTab(int position, Drawable imageId) {
        ActionBar.Tab tab = getSupportActionBar().newTab();
        tab.setTabListener(this);
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_layout, null);
        ((TextView) v.findViewById(R.id.tab_title)).setText(mSectionsPagerAdapter.getPageTitle(position));
        ((ImageView) v.findViewById(R.id.tab_icon)).setImageDrawable(imageId);
        tab.setCustomView(v);
        getSupportActionBar().addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kasir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(AdminActivity.this, AutentikasiActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        actionBarActiveIndex = tab.getPosition();
        mViewPager.setCurrentItem(tab.getPosition());
        if (fab != null) {
            if (actionBarActiveIndex == 0) {
                fab.setVisibility(View.INVISIBLE);
            } else if (actionBarActiveIndex == 1) {
                fab.setVisibility(View.VISIBLE);
                promosiFragment.updateDataPromosi();
            }
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onTambahEvent(String judul, String jangka, String deskripsi) {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Menunggu...");
        Event event = new Event();
        event.setJudul(judul);
        event.setTanggal(jangka);
        event.setDeskripsi(deskripsi);
        Backendless.Persistence.of(Event.class).save(event, new AsyncCallback<Event>() {
            @Override
            public void handleResponse(Event response) {
                Toast.makeText(getApplicationContext(), "Data promosi berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                promosiFragment.updateDataPromosi();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                progressDialog.dismiss();
            }
        });
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new StatistikFragment();
                case 1:
                    return promosiFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "STATISTIK";
                case 1:
                    return "PROMOSI";
            }
            return null;
        }
    }
}
