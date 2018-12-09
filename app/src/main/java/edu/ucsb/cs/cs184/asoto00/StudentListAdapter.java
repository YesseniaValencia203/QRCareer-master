package edu.ucsb.cs.cs184.asoto00;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder>  {
    // needs to be private ArrayList<StudentUser> allStudents;
    private ArrayList<String> allStudents;
    // StudentDialogFragment needs to be implemented correctly.
    private StudentDialogFragment dialogFragment;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView studentName;
        private final TextView studentPhone;
        private final ImageButton deleteButton;

        public ViewHolder(View view) {
            super(view);
            studentName = view.findViewById(R.id.student_name_row);
            studentPhone = view.findViewById(R.id.student_phone_row);
            deleteButton = view.findViewById(R.id.deleteRowButton);

        }


        public TextView getStudentNameView() {
            return studentName;
        }
        public TextView getStudentPhoneView() { return studentPhone; }
        public ImageButton getDeleteButtonView() { return deleteButton; }


    }
    public StudentListAdapter(ArrayList<String> students) {
        allStudents = new ArrayList<String>();
        allStudents = students;

    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_row_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.getStudentNameView().setText(allStudents.get(position));
    //  viewHolder.getStudentPhoneView().setText(allStudents.get(position).SPhone);
        viewHolder.getDeleteButtonView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStudents.remove(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return allStudents.size();
    }

}
