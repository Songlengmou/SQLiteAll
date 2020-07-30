package com.anningtex.sqliteall.two;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.sql.TwoDBHelper;

/**
 * @author Administrator
 * desc:展示信息
 */
public class ShowActivity extends AppCompatActivity {
    private SimpleCursorAdapter adapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        TwoDBHelper helper = new TwoDBHelper(getApplicationContext(), "two.db", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        cursor = db.rawQuery("SELECT id as _id, name,age FROM Person", null);
        String[] from = {"_id", "name", "age"};
        int[] to = {R.id.txtID, R.id.txtName, R.id.txtAge};

        adapter = new SimpleCursorAdapter(this, R.layout.item_two, cursor, from, to);
        //cursor.close();不能close()，否则SimpleCursorAdapter将不能从Cursor中读取数据显示
        ListView li = findViewById(R.id.listView1);
        li.setAdapter(adapter);

        TextView tv = findViewById(R.id.textView_rem);
        tv.setText("查询到" + cursor.getCount() + "条记录");

        ListView list1 = findViewById(R.id.listView1);
        //将上下文菜单注册到ListView上
        registerForContextMenu(list1);

        Button bt1 = findViewById(R.id.button_add);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(ShowActivity.this, InsertActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            // 100表明是来自于InsertActivity的回传
            if (resultCode == RESULT_OK) {
                //刷新
                cursor.requery();
                adapter.notifyDataSetChanged();
            }
        }

        if (requestCode == 200) {
            //200表明是来自于UpdateActivity的回传
            if (requestCode == RESULT_OK) {

                //TODO 修改成功后 界面刷新不了  需返回重新进入
//                cursor.requery();
//                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("操作");
        getMenuInflater().inflate(R.menu.managa, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                delete(item);
                return true;
            case R.id.update:
                update(item);
                return true;
            default:
                return false;
        }
    }

    /**
     * 删除：根据id值作为删除记录的条件
     */
    public void delete(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (info.id > 0) {
            new AlertDialog.Builder(this).setTitle("删除id为" + info.id + " ?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TwoDBHelper helper = new TwoDBHelper(getApplicationContext(), "two.db", null, 1);
                            SQLiteDatabase db = helper.getWritableDatabase();
                            db.execSQL("Delete from person where id= ? ", new Object[]{info.id});
                            db.close();
                            Toast.makeText(getApplicationContext(), "记录删除成功", Toast.LENGTH_SHORT).show();
                            cursor.requery();
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("取消", null).show();
        }
    }

    /**
     * 修改：将id值及其他字段的值传到UpdateActivity显示和修改
     * 将当前选中行的id、name、age字段值送到UpdateActivity中去修改，后者回传信息
     */
    public void update(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent = new Intent(ShowActivity.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", String.valueOf(info.id));
        bundle.putString("username", ((TextView) info.targetView.findViewById(R.id.txtName)).getText().toString());
        bundle.putString("age", ((TextView) info.targetView.findViewById(R.id.txtAge)).getText().toString());
        intent.putExtras(bundle);
        startActivityForResult(intent, 200);
    }
}
