package in.cdac.courseMaster;
import in.cdac.courseMaster.CourseMaster;
public class RunCourse {
    public static void main(String[] args) {
        CourseMaster obj = new CourseMaster();
        String imagePath="image\\savedImage.jpg";
        obj.insert(1, "C-DAC", "image\\savedImage.jpg", "Full stop developement", "B3", "Saurav Patil");
        obj.select_all();
        obj.update("course_batch", 1, "B9");
        obj.delete(1);

    }
}
