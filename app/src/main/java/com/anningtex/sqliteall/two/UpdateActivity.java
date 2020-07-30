package com.anningtex.sqliteall.two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.sql.TwoDBHelper;

/**
 * @author Administrator
 * desc:改
 */
public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("id");
        String name = bundle.getString("username");
        String age = bundle.getString("age");

        final EditText et1 = findViewById(R.id.editText3);
        final EditText et2 = findViewById(R.id.editText4);
        final EditText et3 = findViewById(R.id.editText5);
        et1.setText(id);
        et2.setText(name);
        et3.setText(age);

        Button bt1 = findViewById(R.id.button_save);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                TwoDBHelper helper = new TwoDBHelper(getApplicationContext(), "two.db", null, 1);
                SQLiteDatabase db = helper.getWritableDatabase();
                //更新数据，id值不能修改
                db.execSQL("Update person set name= ? , age=? where id=?", new Object[]{et2.getText().toString(), et3.getText().toString(), et1.getText().toString()});
                db.close();
                Toast.makeText(getApplicationContext(), "记录修改成功", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, null);
                finish();
            }
        });

        Button bt2 = findViewById(R.id.button_cancel2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
