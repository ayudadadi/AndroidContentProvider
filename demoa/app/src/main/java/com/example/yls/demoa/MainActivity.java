package com.example.yls.demoa;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnQdb;
    private TextView txtShow;
    private EditText ed_stuno;
    private EditText ed_name;
    private EditText ed_age;
    private Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnQdb = findViewById(R.id.btn_qdb);
        txtShow = findViewById(R.id.show);
        btnQdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.gdcp.student/student");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if(cursor != null && cursor.getCount() > 0){
                    StringBuffer buffer = new StringBuffer();
                    for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                        String stuno = cursor.getString(0);
                        String name = cursor.getString(1);
                        int age = cursor.getInt(2);
                        buffer.append(stuno).append("  ").append(name).append("  ").append(age).append("\n");
                    }

                    txtShow.setText(buffer);
                }
                else {
                    txtShow.setText("行数为0");                }
            }
        });
        ed_stuno = findViewById(R.id.ed_stuno);
        ed_name = findViewById(R.id.ed_name);
        ed_age = findViewById(R.id.ed_age);
        btnInsert = findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.gdcp.student/student");
                String stuno = ed_stuno.getText().toString();
                String name = ed_name.getText().toString();
                int age = Integer.parseInt(ed_age.getText().toString());
                ContentValues values = new ContentValues();
                values.put("stuno", stuno);
                values.put("name", name);
                values.put("age", age);
                getContentResolver().insert(uri, values);
            }
        });
    }
}
