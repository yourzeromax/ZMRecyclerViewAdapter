package com.yourzeromax.zmrecyclerviewadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yourzeromax.adapterlibrary.ZMMultiAdapter;
import com.yourzeromax.zmrecyclerviewadapter.model.Student;
import com.yourzeromax.zmrecyclerviewadapter.utils.StudentUtils;

import java.util.List;
import java.util.Locale;

public class MultiAdapterActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Student> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_adapter);
        recyclerView = findViewById(R.id.recycler_view);
        getData();

        initRecyclerView();
    }

    private void getData() {
        dataList = StudentUtils.getMultiStudentsData(20);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ZMMultiAdapter<Student>(this, dataList) {
            @Override
            public void convert(CommonViewHolder viewHolder, Student data) {
                viewHolder.setText(R.id.tv_name, data.getName());
                viewHolder.setText(R.id.tv_id, String.format(Locale.ENGLISH, "%1$d", data.getId()));
            }

            @Override
            public int getLayoutId(int position, Student data) {
                if (data.isMulti()) {
                    return R.layout.item_student_multi;
                }
                return R.layout.item_student;
            }
        });
    }

}
