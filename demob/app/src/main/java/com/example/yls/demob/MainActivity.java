package com.example.yls.demob;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button btnInsertStu;
    private Button btnQueryStu;
    private Button btnInsertSco;
    private Button btnQuerySco;
    private Button btnDelStu;
    private Button btnUpdStu;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertStu = findViewById(R.id.btn_insert_stu);
        btnInsertStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.gdcp.student/student");
                ContentValues values = new ContentValues();
                values.put("stuno", "171001");
                values.put("name", "zhangsan");
                values.put("age", 20);
                getContentResolver().insert(uri, values);
            }
        });
        btnDelStu= findViewById(R.id.del_stu);
        btnDelStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.gdcp.student/student");
                String where = "name ='zhangsan'";
                int rows = getContentResolver().delete(uri,where,null);
                Toast.makeText(MainActivity.this,"删除了"+rows+"行",Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdStu = findViewById(R.id.upd_stu);
        btnUpdStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.gdcp.student/student");
                ContentValues values = new ContentValues();
                values.put("age",59);
                String where = "age =20";
                int rows =getContentResolver().update(uri,values,where,null);
                Toast.makeText(MainActivity.this,"更新了"+rows+"行",Toast.LENGTH_SHORT).show();


            }
        });
        txtResult = findViewById(R.id.txt_result);
        btnQueryStu = findViewById(R.id.btn_query_stu);
        btnQueryStu.setOnClickListener(new View.OnClickListener() {
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

                   txtResult.setText(buffer);

               }else {
                   txtResult.setText("行数为0");
               }
            }
        });

        btnInsertSco = findViewById(R.id.btn_insert_sco);
        btnInsertSco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.gdcp.student/score");
                ContentValues values = new ContentValues();
                values.put("stuno", "171001");
                values.put("english", 80);
                values.put("android", 85);
                getContentResolver().insert(uri, values);
            }
        });

        btnQuerySco = findViewById(R.id.btn_query_sco);
        btnQuerySco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.gdcp.student/score");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if(cursor != null && cursor.getCount() > 0){
                    StringBuffer buffer = new StringBuffer();
                    for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                        String stuno = cursor.getString(0);
                        int english  = cursor.getInt(1);
                        int android = cursor.getInt(2);
                        buffer.append(stuno).append("  ").append(english).append("  ").append(android).append("\n");
                    }

                    txtResult.setText(buffer);
                }
            }
        });


    }
}
