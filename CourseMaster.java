package in.cdac.courseMaster;

import database.Database;
import java.sql.*;
import java.io.*;

public class CourseMaster {
    public void select_all() {
        String sqlQuery = "select * from course_master";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            ResultSet rs = pstmt.executeQuery();
            FileOutputStream fileOutputStream = new FileOutputStream("image\\savedImage.jpg");
            Blob clob = rs.getBlob(2);
            while (rs.next()) {
                int course_id = rs.getInt(1);
                String course_name = rs.getString(2);
                byte[] byteArr = clob.getBytes(1, (int) clob.length());
                fileOutputStream.write(byteArr);
                String course_details = rs.getString(4);
                String course_batch = rs.getString(5);
                String submitted_by = rs.getString(5);
                System.out.println(course_id + " - " + course_name + " - " + course_details + " - " + course_batch + " - " + submitted_by);
            }
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();///saurav
        }
    }

    public void insert(int course_id,String course_name,String imagePath,String course_details, String course_batch, String submitted_by) {
        String sql = "INSERT INTO course_master VALUES (?, ?, ?, ?, ?, ?)";
        FileInputStream fis;

        try {
            Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, course_id);
            pstmt.setString(2, course_name);
            File image=new File(imagePath);
            fis=new FileInputStream(image);

            pstmt.setBinaryStream(3, (InputStream)fis, (int)(image.length()));


            pstmt.setString(4, course_details);
            pstmt.setString(5, course_batch);
            pstmt.setString(6, submitted_by);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new Course was inserted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(String column,int course_id,String value) {
        String sqlUpdate = "UPDATE course_master set "+ column + "= ? where course_id = ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            // Substitute 3 values

            pstmt.setString(1, value);
            pstmt.setInt(2, course_id);
            

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Course was updated successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void delete(int course_id) {
        String sql = "DELETE FROM course_master WHERE course_id=?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, course_id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
