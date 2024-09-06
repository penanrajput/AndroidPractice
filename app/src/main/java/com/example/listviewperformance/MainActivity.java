package com.example.listviewperformance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//     1. declare lvCourses and courses(get all Random Records)
    ListView lvCourses;
    ArrayList<Course> courses = Course.generateNRandomCourse(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Create Custom Adapter object
        CourseAdapter courseAdapter = new CourseAdapter();

        // get Id of our activity_main.xml textbox Id and attach custom Adapter to it
        lvCourses = findViewById(R.id.lvCourses);
        lvCourses.setAdapter(courseAdapter);


        }

//     2. Create Custom Adapter "Course Adapter" extending BaseAdapter and
//     implement its 4 abstract methods
//     for 1st getCount() : return int, so return courses.size()
//     for 2nd getItem() : return "Course" (VERY IMP), so return course object at index=position
//     for 3rd getItemId() : do nothing here
//     for 4th getView() : returns "View"
//     so inside it a. create view and inflate it with our list_item_cours layout
//     b. Create object of Course at index = position
//     c. get the list of ids from out list_item_course Layout and set the values from step 2
//     d. return view

    class CourseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return courses.size();
        }

        @Override
        public Course getItem(int position) {
            return courses.get(position);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Log.d("LISTVIEW", "position=" + position + " convertView=" + convertView);

            CourseViewHolder holder;

            // if convertView is "not exist" = null then create View
            if(convertView == null)
            {
                convertView = getLayoutInflater().inflate(
                        R.layout.list_item_course,
                        parent,
                        false
                );

                holder = new CourseViewHolder(convertView);
                convertView.setTag(holder);
            }
            // if convertview exists -> then re-use it
            else {
                holder = (CourseViewHolder) convertView.getTag();
            }

            // Creating course from current position
            Course course = getItem(position);

            // attaching the coursename, teacher, lectures
            holder.tvCourseName.setText(course.getName());
            holder.tvTeacherName.setText(course.getTeacherName());
            holder.tvLectures.setText(String.valueOf(course.getLectures()));

            return convertView;
        }

        class CourseViewHolder{
            TextView tvCourseName;
            TextView tvTeacherName;
            TextView tvLectures;

            public CourseViewHolder(View convertView) {
                this.tvCourseName = convertView.findViewById(R.id.tvCourseName);
                this.tvTeacherName = convertView.findViewById(R.id.tvTeacherName);
                this.tvLectures = convertView.findViewById(R.id.tvLectures);
            }
        }
    }
}