package com.anningtex.sqliteall.four.act;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.four.SQLDaoManger;
import com.anningtex.sqliteall.four.StudentBean;
import com.anningtex.sqliteall.four.adapter.MyAdapter;
import com.anningtex.sqliteall.four.ReturnOnClick;

import java.util.List;

/**
 * @author Administrator
 * desc:显示所有的数据
 */
public class ShowFourActivity extends AppCompatActivity {
    private SQLDaoManger SQLDaoManger;
    private List<StudentBean> list;
    private SharedPreferences sp;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_show_four);
        initView();
    }

    private void initView() {
        sp = getSharedPreferences("student", QueryActivity.MODE_PRIVATE);
        ListView lv = findViewById(R.id.listView);
        TextView tvReturn = findViewById(R.id.tv_return);
        tvReturn.setOnClickListener(new ReturnOnClick(this));

        sp = getSharedPreferences("student", MODE_PRIVATE);
        SQLDaoManger = new SQLDaoManger(getApplicationContext());
        list = SQLDaoManger.show();

        myAdapter = new MyAdapter(getApplicationContext(), list);
        lv.setAdapter(myAdapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final StudentBean stu = list.get(position);
                System.out.println(stu);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ShowFourActivity.this);
                String[] str = {"修改", "删除"};
                builder1.setItems(str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            SharedPreferences.Editor edit = sp.edit();
                            int id = stu.getId();
                            edit.putInt("id", id);
                            edit.commit();
                            startActivity(new Intent(ShowFourActivity.this, UpdateFourActivity.class));
                        } else if (which == 1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ShowFourActivity.this);
                            builder.setTitle("删除");
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setMessage("确认要删除这条信息?");
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toast.makeText(ShowFourActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLDaoManger.delete(stu.getId());
                                    List<StudentBean> show = SQLDaoManger.show();
                                    myAdapter.refresh(show);
                                }
                            });
                            builder.create().show();
                        }
                    }
                });
                builder1.create().show();
                return false;
            }
        });
    }

    public void re() {
        List<StudentBean> show = SQLDaoManger.show();
        myAdapter.refresh(show);
    }
}
