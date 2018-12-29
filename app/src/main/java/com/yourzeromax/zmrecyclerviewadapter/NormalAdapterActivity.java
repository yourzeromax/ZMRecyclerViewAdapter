package com.yourzeromax.zmrecyclerviewadapter;

/* *
 * Created by yourzeromax
 * on 2018/12/29
 *
 *
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yourzeromax.adapterlibrary.ZMCommonAdapter;
import com.yourzeromax.zmrecyclerviewadapter.model.Student;
import com.yourzeromax.zmrecyclerviewadapter.utils.StudentUtils;

import java.util.List;
import java.util.Locale;

public class NormalAdapterActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Student> dataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_adapter);
        recyclerView=findViewById(R.id.recycler_view);
        getData();

        initRecyclerView();
    }

    private void getData() {
        dataList = StudentUtils.getStudentsData(20);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ZMCommonAdapter<Student>(this, dataList, R.layout.item_student) {
            @Override
            public void convert(CommonViewHolder viewHolder, Student data) {
                viewHolder.setText(R.id.tv_name, data.getName());
                viewHolder.setText(R.id.tv_id, String.format(Locale.ENGLISH, "%1$d", data.getId()));
            }
        });
    }
}
