package com.example.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.RegionIterator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pet.mode.UserClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText  edSTD , edPassWord , edPassWord2 , edName;
    TextView tvHavaAccount ;
    Button btnNext ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        anhXa();

        // BẮT SỰ KIỆN KHI NHẤN VÀO EDITTEXT THÌ NÓ SẼ CHỮ HINT SẼ MẤT ĐỂ NGƯỜI DÙNG NHẬP VÀO

        edPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edPassWord.setHint("");
            }
        });
        edPassWord2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edPassWord2.setHint("");
            }
        });
        edName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                edName.setHint("");
            }
        });

        // CHUYỂN TRANG
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lấy dữ liệu editText getText().toString().
                if (edPassWord.getText().toString().equals(edPassWord2.getText().toString()))
                {
                    Intent intent = new Intent(Registration.this , ConfirmSDT.class);
                    intent.putExtra("phone" , edSTD.getText().toString());
                    startActivity(intent);


               //      Truyền dữ liệu lên FireBase
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("user");
                    String sdt = edSTD.getText().toString();
                    String userName = edName.getText().toString();
                    String passWord = edPassWord.getText().toString();
                    UserClass user = new UserClass( userName , passWord , sdt);
                    myRef.child("1").setValue(user);



                }
                else
                {

                    Toast toast = Toast.makeText(Registration.this ,"Mật khẩu nhập lại không chính xác vui lòng kiểm tra lại !!! ", Toast.LENGTH_LONG);
                    toast.show(); // nhớ show ra nhe
                }

            }
        });

        tvHavaAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this , Login.class);
                startActivity(intent);
                finish();
            }
        });


    }



    void anhXa()
    {
        edSTD = findViewById(R.id.edSdt);
        tvHavaAccount = findViewById(R.id.tvhaveAccount);
        btnNext = findViewById(R.id.btnNext);
        edName = findViewById(R.id.edFullName);
        edPassWord= findViewById(R.id.edPassWord);
        edPassWord2= findViewById(R.id.edPassWord2);
    }

}