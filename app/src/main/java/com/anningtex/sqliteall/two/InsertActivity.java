package com.anningtex.sqliteall.two;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.sql.TwoDBHelper;

/**
 * @author Administrator
 * desc:增
 */
public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        initView();
    }

    private void initView() {
        Button bt1 = findViewById(R.id.newRec);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText et1 = findViewById(R.id.editText1);
                EditText et2 = findViewById(R.id.editText2);
                String et1S = et1.getText().toString();
                String et2S = et2.getText().toString();

                if (TextUtils.isEmpty(et1S) || TextUtils.isEmpty(et2S)) {
                    Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Person person = new Person(et1S, Integer.parseInt(et2S));
                    insert(person);
                    //不回传intent设为null
                    setResult(RESULT_OK, null);
                    finish();
                }
            }
        });
    }

    private void insert(Person person) {
        TwoDBHelper helper = new TwoDBHelper(getApplicationContext(), "two.db", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "INSERT INTO person VALUES (NULL, ?, ?)";
        db.execSQL(sql, new Object[]{person.name, person.age});
        db.close();
        Toast.makeText(getApplicationContext(), "记录添加成功", Toast.LENGTH_SHORT).show();
    }
}
