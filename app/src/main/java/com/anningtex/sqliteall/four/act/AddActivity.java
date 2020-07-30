package com.anningtex.sqliteall.four.act;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.four.SQLDaoManger;
import com.anningtex.sqliteall.four.ReturnOnClick;

/**
 * @author Administrator
 * desc:增
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etAge;
    private EditText etPhone;
    private EditText etAddress;
    private EditText etClasses;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvPhone;
    private TextView tvAddress;
    private TextView tvClasses;
    private Button btnAdd;
    private Button btnNext;
    private LinearLayout llOne;
    private LinearLayout llTwo;

    private SQLDaoManger db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etPhone = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_address);
        etClasses = findViewById(R.id.et_classes);
        tvName = findViewById(R.id.tv_a);
        tvAge = findViewById(R.id.tv_b);
        tvPhone = findViewById(R.id.tv_c);
        tvAddress = findViewById(R.id.tv_d);
        tvClasses = findViewById(R.id.tv_e);
        btnAdd = findViewById(R.id.add_btn);
        btnNext = findViewById(R.id.btn_next);
        llOne = findViewById(R.id.ll_one);
        llTwo = findViewById(R.id.ll_two);
        TextView tvUp = findViewById(R.id.tv_up);
        TextView tvReturn = findViewById(R.id.tv_return);
        tvReturn.setOnClickListener(new ReturnOnClick(this));

        tvUp.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        db = new SQLDaoManger(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_up:
                llOne.setVisibility(View.VISIBLE);
                llTwo.setVisibility(View.GONE);
                break;
            case R.id.add_btn:
                addData();
                break;
            case R.id.btn_next:
                next();
                break;
            default:
                break;
        }
    }

    public void addData() {
        final String name = tvName.getText().toString();
        final String age = tvAge.getText().toString();
        final String phone = tvPhone.getText().toString();
        final String address = tvAddress.getText().toString();
        final String classes = tvClasses.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("确认信息无误");
        builder.setNegativeButton("再检查一下", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                long add = db.add(name, Integer.valueOf(age), Long.parseLong(phone), address, classes);
                if (add == -1) {
                    Toast.makeText(AddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(AddActivity.this, AddActivity.class));
                    Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public void next() {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String phone = etPhone.getText().toString();
        String address = etAddress.getText().toString();
        String classes = etClasses.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address) || TextUtils.isEmpty(classes)) {
            Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
        } else {
            llOne.setVisibility(View.GONE);
            llTwo.setVisibility(View.VISIBLE);
            tvName.setText(name);
            tvAge.setText(age);
            tvPhone.setText(phone);
            tvAddress.setText(address);
            tvClasses.setText(classes);
        }
    }
}
