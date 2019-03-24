package com.phongbm.buoi9.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.phongbm.buoi9.AppDatabase;
import com.phongbm.buoi9.R;
import com.phongbm.buoi9.Student;
import com.phongbm.buoi9.utils.ValidatorUtils;

public class EditStudentFragment extends BaseFragment implements View.OnClickListener {
    private EditText edtName;
    private EditText edtClass;
    private EditText edtScore;
    private Button btnOk;
    private Student student;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_student_edit;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        edtName = findViewById(R.id.edt_name);
        edtClass = findViewById(R.id.edt_class);
        edtScore = findViewById(R.id.edt_score);
        btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (ValidatorUtils.isInputEmpty(edtClass, edtName, edtScore)){
            return;
        }
        String name = edtName.getText().toString();
        String classRoom = edtClass.getText().toString();
        float score = Float.parseFloat(edtScore.getText().toString());
        boolean isInsert = false;
        if (student == null){
            isInsert = true;
            student = new Student();
        }
        student.setName(name);
        student.setClassRoom(classRoom);
        student.setScore(score);
        if (isInsert){
            AppDatabase.getInstance(getContext())
                    .getStudentDao().insert(student);
        }else{
            AppDatabase.getInstance(getContext())
                    .getStudentDao().update(student);
        }
        getParent().showFragment(getParent().getFmStudent());
        getParent().getFmStudent().getData();
    }

    public void setStudent(Student student) {
        this.student = student;
        if (student != null){
            edtName.setText(student.getName());
            edtScore.setText(student.getScore()+"");
            edtClass.setText(student.getClassRoom());
        }else{
            edtName.setText("");
            edtScore.setText("");
            edtClass.setText("");
        }
    }
}
