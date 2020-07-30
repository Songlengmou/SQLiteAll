package com.anningtex.sqliteall.three;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.anningtex.sqliteall.R;

/**
 * @author Administrator
 * desc:
 */
public class ThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        initView();
    }

    private void initView() {
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreeDataBean p = new ThreeDataBean();
                p.setName("ZL");
                SQLMager.getInstance(ThreeActivity.this).add(p);
                SQLMager.getInstance(ThreeActivity.this).getAll();
            }
        });
        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreeDataBean p = new ThreeDataBean();
                p.setName("LYL");
                SQLMager.getInstance(ThreeActivity.this).up(p, "ZL");
                SQLMager.getInstance(ThreeActivity.this).getAll();
            }
        });
        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLMager.getInstance(ThreeActivity.this).del("LYL");
                SQLMager.getInstance(ThreeActivity.this).getAll();
            }
        });
    }
}
