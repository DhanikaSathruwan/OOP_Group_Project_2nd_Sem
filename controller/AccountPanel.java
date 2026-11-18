package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.User;

public class AccountPanel extends JPanel {
   private JTextField txtFirstName;
   private JTextField txtLastName;
   private JTextField txtEmail;
   private JTextField txtNIC;
   private JTextField txtPhone;
   private JTextField txtCity;
   private JTextField txtAddress;
   private JTextField txtSchool;
   private JLabel lblProfilePic;
   private String currentPhotoPath = "";
   private UserController userController = new UserController();

   public AccountPanel() {
      this.setLayout(new BorderLayout(15, 15));
      this.setBackground(Color.WHITE);
      this.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
      JLabel lblTitle = new JLabel("MY PERSONAL ACCOUNT DETAILS", 0);
      lblTitle.setFont(new Font("Arial", 1, 22));
      lblTitle.setForeground(new Color(44, 62, 80));
      this.add(lblTitle, "North");
      JPanel photoPanel = new JPanel(new BorderLayout(10, 10));
      photoPanel.setBackground(Color.WHITE);
      photoPanel.setBorder(BorderFactory.createTitledBorder("My Profile Picture"));
      photoPanel.setPreferredSize(new Dimension(240, 280));
      this.lblProfilePic = new JLabel("No Photo", 0);
      this.lblProfilePic.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
      photoPanel.add(this.lblProfilePic, "Center");
      JButton btnChangePhoto = new JButton("Change My Photo");
      photoPanel.add(btnChangePhoto, "South");
      this.add(photoPanel, "West");
      JPanel formPanel = new JPanel(new GridLayout(8, 2, 12, 14));
      formPanel.setBackground(Color.WHITE);
      formPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
      formPanel.add(new JLabel("First Name:"));
      this.txtFirstName = new JTextField();
      formPanel.add(this.txtFirstName);
      formPanel.add(new JLabel("Last Name:"));
      this.txtLastName = new JTextField();
      formPanel.add(this.txtLastName);
      formPanel.add(new JLabel("Email Address (Logged In):"));
      this.txtEmail = new JTextField();
      this.txtEmail.setEditable(false);
      this.txtEmail.setBackground(new Color(235, 235, 235));
      formPanel.add(this.txtEmail);
      formPanel.add(new JLabel("NIC Number:"));
      this.txtNIC = new JTextField();
      this.txtNIC.setEditable(false);
      this.txtNIC.setBackground(new Color(235, 235, 235));
      formPanel.add(this.txtNIC);
      formPanel.add(new JLabel("Mobile Phone:"));
      this.txtPhone = new JTextField();
      formPanel.add(this.txtPhone);
      formPanel.add(new JLabel("School:"));
      this.txtSchool = new JTextField();
      formPanel.add(this.txtSchool);
      formPanel.add(new JLabel("City:"));
      this.txtCity = new JTextField();
      formPanel.add(this.txtCity);
      formPanel.add(new JLabel("Home Address:"));
      this.txtAddress = new JTextField();
      formPanel.add(this.txtAddress);
      this.add(formPanel, "Center");
      JPanel btnPanel = new JPanel();
      btnPanel.setBackground(Color.WHITE);
      JButton btnUpdate = new JButton("Save & Update My Details");
      btnUpdate.setBackground(new Color(39, 174, 96));
      btnUpdate.setBorderPainted(false);
      btnUpdate.setForeground(Color.WHITE);
      btnUpdate.setFont(new Font("Arial", 1, 14));
      btnPanel.add(btnUpdate);
      this.add(btnPanel, "South");
      btnChangePhoto.addActionListener((e) -> {
         JFileChooser chooser = new JFileChooser();
         if (chooser.showOpenDialog(this) == 0) {
            File f = chooser.getSelectedFile();
            this.currentPhotoPath = f.getAbsolutePath();
            this.displayPhoto(this.currentPhotoPath);
         }

      });
      btnUpdate.addActionListener((e) -> {
         User user = Session.getCurrentUser();
         if (user != null) {
            user.setFirstName(this.txtFirstName.getText().trim());
            user.setLastName(this.txtLastName.getText().trim());
            user.setMobile(this.txtPhone.getText().trim());
            user.setSchool(this.txtSchool.getText().trim());
            user.setCity(this.txtCity.getText().trim());
            user.setAddress(this.txtAddress.getText().trim());
            user.setPhotoPath(this.currentPhotoPath);
            if (this.userController.updateUserDetails(user)) {
               JOptionPane.showMessageDialog(this, "Your Account Details & Photo have been updated in the Database!");
            } else {
               JOptionPane.showMessageDialog(this, "Failed to update details!", "Error", 0);
            }
         }

      });
      this.loadLoggedInUserData();
   }

   public void loadLoggedInUserData() {
      User user = Session.getCurrentUser();
      if (user != null) {
         this.txtFirstName.setText(user.getFirstName());
         this.txtLastName.setText(user.getLastName());
         this.txtEmail.setText(user.getEmail());
         this.txtNIC.setText(user.getNic());
         this.txtPhone.setText(user.getMobile());
         this.txtSchool.setText(user.getSchool() != null ? user.getSchool() : "");
         this.txtCity.setText(user.getCity() != null ? user.getCity() : "");
         this.txtAddress.setText(user.getAddress() != null ? user.getAddress() : "");
         this.currentPhotoPath = user.getPhotoPath() != null ? user.getPhotoPath() : "";
         this.displayPhoto(this.currentPhotoPath);
      }

   }

   private void displayPhoto(String path) {
      if (path != null && !path.isEmpty() && (new File(path)).exists()) {
         ImageIcon icon = new ImageIcon(path);
         Image scaled = icon.getImage().getScaledInstance(190, 210, 4);
         this.lblProfilePic.setIcon(new ImageIcon(scaled));
         this.lblProfilePic.setText("");
      } else {
         this.lblProfilePic.setIcon((Icon)null);
         this.lblProfilePic.setText("<html><center>My Profile Photo<br>(" + path + ")</center></html>");
      }

   }
}
