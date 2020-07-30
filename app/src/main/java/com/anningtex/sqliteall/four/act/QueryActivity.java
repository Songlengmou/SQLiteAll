package com.anningtex.sqliteall.four.act;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.four.SQLDaoManger;
import com.anningtex.sqliteall.four.StudentBean;
import com.anningtex.sqliteall.four.adapter.MyAdapter;
import com.anningtex.sqliteall.four.ReturnOnClick;

import java.util.List;

/**
 * @author Administrator
 * desc:查
 */
public class QueryActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private ListView listView;
    private Button mBtnQuery;
    private LinearLayout llOne;

    private MyAdapter adapter;
    private List<StudentBean> list;
    private String name;

    private SharedPreferences sp;
    private SQLDaoManger SQLDaoManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_query);
        initView();
    }

    private void initView() {
        et_name = findViewById(R.id.et_name);
        listView = findViewById(R.id.list_cha);
        llOne = findViewById(R.id.ll_one);
        mBtnQuery = findViewById(R.id.btn_query);
        mBtnQuery.setOnClickListener(this);
        TextView tvReturn = findViewById(R.id.tv_return);
        tvReturn.setOnClickListener(new ReturnOnClick(this));

        sp = getSharedPreferences("student", QueryActivity.MODE_PRIVATE);

        SQLDaoManger = new SQLDaoManger(getApplicationContext());
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final StudentBean stu = list.get(position);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(QueryActivity.this);
                String[] str = {"修改", "删除"};
                builder1.setItems(str, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            SharedPreferences.Editor edit = sp.edit();
                            int id = stu.getId();
                            edit.putInt("id", id);
                            edit.commit();
                            startActivity(new Intent(QueryActivity.this, UpdateFourActivity.class));
                        } else if (which == 1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(QueryActivity.this);
                            builder.setTitle("删除");
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setMessage("确认要删除这条信息");
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toast.makeText(QueryActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLDaoManger.delete(stu.getId());
                                    List<StudentBean> cha = SQLDaoManger.cha(name);
                                    adapter.refresh(cha);
                                    dialog.dismiss();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                query();
                break;
            default:
                break;
        }
    }

    public void query() {
        name = et_name.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        list = SQLDaoManger.cha(name);
        boolean a = false;
        for (int i = 0; i < list.size(); i++) {
            if (name.equals(list.get(i).getName())) {
                llOne.setVisibility(View.VISIBLE);
                adapter = new MyAdapter(getApplicationContext(), list);
                listView.setAdapter(adapter);
                a = true;
            }
        }
        if (a == false) {
            Toast.makeText(getApplicationContext(), "没有这个学生", Toast.LENGTH_SHORT).show();
        }
    }
}
