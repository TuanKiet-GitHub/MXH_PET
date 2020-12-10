    package com.example.pet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ConfirmSDT extends AppCompatActivity {
    EditText edText1, edText2 , edText3 , edText4 , edText5, edText6 , editText7;
    TextView tvSDT ;
    Button btnNext ;
    FirebaseAuth    mAuth;
    String codeBySystem;
    public static final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_s_d_t);
        anhXa();
        eventEdText();
        Intent intent = getIntent();
        String sdt = intent.getStringExtra("phone");
        sdt = "+84" + sdt.substring(1).toString().trim() ;
//        tvSDT.setText(sdt);
//        mAuth = FirebaseAuth.getInstance();
//        sendOTP(sdt);
//        String codeInputUser = edText1.getText().toString() + edText2.getText().toString() + edText3.getText().toString() + edText4.getText().toString() + edText5.getText().toString() + edText6.getText().toString();
//
//
//                        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editText7.setText(codeInputUser);
//            }
//      });
    }

    //region GỬI MÃ OTP
    private void sendOTP(String phone)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        Log.d(TAG, "Send OTP");


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            String code = credential.getSmsCode();

            if (code != null)
            {
                editText7.setText(code);
                verifyVerificationCode(code);
                Log.d(TAG, "onVerification COMPLETED " + code);

            }


        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.d(TAG, "on Verification Failed " + e.getMessage() , e);

        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
              super.onCodeSent(verificationId, token);

              codeBySystem = verificationId ;
            Log.d(TAG, "oN cODE sEND " );

        }



    };
    private void verifyVerificationCode (String code )
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem , code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(ConfirmSDT.this , "Verification done", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ConfirmSDT.this , Home.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                               Toast.makeText(ConfirmSDT.this , "Incorrect Verification Code " , Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });
    }

    //endregion

    //region SỰ KIỆN EDIT TEXT VÀ ÁNH XẠ
    private final  long DELAY =1000 ;
    private Timer timer ;
    private TimerTask timerTask ;

    // XỬ LÝ EVENT CHO EDIT TEXT
    void eventEdText() {

        edText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(edText1.getText().length() == 1) {
                    edText2.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText2.setCursorVisible(true);
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {

                        }
                    };
                    timer.schedule(timerTask,0, DELAY);
                }

            }
        });

        edText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(edText2.getText().length() == 1) {
                    edText3.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText3.setCursorVisible(true);
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {

                        }
                    };
                    timer.schedule(timerTask,0, DELAY);
                }

                if (edText2.getText().length() == 0)
                {
                    edText1.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText1.setCursorVisible(true);
                }
            }

        });

        edText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (edText3.getText().length() == 1)
                {
                    edText4.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText4.setCursorVisible(true);
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {

                        }
                    };
                    timer.schedule(timerTask,0, DELAY);
                }
                if (edText3.getText().length() == 0)
                {
                    edText2.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText2.setCursorVisible(true);
                }
            }
        });

        edText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (edText4.getText().length() == 1)
                {
                    edText5.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText5.setCursorVisible(true);
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {

                        }
                    };
                    timer.schedule(timerTask,0, DELAY);
                }
                if (edText4.getText().length() == 0)
                {
                    edText3.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText3.setCursorVisible(true);
                }
            }
        });

        edText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (edText5.getText().length() == 1)
                {
                    edText6.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText6.setCursorVisible(true);
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {

                        }
                    };
                    timer.schedule(timerTask,0, DELAY);
                }
                if (edText5.getText().length() == 0)
                {
                    edText4.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                    edText4.setCursorVisible(true);
                }
            }
        });

        edText6.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {
                 if (edText6.getText().length() == 0)
                 {
                     edText5.requestFocus(); // không có dòng này thì setCursorVisible không chạy
                     edText5.setCursorVisible(true);
                 }
             }
         });



    }



    void anhXa()
    {
        btnNext = findViewById(R.id.btnNext);
        edText1 = findViewById(R.id.edText1);
        edText2 = findViewById(R.id.edText2);
        edText3 = findViewById(R.id.edText3);
        edText4 = findViewById(R.id.edText4) ;
        edText5 = findViewById(R.id.edText5) ;
        edText6 = findViewById(R.id.edText6) ;
        tvSDT = findViewById(R.id.textViewSDT) ;
        editText7 = findViewById(R.id.tam);

;    }
    //endregion
}