package com.tifaniwarnita.ciccatalystcore;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tifaniwarnita.ciccatalystcore.admin.AdminActivity;
import com.tifaniwarnita.ciccatalystcore.kasir.KasirActivity;

public class AutentikasiActivity extends AppCompatActivity
        implements AutentikasiAdminFragment.AutentikasiAdminFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentikasi);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, new PilihanAksesFragment())
                .commit();
    }

    @Override
    public void berhasilLogin() {
        Intent intent = new Intent(AutentikasiActivity.this, AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        startActivity(intent);
    }
}
