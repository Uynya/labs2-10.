package com.example.my1;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //EditText inputEditText;
    TextView outputTextView;

    TextInputLayout emailLayout, passwordLayout;
    TextInputEditText emailEditText, passwordEditText;

    boolean checkEmail = false;
    boolean checkPassword = false;

    public void init()
    {
        emailLayout = findViewById(R.id.emailTextLayout);
        emailEditText = findViewById(R.id.emailText);
        passwordLayout = findViewById(R.id.passwordTextLayout);
        passwordEditText = findViewById(R.id.passwordText);
        outputTextView = findViewById(R.id.TV);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr10ex1);
        setContentView(R.layout.lr10my);

        //inputEditText = findViewById(R.id.EdTV);
        //outputTextView = findViewById(R.id.TV);

        init();

        //это из примера 10 лабы
//        inputEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                outputTextView.setText(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.length() >= 5)
//                    {outputTextView.setTextSize(10f);}
//                if (s.length() >= 10)
//                    {outputTextView.setTextColor(Color.GREEN);}
//            }
//        });

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString().trim();
                if (!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    emailLayout.setError("Введите корректный email");
                    checkEmail = false;
                }
                else {
                    emailLayout.setError(null);
                    checkEmail = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > emailLayout.getCounterMaxLength())
                    emailLayout.setError("Электронная почта не может содержать больше "
                    + emailLayout.getCounterMaxLength() + " символов");
            }
        });

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String password = passwordEditText.getText().toString();
                    if (password.length() < 6) {
                        passwordLayout.setError("Пароль должен содержать минимум 6 символов");
                        checkPassword = false;
                    } else if (password.length() >= 10) {
                        passwordLayout.setError("Пароль не должен содержать больше 10 символов");
                        checkPassword = false;
                    } else {
                        passwordLayout.setError(null);
                        checkPassword = true;
                    }
                    return true;
                }
                return false;
            }
        });
    }
    public void onClick(View view) {
        //String input = inputEditText.getText().toString();
        outputTextView.setText("");
        if (checkEmail && checkPassword){
            outputTextView.setText("Валидация завершена");
        }
        else {
            outputTextView.setText("Где-то ошибка");
            if (!checkEmail){
                emailLayout.setError("Обязательное поле");
            }
            if (!checkPassword){
                passwordLayout.setError("Обязательное поле");
            }
        }
    }
}