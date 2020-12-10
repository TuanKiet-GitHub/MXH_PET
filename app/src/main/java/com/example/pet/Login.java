package com.example.pet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText edSDT, edPassWord;
    Button btnLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        eventLogin();
//        mAuth = FirebaseAuth.getInstance();

    }

    // region Event btnLogin
    void eventLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = edSDT.getText().toString().trim();
                String passWord = edPassWord.getText().toString().trim();

                if (sdt.isEmpty()) {
                    //     Toast.makeText(Login.this, "Name " + name, Toast.LENGTH_LONG).show();
                    edSDT.requestFocus();
                    return;
                }
                if (passWord.isEmpty()) {
                    Toast.makeText(Login.this, "Mật khẩu không được bỏ trống !!! " + passWord, Toast.LENGTH_LONG).show();
                    edPassWord.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(sdt, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplication(), Home.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Lỗi rùi !!! ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
    // endgion



    // region    Sự kiện editText

    void eventEditText() {
        edPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edPassWord.setHint("");
            }
        });
        edSDT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edSDT.setHint("");
            }
        });
    }
    // endregion

    //region ANH XẠ
    void anhXa() {
        btnLogin = findViewById(R.id.btnDangNhap);
        edSDT = findViewById(R.id.edSDT);
        //   edTen.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        edPassWord = findViewById(R.id.edMk);
//        edMk.setInputType(InputType.TYPE_CLASS_TEXT |
//                InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
    // endregion
}