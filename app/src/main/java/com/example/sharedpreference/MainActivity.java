package com.example.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etID;
    private Button btnSave, btnClear;
    private TextView tvResult;

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_Name);
        etID = findViewById(R.id.et_ID);
        btnSave = findViewById(R.id.btn_save);
        btnClear = findViewById(R.id.btn_clear);
        tvResult = findViewById(R.id.tv_result);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String ID = etID.getText().toString();
                if (!name.isEmpty() && !ID.isEmpty()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME, name);
                    editor.putString(KEY_ID, ID);
                    editor.apply();
                    tvResult.setText("Ho ten: " + name + "\nMa SV: " + ID);
                } else {
                    tvResult.setText("Vui long nhap du thong tin");
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                tvResult.setText("");
            }
        });

        String name = sharedPreferences.getString(KEY_NAME, null);
        String studentID = sharedPreferences.getString(KEY_ID, null);
        if (name != null && studentID != null) {
            tvResult.setText("Ho ten: " + name + "\nMa SV: " + studentID);
        }
    }
}