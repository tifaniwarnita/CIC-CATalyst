package com.tifaniwarnita.ciccatalystcore.kasir;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.tifaniwarnita.ciccatalystcore.MainActivity;
import com.tifaniwarnita.ciccatalystcore.R;
import com.tifaniwarnita.ciccatalystcore.model.Pelanggan;

import java.util.ArrayList;
import java.util.Date;

public class KasirActivity extends AppCompatActivity implements
        ActionBar.TabListener, ReservasiFragment.ReservationFragmentListener,
        DetailReservasiFragment.ReservationDetailFragmentListener,
        TambahReservasiDialogFragment.TambahReservasiDialogFragmentListener,
        TambahPelangganDialogFragment.TambahPelangganDialogFragmentListener {

    public static ArrayList<Pelanggan> dataPelanggan = new ArrayList<>();
    private int actionBarActiveIndex = 0;

    private PelangganFragment pelangganFragment = new PelangganFragment();
    private PesananFragment pesananFragment = new PesananFragment();
    private ReservasiFragment reservasiFragment = new ReservasiFragment();

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir);
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

        createTab(0, getResources().getDrawable(R.drawable.icon_pelanggan));
        createTab(1, getResources().getDrawable(R.drawable.icon_pesanan));
        createTab(2, getResources().getDrawable(R.drawable.icon_reservasi));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (actionBarActiveIndex) {
                    case 0:
                        TambahPelangganDialogFragment tambahPelangganDialogFragment = new TambahPelangganDialogFragment();
                        tambahPelangganDialogFragment.show(getSupportFragmentManager(), null);
                        break;
                    case 1:
                        TambahPesananDialogFragment tambahPesananDialogFragment = new TambahPesananDialogFragment();
                        tambahPesananDialogFragment.show(getSupportFragmentManager(), null);
                        break;
                    case 2:
                        TambahReservasiDialogFragment tambahReservasiDialog = TambahReservasiDialogFragment.newInstance(
                                DetailReservasiFragment.convertDateToString(new Date())
                        );
                        tambahReservasiDialog.show(getSupportFragmentManager(), null);
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
            Toast.makeText(getApplicationContext(), "LOGOUT", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        actionBarActiveIndex = tab.getPosition();
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onSelectDate(Date date) {
        Intent intent = new Intent(this, DetailReservasiActivity.class);
        intent.putExtra(getResources().getString(R.string.detail_reservasi),
                DetailReservasiFragment.convertDateToString(date));
        startActivity(intent);
    }

    @Override
    public void onDetailReservasiBack() {

    }

    @Override
    public void onReservasi(int i, int j) {

    }

    @Override
    public void onTambahPelanggan(String nama, String noHP, String email) {
        // TODO: Masukin ke db
        /*
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", "Menunggu...");
        kalo udah isi db dismiss
        habis itu panggil
        pelangganFragment.updateDataPelanggan(); -> belum dicoba sih...

         */
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_kasir, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
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
                    return pelangganFragment;
                case 1:
                    return pesananFragment;
                case 2:
                    return reservasiFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PELANGGAN";
                case 1:
                    return "PESANAN";
                case 2:
                    return "RESERVASI";
            }
            return null;
        }
    }
}
