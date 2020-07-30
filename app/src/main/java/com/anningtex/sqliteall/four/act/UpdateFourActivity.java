package com.anningtex.sqliteall.four.act;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.four.SQLDaoManger;
import com.anningtex.sqliteall.four.ReturnOnClick;

/**
 * @author Administrator
 * desc: 改
 */
public class UpdateFourActivity extends AppCompatActivity {
    private EditText etNewName;
    private EditText etNewAge;
    private EditText etNewPhone;
    private EditText etNewAddress;
    private EditText etNewClasses;
    private Button btnModify;

    private SQLDaoManger SQLDaoManger;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_update_four);
        initView();
    }

    private void initView() {
        TextView tvGetId = findViewById(R.id.tv_getId);
        etNewName = findViewById(R.id.et_newName);
        etNewAge = findViewById(R.id.et_newAge);
        etNewPhone = findViewById(R.id.et_newPhone);
        etNewAddress = findViewById(R.id.et_newAddress);
        etNewClasses = findViewById(R.id.et_newClasses);
        btnModify = findViewById(R.id.btn_modify);
        TextView tvReturn = findViewById(R.id.tv_return);
        tvReturn.setOnClickListener(new ReturnOnClick(this));

        SharedPreferences sp = getSharedPreferences("student", MODE_PRIVATE);
        SQLDaoManger = new SQLDaoManger(UpdateFourActivity.this);
        id = sp.getInt("id", 0);
        tvGetId.setText("编号:" + id);
        String[] info = SQLDaoManger.cha1(Integer.valueOf(id));
        etNewName.setText(info[0]);
        etNewAge.setText(info[1]);
        etNewPhone.setText(info[2]);
        etNewAddress.setText(info[3]);
        etNewClasses.setText(info[4]);

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modify();
            }
        });
    }

    public void modify() {
        final String newName = etNewName.getText().toString();
        final String newAge = etNewAge.getText().toString();
        final String newPhone = etNewPhone.getText().toString();
        final String newAddress = etNewAddress.getText().toString();
        final String newClasses = etNewClasses.getText().toString();
        if (TextUtils.isEmpty(newName) || TextUtils.isEmpty(newAge) || TextUtils.isEmpty(newPhone)
                || TextUtils.isEmpty(newAddress) || TextUtils.isEmpty(newClasses)) {
            Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("修改");
            builder.setMessage("修改此条信息");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int update = SQLDaoManger.update(Integer.valueOf(id), newName, Integer.valueOf(newAge),
                            Long.valueOf(newPhone), newAddress, newClasses);
                    if (update == 0) {
                        Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                        //TODO  修改后无法刷新当前数据列表


                    }
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }
}
