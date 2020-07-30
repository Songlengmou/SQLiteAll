package com.anningtex.sqliteall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.sqliteall.four.FourActivity;
import com.anningtex.sqliteall.one.OneActivity;
import com.anningtex.sqliteall.three.ThreeActivity;
import com.anningtex.sqliteall.two.TwoActivity;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mOneBtn;
    private Button mTwoBtn;
    private Button mThreeBtn;
    private Button mFourBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mOneBtn = findViewById(R.id.one_btn);
        mOneBtn.setOnClickListener(this);
        mTwoBtn = findViewById(R.id.two_btn);
        mTwoBtn.setOnClickListener(this);
        mThreeBtn = findViewById(R.id.three_btn);
        mThreeBtn.setOnClickListener(this);
        mFourBtn = findViewById(R.id.four_btn);
        mFourBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_btn:
                startActivity(new Intent(MainActivity.this, OneActivity.class));
                break;
            case R.id.two_btn:
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
                break;
            case R.id.three_btn:
                startActivity(new Intent(MainActivity.this, ThreeActivity.class));
                break;
            case R.id.four_btn:
                startActivity(new Intent(MainActivity.this, FourActivity.class));
                break;
            default:
                break;
        }
    }
}
