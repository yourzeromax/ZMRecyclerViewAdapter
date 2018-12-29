package com.yourzeromax.zmrecyclerviewadapter.utils;

/* *
 * Created by yourzeromax
 * on 2018/12/29
 *
 *
 */

import com.yourzeromax.zmrecyclerviewadapter.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentUtils {
    public static List<Student> getStudentsData(int dataLength) {
        List<Student> data = new ArrayList<>();
        for (int i = 0; i < dataLength; i++) {
            data.add(new Student("yuzhimou  " + i, 20 + i, 20144444));
        }
        return data;
    }

    public static List<Student> getMultiStudentsData(int dataLength) {
        List<Student> data = new ArrayList<>();
        for (int i = 0; i < dataLength; i++) {
            Student student = new Student("yuzhimou  " + i, 20 + i, 20144444);
            if (i % 2 == 0) {
                student.setMulti(true);
            } else {
                student.setMulti(false);
            }
            data.add(student);
        }
        return data;
    }
}
