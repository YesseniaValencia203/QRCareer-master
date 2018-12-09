package edu.ucsb.cs.cs184.asoto00;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> implements View.OnClickListener {
//    private ArrayList<StudentUser> allStudents;
  private ArrayList<String> allStudents;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView studentName;
        private final ImageButton deleteButton;
        //private final TextView studentYear;

        public ViewHolder(View view) {
            super(view);
            studentName = view.findViewById(R.id.student_name_row);
            deleteButton = view.findViewById(R.id.deleteRowButton);

         //   studentYear = view.findViewById(R.id.student_year_row);

        }


        public TextView getStudentNameView() {
            return studentName;
        }
        public ImageButton getDeleteButtonView() { return deleteButton; }

       // public TextView getStudentYearView() {
          //  return studentYear;
      //  }
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
        viewHolder.getDeleteButtonView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStudents.remove(position);
            }
        });


//        viewHolder.getStudentYearView().setText(allStudents.get(position).GradYear);
    }
    @Override
    public int getItemCount() {
        return allStudents.size();
    }

    @Override
    public void onClick(View v) {
    }
}
