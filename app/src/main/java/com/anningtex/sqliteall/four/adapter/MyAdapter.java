package com.anningtex.sqliteall.four.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anningtex.sqliteall.R;
import com.anningtex.sqliteall.four.StudentBean;

import java.util.List;

/**
 * @author Administrator
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<StudentBean> lists;

    public MyAdapter(Context context, List<StudentBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        StudentBean stu = lists.get(position);
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_four, null);
        } else {
            view = convertView;
        }

        TextView id_tv = view.findViewById(R.id.tv_id);
        TextView name_tv = view.findViewById(R.id.tv_name);
        TextView age_tv = view.findViewById(R.id.tv_age);
        TextView phone_tv = view.findViewById(R.id.tv_phone);
        TextView address_tv = view.findViewById(R.id.tv_address);
        TextView classes_tv = view.findViewById(R.id.tv_classes);

        id_tv.setText(String.valueOf(stu.getId()));
        name_tv.setText(stu.getName());
        age_tv.setText(String.valueOf(stu.getAge()));
        phone_tv.setText(String.valueOf(stu.getPhone()));
        address_tv.setText(stu.getAddress());
        classes_tv.setText(stu.getClasses());
        return view;
    }

    public void refresh(List<StudentBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }
}
