package com.example.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainPage extends AppCompatActivity {
    TextView txtLogin ;
    Button btnRegistration ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        anhXa();
        event();
    }
    void event ()
    {
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this , Registration.class);
                startActivity(intent);
                finish();

            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this , Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
    void anhXa()
    {
        txtLogin = findViewById(R.id.txtDangNhap);
        btnRegistration = findViewById(R.id.btnDangKi);
//        edTen = findViewById(R.id.edTenDN);
//        edTen.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//        edMk = findViewById(R.id.edMk);
//        edMk.setInputType(InputType.TYPE_CLASS_TEXT |
//                InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}