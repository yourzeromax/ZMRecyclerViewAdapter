package com.yourzeromax.zmrecyclerviewadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yourzeromax.adapterlibrary.ZMCommonAdapter;
import com.yourzeromax.adapterlibrary.ZMHeaderFooterAdapter;
import com.yourzeromax.zmrecyclerviewadapter.model.Student;
import com.yourzeromax.zmrecyclerviewadapter.utils.StudentUtils;

import java.util.List;
import java.util.Locale;

public class HeaderFooterActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Student> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer);
        setContentView(R.layout.activity_multi_adapter);
        recyclerView = findViewById(R.id.recycler_view);
        getData();

        initRecyclerView();
    }

    private void getData() {
        dataList = StudentUtils.getStudentsData(20);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ZMCommonAdapter<Student> mAdapter = new ZMCommonAdapter<Student>(this, dataList, R.layout.item_student) {
            @Override
            public void convert(CommonViewHolder viewHolder, Student data) {
                viewHolder.setText(R.id.tv_name, data.getName());
                viewHolder.setText(R.id.tv_id, String.format(Locale.ENGLISH, "%1$d", data.getId()));
            }
        };
        ZMHeaderFooterAdapter<Student> mHeaderFooterAdapter = new ZMHeaderFooterAdapter<>(mAdapter);
        View mHeader = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        View mFooter = LayoutInflater.from(this).inflate(R.layout.item_footer, recyclerView, false);
        mHeaderFooterAdapter.addHeaderView(R.layout.item_header, mHeader);
        mHeaderFooterAdapter.addFooterView(R.layout.item_footer, mFooter);
        recyclerView.setAdapter(mHeaderFooterAdapter);
    }
}
