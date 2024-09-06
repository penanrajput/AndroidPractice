import java.util.ArrayList;
import java.util.Random;

public class Course {

    String name;
    String teacherName;
    int lectures;

    public Course(String name, String teacherName, int lectures) {
        this.name = name;
        this.teacherName = teacherName;
        this.lectures = lectures;
    }

    public String getName() {
        return name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getLectures() {
        return lectures;
    }
    public static final String[] teachers = {
      "A", "B", "C", "D", "E"
    };

    public static final String[]  courseNames = {
      "Android", "C++", "Java", "C", "SpringBoot", "Angular"
    };

    public static ArrayList<Course> generateNRandomCourse(int n)
    {
        Random r = new Random();
        ArrayList<Course> courses = new ArrayList<Course>();
        for(int i=0; i <n; i++)
        {
            Course course = new Course(
                    teachers[r.nextInt(6)],
                    courseNames[r.nextInt(6)],
                    10 + r.nextInt(10)
            ); // calls constructor of Course(teacher, course, #lecures)
            courses.add(course);
        }

        return courses;
    }
}


