package com.tifaniwarnita.ciccatalystcore;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutentikasiAdminFragment extends Fragment {

    private AutentikasiAdminFragmentListener fragmentListener;

    public interface AutentikasiAdminFragmentListener {
        void berhasilLogin();
    }

    public AutentikasiAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_autentikasi_admin, container, false);

        final EditText editTextUsername = (EditText) v.findViewById(R.id.edit_text_username);
        final EditText editTextPassword = (EditText) v.findViewById(R.id.edit_text_password);
        Button buttonLogin = (Button) v.findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                if (username == null || username.equals("")) {
                    Toast.makeText(getContext(), "Silakan masukan username",
                            Toast.LENGTH_SHORT).show();
                } else if (password == null || password.equals("")) {
                    Toast.makeText(getContext(), "Silakan masukan password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (checkUsernamePassword(username, password)) {
                        // Move to admin panel
                        Toast.makeText(getContext(), "Login berhasil",
                                Toast.LENGTH_SHORT).show();
                        fragmentListener.berhasilLogin();
                        // TODO:
                    } else {
                        Toast.makeText(getContext(), "Username atau password salah ",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }

    private boolean checkUsernamePassword(String username, String password) {
        if (username.equals("tifaniwarnita") && password.equals("kucingterbang")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AutentikasiAdminFragmentListener) {
            fragmentListener = (AutentikasiAdminFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

}
