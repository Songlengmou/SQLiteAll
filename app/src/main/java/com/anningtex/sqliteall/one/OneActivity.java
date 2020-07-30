package com.anningtex.sqliteall.one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.sql.OneDBHelper;
import com.anningtex.sqliteall.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * desc: 静态的简单数据库（增删改查）
 */
public class OneActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mInsertBtn;
    private Button mDeleteBtn;
    private Button mUpdateBtn;
    private Button mQueryBtn;
    private ListView lvShow;
    private OneDBHelper mHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        PermissionUtils.verifyStoragePermissions(this);
        initView();
        mHelper = new OneDBHelper(this);
        mDatabase = mHelper.getWritableDatabase();
    }

    private void initView() {
        mInsertBtn = findViewById(R.id.insert_btn);
        mInsertBtn.setOnClickListener(this);
        mDeleteBtn = findViewById(R.id.delete_btn);
        mDeleteBtn.setOnClickListener(this);
        mUpdateBtn = findViewById(R.id.update_btn);
        mUpdateBtn.setOnClickListener(this);
        mQueryBtn = findViewById(R.id.query_btn);
        mQueryBtn.setOnClickListener(this);
        lvShow = findViewById(R.id.lv_show);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.insert_btn) {
            insertData();
        } else if (view.getId() == R.id.delete_btn) {
            deleteData();
        } else if (view.getId() == R.id.update_btn) {
            updateData();
        } else if (view.getId() == R.id.query_btn) {
            queryData();
        }
    }

    /**
     * 增
     * null。数据库如果插入的数据为null，会引起数据库不稳定。为了防止崩溃，需要传入第二个参数要求的对象
     * 如果插入的数据不为null，没有必要传入第二个参数避免崩溃，所以为null
     */
    private void insertData() {
        ContentValues values = new ContentValues();
        values.put(OneDBHelper.NAME, "长安");
        values.put(OneDBHelper.AGE, 24);
        mDatabase.insert(OneDBHelper.TABLE_NAME, null, values);
        Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 删
     */
    private void deleteData() {
        int count = mDatabase.delete(OneDBHelper.TABLE_NAME, OneDBHelper.NAME + " = ?", new String[]{"一片月"});
        Toast.makeText(this, "删除数量：" + count, Toast.LENGTH_SHORT).show();
    }

    /**
     * 改
     */
    private void updateData() {
        ContentValues values = new ContentValues();
        values.put(OneDBHelper.NAME, "一片月");
        values.put(OneDBHelper.AGE, 18);
        int count = mDatabase
                .update(OneDBHelper.TABLE_NAME, values, OneDBHelper.NAME + " = ?", new String[]{"长安"});
        Toast.makeText(this, "修改成功：" + count, Toast.LENGTH_SHORT).show();
    }

    /**
     * 查
     */
    private void queryData() {
        Cursor cursor = mDatabase.query(OneDBHelper.TABLE_NAME,
                new String[]{OneDBHelper.NAME, OneDBHelper.AGE},
                OneDBHelper.AGE + " > ?",
                new String[]{"16"},
                null,
                null,
                // 注意空格！
                OneDBHelper.AGE + " desc");

        int nameIndex = cursor.getColumnIndex(OneDBHelper.NAME);
        int ageIndex = cursor.getColumnIndex(OneDBHelper.AGE);
        List<OneBean> list;
        list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(nameIndex);
            String age = cursor.getString(ageIndex);
            Log.d("1507", "name: " + name + ", age: " + age);

            list.add(new OneBean(name, age));
        }

        MyAdapter myAdapter = new MyAdapter(this, list);
        lvShow.setAdapter(myAdapter);
    }

    public class MyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<OneBean> list;

        public MyAdapter(Context context, List<OneBean> list) {
            this.context = context;
            this.list = list;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = inflater.inflate(R.layout.item_one, null);
            }

            TextView name = view.findViewById(R.id.tv_name);
            name.setText("Name: " + list.get(i).getName() + " Age: " + list.get(i).getAge());

            return view;
        }
    }
}
