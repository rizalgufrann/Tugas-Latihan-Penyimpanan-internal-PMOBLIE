package com.example.rizal_login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText mViewUser, mViewNim, mViewPassword, mViewRepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mViewUser =findViewById(R.id.et_emailSignup);
        mViewNim =findViewById(R.id.et_nim);
        mViewPassword =findViewById(R.id.et_passwordSignup);
        mViewRepassword =findViewById(R.id.et_passwordSignup2);

        mViewRepassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.button_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
    }

    private void razia(){
        mViewUser.setError(null);
        mViewNim.setError(null);
        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String repassword = mViewRepassword.getText().toString();
        String user = mViewUser.getText().toString();
        String nim = mViewNim.getText().toString();
        String password = mViewPassword.getText().toString();

        if (TextUtils.isEmpty(user)){
            mViewUser.setError("Kolom ini wajib diisi");
            fokus = mViewUser;
            cancel = true;
        }else if(cekUser(user)){
            mViewUser.setError("Username ini sudah ada");
            fokus = mViewUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(nim)){
            mViewNim.setError("Kolom ini wajib di isi");
            fokus = mViewNim;
            cancel = true;
        }else if(cekUser(nim)){
            mViewNim.setError("Nim ini sudah ada");
            fokus = mViewNim;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("Kolom ini wajib diisi");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password,repassword)){
            mViewRepassword.setError("Password salah");
            fokus = mViewRepassword;
            cancel = true;
        }

        if (cancel){
            fokus.requestFocus();
        }else{
            Preferences.setRegisteredUser(getBaseContext(),user);
            Preferences.setRegisteredPass(getBaseContext(),password);
            finish();
        }
    }

    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }

    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
    private boolean ceknimr(String nim){
        return nim.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}