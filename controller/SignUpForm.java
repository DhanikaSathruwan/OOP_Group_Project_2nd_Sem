package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.User;

public class SignUpForm extends JFrame {
   private JTextField txtFN;
   private JTextField txtLN;
   private JTextField txtNIC;
   private JTextField txtMobile;
   private JTextField txtEmail;
   private JTextField txtOTP;
   private JComboBox<String> cbYear;
   private JComboBox<String> cbInstitute;
   private JPasswordField txtPassword;
   private JLabel lblSelectedPhoto;
   private String selectedPhotoPath = "profile_placeholder.png";
   private UserController userController = new UserController();

   public SignUpForm() {
      this.setTitle("Sign Up - New Student Registration");
      this.setSize(550, 760);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      this.setLayout(new BorderLayout());
      JPanel headerPanel = new JPanel(new BorderLayout());
      headerPanel.setBackground(new Color(39, 174, 96));
      headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
      JLabel lblHeaderIcon = new JLabel(new ImageIcon("signup_header_photo.png"));
      headerPanel.add(lblHeaderIcon, "West");
      JLabel lblTitle = new JLabel("CREATE NEW STUDENT ACCOUNT", 0);
      lblTitle.setFont(new Font("Arial", 1, 20));
      lblTitle.setForeground(Color.WHITE);
      headerPanel.add(lblTitle, "Center");
      this.add(headerPanel, "North");
      JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 12));
      formPanel.setBorder(BorderFactory.createEmptyBorder(20, 35, 20, 35));
      formPanel.setBackground(Color.WHITE);
      formPanel.add(new JLabel("First Name: *"));
      this.txtFN = new JTextField();
      formPanel.add(this.txtFN);
      formPanel.add(new JLabel("Last Name: *"));
      this.txtLN = new JTextField();
      formPanel.add(this.txtLN);
      formPanel.add(new JLabel("NIC Number: *"));
      this.txtNIC = new JTextField();
      formPanel.add(this.txtNIC);
      formPanel.add(new JLabel("Target Year: *"));
      this.cbYear = new JComboBox(new String[]{"2026", "2027", "2028"});
      formPanel.add(this.cbYear);
      formPanel.add(new JLabel("Mobile Number: *"));
      this.txtMobile = new JTextField();
      formPanel.add(this.txtMobile);
      formPanel.add(new JLabel("Email Address: *"));
      this.txtEmail = new JTextField();
      formPanel.add(this.txtEmail);
      formPanel.add(new JLabel("Email OTP (Verification):"));
      this.txtOTP = new JTextField();
      formPanel.add(this.txtOTP);
      formPanel.add(new JLabel("Institute: *"));
      this.cbInstitute = new JComboBox(new String[]{"A Positive Badulla", "Other"});
      formPanel.add(this.cbInstitute);
      formPanel.add(new JLabel("Create Password: *"));
      this.txtPassword = new JPasswordField();
      formPanel.add(this.txtPassword);
      JPanel photoPanel = new JPanel(new FlowLayout(0, 5, 0));
      photoPanel.setBackground(Color.WHITE);
      JButton btnUploadPhoto = new JButton("Choose Photo");
      this.lblSelectedPhoto = new JLabel("No file chosen");
      this.lblSelectedPhoto.setFont(new Font("Arial", 2, 11));
      photoPanel.add(btnUploadPhoto);
      photoPanel.add(this.lblSelectedPhoto);
      btnUploadPhoto.addActionListener((e) -> {
         JFileChooser chooser = new JFileChooser();
         if (chooser.showOpenDialog(this) == 0) {
            File file = chooser.getSelectedFile();
            this.selectedPhotoPath = file.getAbsolutePath();
            this.lblSelectedPhoto.setText(file.getName());
         }

      });
      formPanel.add(new JLabel("Profile Photo:"));
      formPanel.add(photoPanel);
      this.add(formPanel, "Center");
      JPanel btnPanel = new JPanel(new FlowLayout(1, 20, 15));
      btnPanel.setBackground(new Color(245, 246, 250));
      JButton btnBack = new JButton("Back to Login");
      JButton btnSignUp = new JButton("Complete Registration");
      btnSignUp.setBackground(new Color(39, 174, 96));
      btnSignUp.setBorderPainted(false);
      btnSignUp.setForeground(Color.WHITE);
      btnSignUp.setFont(new Font("Arial", 1, 14));
      btnPanel.add(btnBack);
      btnPanel.add(btnSignUp);
      this.add(btnPanel, "South");
      btnBack.addActionListener((e) -> {
         this.dispose();
         (new LoginForm()).setVisible(true);
      });
      btnSignUp.addActionListener((e) -> {
         String fn = this.txtFN.getText().trim();
         String ln = this.txtLN.getText().trim();
         String nic = this.txtNIC.getText().trim();
         String mobile = this.txtMobile.getText().trim();
         String email = this.txtEmail.getText().trim();
         String pwd = (new String(this.txtPassword.getPassword())).trim();
         if (!fn.isEmpty() && !ln.isEmpty() && !nic.isEmpty() && !mobile.isEmpty() && !email.isEmpty() && !pwd.isEmpty()) {
            User newUser = new User(fn, ln, nic, this.cbYear.getSelectedItem().toString(), mobile, email, this.cbInstitute.getSelectedItem().toString(), this.selectedPhotoPath);
            boolean success = this.userController.registerUser(newUser, pwd);
            if (success) {
               JOptionPane.showMessageDialog(this, "Registration Successful!\nNow please log in using your Email and Password.", "Success - Redirecting to Login", 1);
               this.dispose();
               (new LoginForm()).setVisible(true);
            } else {
               JOptionPane.showMessageDialog(this, "Registration Failed! This Email may already exist or Database is disconnected.\nPlease check your details and try again.", "Sign Up Error", 0);
            }

         } else {
            JOptionPane.showMessageDialog(this, "Please fill all required (*) fields correctly! Cannot proceed with empty details.", "Validation Error", 2);
         }
      });
   }
}
