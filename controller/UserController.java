package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ClassItem;
import model.DBConnection;
import model.User;

public class UserController {
   public User loginUser(String email, String password) {
      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email.trim());
            pst.setString(2, password.trim());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               return new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("nic"), rs.getString("target_year"), rs.getString("mobile"), rs.getString("email"), rs.getString("institute"), rs.getString("photo_path"), rs.getString("city"), rs.getString("address"), rs.getString("school"), rs.getString("role"));
            }
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return null;
   }

   public boolean registerUser(User user, String password) {
      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "INSERT INTO users (first_name, last_name, nic, target_year, mobile, email, institute, password, photo_path, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'STUDENT')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getNic());
            pst.setString(4, user.getTargetYear());
            pst.setString(5, user.getMobile());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getInstitute());
            pst.setString(8, password);
            pst.setString(9, user.getPhotoPath());
            return pst.executeUpdate() > 0;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return false;
   }

   public boolean updateUserDetails(User user) {
      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "UPDATE users SET first_name=?, last_name=?, mobile=?, city=?, address=?, school=?, photo_path=? WHERE email=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getMobile());
            pst.setString(4, user.getCity());
            pst.setString(5, user.getAddress());
            pst.setString(6, user.getSchool());
            pst.setString(7, user.getPhotoPath());
            pst.setString(8, user.getEmail());
            return pst.executeUpdate() > 0;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return false;
   }

   public List<ClassItem> getFilteredClasses(String subject, String targetYear) {
      List<ClassItem> list = new ArrayList();

      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "SELECT * FROM classes WHERE subject=? AND target_year=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, subject);
            pst.setString(2, targetYear);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
               list.add(new ClassItem(rs.getInt("class_id"), rs.getString("subject"), rs.getString("target_year"), rs.getString("lesson_month"), rs.getString("class_time"), rs.getString("fee")));
            }
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return list;
   }

   public boolean savePayment(String email, String subject, String year, String lesson, String method, String amount, String slipPath) {
      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "INSERT INTO payments (user_email, payment_date, subject, target_year, lesson_name, payment_method, amount, slip_path, status) VALUES (?, CURDATE(), ?, ?, ?, ?, ?, ?, 'Enrolled / Slip Uploaded')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, subject);
            pst.setString(3, year);
            pst.setString(4, lesson);
            pst.setString(5, method);
            pst.setString(6, amount);
            pst.setString(7, slipPath);
            return pst.executeUpdate() > 0;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return false;
   }

   public List<Object[]> getUserPayments(String email) {
      List<Object[]> list = new ArrayList();

      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "SELECT payment_date, subject, target_year, lesson_name, payment_method, amount, status FROM payments WHERE user_email=? ORDER BY payment_id DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
               Object[] var10001 = new Object[]{rs.getString("payment_date"), null, null, null, null, null};
               String var10004 = rs.getString("subject");
               var10001[1] = var10004 + " (" + rs.getString("target_year") + ")";
               var10001[2] = rs.getString("lesson_name");
               var10001[3] = rs.getString("payment_method");
               var10001[4] = rs.getString("amount");
               var10001[5] = rs.getString("status");
               list.add(var10001);
            }
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return list;
   }

   public List<Object[]> getAllUsersForAdmin() {
      List<Object[]> list = new ArrayList();

      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "SELECT id, first_name, last_name, nic, target_year, mobile, email, institute, city, role FROM users ORDER BY id DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
               Object[] var10001 = new Object[]{rs.getInt("id"), null, null, null, null, null, null, null, null};
               String var10004 = rs.getString("first_name");
               var10001[1] = var10004 + " " + rs.getString("last_name");
               var10001[2] = rs.getString("nic");
               var10001[3] = rs.getString("target_year");
               var10001[4] = rs.getString("mobile");
               var10001[5] = rs.getString("email");
               var10001[6] = rs.getString("institute");
               var10001[7] = rs.getString("city");
               var10001[8] = rs.getString("role");
               list.add(var10001);
            }
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return list;
   }

   public List<Object[]> getAllPaymentsForAdmin() {
      List<Object[]> list = new ArrayList();

      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "SELECT payment_id, user_email, payment_date, subject, target_year, lesson_name, amount, slip_path, status FROM payments ORDER BY payment_id DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
               list.add(new Object[]{rs.getInt("payment_id"), rs.getString("user_email"), rs.getString("payment_date"), rs.getString("subject"), rs.getString("target_year"), rs.getString("lesson_name"), rs.getString("amount"), rs.getString("slip_path"), rs.getString("status")});
            }
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return list;
   }

   public boolean updatePaymentStatusByAdmin(int paymentId, String newStatus) {
      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "UPDATE payments SET status=? WHERE payment_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newStatus);
            pst.setInt(2, paymentId);
            return pst.executeUpdate() > 0;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return false;
   }

   public List<Object[]> getAllClassesForAdmin() {
      List<Object[]> list = new ArrayList();

      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "SELECT * FROM classes ORDER BY class_id DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
               list.add(new Object[]{rs.getInt("class_id"), rs.getString("subject"), rs.getString("target_year"), rs.getString("lesson_month"), rs.getString("class_time"), rs.getString("fee")});
            }
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return list;
   }

   public boolean addNewClassByAdmin(String subject, String year, String lesson, String time, String fee) {
      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "INSERT INTO classes (subject, target_year, lesson_month, class_time, fee) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, subject);
            pst.setString(2, year);
            pst.setString(3, lesson);
            pst.setString(4, time);
            pst.setString(5, fee);
            return pst.executeUpdate() > 0;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return false;
   }

   public boolean updateClassByAdmin(int classId, String subject, String year, String lesson, String time, String fee) {
      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "UPDATE classes SET subject=?, target_year=?, lesson_month=?, class_time=?, fee=? WHERE class_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, subject);
            pst.setString(2, year);
            pst.setString(3, lesson);
            pst.setString(4, time);
            pst.setString(5, fee);
            pst.setInt(6, classId);
            return pst.executeUpdate() > 0;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return false;
   }

   public boolean deleteClassByAdmin(int classId) {
      try {
         Connection conn = DBConnection.getConnection();
         if (conn != null) {
            String sql = "DELETE FROM classes WHERE class_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, classId);
            return pst.executeUpdate() > 0;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return false;
   }
}
