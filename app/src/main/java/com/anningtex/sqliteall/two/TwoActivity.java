package com.anningtex.sqliteall.two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.sql.TwoDBHelper;

/**
 * @author Administrator
 * desc: 数据库（增删改查）
 * source:https://blog.csdn.net/congcong7267/article/details/80208759?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
 * <p>
 * 注：主界面button的删除和修改按钮功能： 长按查询数据列表可弹出
 */
public class TwoActivity extends AppCompatActivity implements View.OnClickListener {

    private static int DB_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
    }

    private void initView() {
        Button bt1 = findViewById(R.id.button1);
        Button bt2 = findViewById(R.id.button2);
        Button bt3 = findViewById(R.id.button3);
        Button bt4 = findViewById(R.id.button4);
        Button bt5 = findViewById(R.id.button5);
        Button bt6 = findViewById(R.id.button6);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Person person;
        switch (view.getId()) {
            case R.id.button1:
                createDb();
                break;
            case R.id.button2:
                person = new Person();
                person.name = "LYL";
                person.age = 39;
                insert(person);
                break;
            case R.id.button3:
                deleteById(1);
                break;
            case R.id.button4:
                person = new Person();
                person.name = "LYL";
                person.age = 39;
                updateById(1, person);
                break;
            case R.id.button5:
                find();
                break;
            default:
                break;
        }
    }

    private void createDb() {
        TwoDBHelper helper = new TwoDBHelper(getApplicationContext(), "two.db", null, DB_VERSION);
        //调用getWritableDatabase()或getReadableDatabase()才会真正创建或打开
        SQLiteDatabase db = helper.getWritableDatabase();
        db.close(); //操作完成后关闭数据库连接
        Toast.makeText(getApplicationContext(), "数据库创建成功", Toast.LENGTH_SHORT).show();
    }

    private void insert(Person person) {
        TwoDBHelper helper = new TwoDBHelper(getApplicationContext(), "two.db", null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO person VALUES (NULL, ? , ? )", new Object[]{person.name, person.age});
        db.close();
        Toast.makeText(getApplicationContext(), "记录添加成功", Toast.LENGTH_SHORT).show();
    }

    private void deleteById(int id) {
        TwoDBHelper helper = new TwoDBHelper(getApplicationContext(), "two.db", null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("Delete from person where id= ? ", new Object[]{id});
        db.close();
        Toast.makeText(getApplicationContext(), "记录删除成功", Toast.LENGTH_SHORT).show();
    }

    private void updateById(int id, Person person) {
        TwoDBHelper helper = new TwoDBHelper(getApplicationContext(), "two.db", null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("Update person set name= ? , age= ? where id= ? ", new Object[]{person.name, person.age, id});
        db.close();
        Toast.makeText(getApplicationContext(), "记录修改成功", Toast.LENGTH_SHORT).show();
    }

    private void find() {
        startActivity(new Intent(TwoActivity.this, ShowActivity.class));
    }
}
