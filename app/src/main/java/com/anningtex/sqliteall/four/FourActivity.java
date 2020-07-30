package com.anningtex.sqliteall.four;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.four.act.AddActivity;
import com.anningtex.sqliteall.four.act.QueryActivity;
import com.anningtex.sqliteall.four.act.ShowFourActivity;

/**
 * @author Administrator
 */
public class FourActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnAdd;
    private Button mBtnQuery;
    private Button mBtnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        initView();
    }

    private void initView() {
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
        mBtnQuery = findViewById(R.id.btn_query);
        mBtnQuery.setOnClickListener(this);
        mBtnShow = findViewById(R.id.btn_show);
        mBtnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
                break;
            case R.id.btn_query:
                startActivity(new Intent(getApplicationContext(), QueryActivity.class));
                break;
            case R.id.btn_show:
                startActivity(new Intent(getApplicationContext(), ShowFourActivity.class));
                break;
            default:
                break;
        }
    }
}
