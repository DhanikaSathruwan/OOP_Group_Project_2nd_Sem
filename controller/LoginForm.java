package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.User;

public class LoginForm extends JFrame {
   private JTextField txtEmail;
   private JPasswordField txtPassword;
   private UserController userController = new UserController();

   public LoginForm() {
      this.setTitle("Education App - System Login");
      this.setSize(450, 620);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      this.setLayout(new BorderLayout());
      JPanel headerPanel = new JPanel(new BorderLayout());
      headerPanel.setBackground(new Color(25, 42, 86));
      headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
      JLabel lblPhotoBanner = new JLabel(new ImageIcon("login_placeholder.png"));
      lblPhotoBanner.setHorizontalAlignment(0);
      headerPanel.add(lblPhotoBanner, "North");
      JLabel lblTitle = new JLabel("STUDENT & ADMIN LOGIN", 0);
      lblTitle.setFont(new Font("Arial", 1, 22));
      lblTitle.setForeground(Color.WHITE);
      headerPanel.add(lblTitle, "Center");
      this.add(headerPanel, "North");
      JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
      formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
      formPanel.setBackground(Color.WHITE);
      JLabel lblEmail = new JLabel("Email Address:");
      lblEmail.setFont(new Font("Arial", 1, 14));
      formPanel.add(lblEmail);
      this.txtEmail = new JTextField();
      this.txtEmail.setFont(new Font("Arial", 0, 14));
      formPanel.add(this.txtEmail);
      JLabel lblPass = new JLabel("Password:");
      lblPass.setFont(new Font("Arial", 1, 14));
      formPanel.add(lblPass);
      this.txtPassword = new JPasswordField();
      this.txtPassword.setFont(new Font("Arial", 0, 14));
      formPanel.add(this.txtPassword);
      JButton btnLogin = new JButton("LOGIN TO SYSTEM");
      btnLogin.setBackground(new Color(0, 0, 128));
      btnLogin.setBorderPainted(false);
      btnLogin.setForeground(Color.WHITE);
      btnLogin.setFont(new Font("Arial", 1, 15));
      formPanel.add(btnLogin);
      JButton btnSignup = new JButton("New Student? Click Here to SIGN UP");
      btnSignup.setForeground(new Color(192, 57, 43));
      btnSignup.setFont(new Font("Arial", 1, 13));
      btnSignup.setContentAreaFilled(false);
      btnSignup.setBorderPainted(false);
      formPanel.add(btnSignup);
      this.add(formPanel, "Center");
      btnLogin.addActionListener((e) -> {
         String email = this.txtEmail.getText().trim();
         String pwd = (new String(this.txtPassword.getPassword())).trim();
         if (!email.isEmpty() && !pwd.isEmpty()) {
            User loggedInUser = this.userController.loginUser(email, pwd);
            if (loggedInUser != null) {
               Session.setCurrentUser(loggedInUser);
               this.dispose();
               if ("ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
                  JOptionPane.showMessageDialog((Component)null, "Welcome Admin: " + loggedInUser.getFirstName());
                  (new AdminDashboard()).setVisible(true);
               } else {
                  (new MainForm()).setVisible(true);
               }
            } else {
               JOptionPane.showMessageDialog(this, "Invalid Email or Password! Access Denied.\nPlease try again or Sign Up first.", "Login Failed - Try Again", 0);
               this.txtPassword.setText("");
               this.txtPassword.requestFocus();
            }

         } else {
            JOptionPane.showMessageDialog(this, "Please enter both Email Address and Password!", "Missing Details", 2);
         }
      });
      btnSignup.addActionListener((e) -> {
         this.dispose();
         (new SignUpForm()).setVisible(true);
      });
   }
}
