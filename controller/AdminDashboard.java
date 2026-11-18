package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminDashboard extends JFrame {
   private UserController userController = new UserController();
   private DefaultTableModel usersModel;
   private DefaultTableModel paymentsModel;
   private DefaultTableModel classesModel;
   private JTable tblUsers;
   private JTable tblPayments;
   private JTable tblClasses;
   private JTextField txtClassId;
   private JTextField txtLesson;
   private JTextField txtTime;
   private JTextField txtFee;
   private JComboBox<String> cbSubject;
   private JComboBox<String> cbTargetYear;

   public AdminDashboard() {
      this.setTitle("SYSTEM ADMINISTRATOR DASHBOARD - Manage Students, Payments & Classes");
      this.setSize(1150, 750);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      this.setLayout(new BorderLayout());
      JPanel headerPanel = new JPanel(new BorderLayout());
      headerPanel.setBackground(new Color(44, 62, 80));
      headerPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
      JLabel lblAdminTitle = new JLabel("ADMIN CONTROL PANEL - Full System Management");
      lblAdminTitle.setIcon(new ImageIcon("admin_logo_placeholder.png"));
      lblAdminTitle.setFont(new Font("Arial", 1, 20));
      lblAdminTitle.setForeground(Color.WHITE);
      headerPanel.add(lblAdminTitle, "West");
      JButton btnLogout = new JButton("Logout Admin");
      btnLogout.setBackground(new Color(236, 245, 64));
      btnLogout.setBorderPainted(false);
      btnLogout.setForeground(Color.BLACK);
      headerPanel.add(btnLogout, "East");
      this.add(headerPanel, "North");
      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setFont(new Font("Arial", 1, 14));
      JPanel classPanel = new JPanel(new BorderLayout(10, 10));
      classPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      String[] classCols = new String[]{"Class ID", "Subject", "Target Year", "Lesson / Month", "Class Time", "Fee"};
      this.classesModel = new DefaultTableModel(classCols, 0);
      this.tblClasses = new JTable(this.classesModel);
      this.tblClasses.setRowHeight(28);
      classPanel.add(new JScrollPane(this.tblClasses), "Center");
      JPanel editClassPanel = new JPanel(new GridLayout(3, 4, 10, 10));
      editClassPanel.setBorder(BorderFactory.createTitledBorder("Add New Class or Edit Selected Class Time / Target Year / Fee"));
      editClassPanel.add(new JLabel("Selected Class ID:"));
      this.txtClassId = new JTextField();
      this.txtClassId.setEditable(true);
      editClassPanel.add(this.txtClassId);
      editClassPanel.add(new JLabel("Subject:"));
      this.cbSubject = new JComboBox(new String[]{"AL SFT (Science for Technology)", "AL ICT (Information Comm. Tech.)","AL ET (Engineering Tech.)","AL BST (Bio System Tech.)"});
      editClassPanel.add(this.cbSubject);
      editClassPanel.add(new JLabel("Target Year:"));
      this.cbTargetYear = new JComboBox(new String[]{"2026", "2027", "2028"});
      editClassPanel.add(this.cbTargetYear);
      editClassPanel.add(new JLabel("Lesson / Month:"));
      this.txtLesson = new JTextField();
      editClassPanel.add(this.txtLesson);
      editClassPanel.add(new JLabel("Class Time (e.g. Sat 8-12 AM):"));
      this.txtTime = new JTextField();
      editClassPanel.add(this.txtTime);
      editClassPanel.add(new JLabel("Class Fee (e.g. Rs. 2500):"));
      this.txtFee = new JTextField();
      editClassPanel.add(this.txtFee);
      JButton btnAddClass = new JButton("Add New Class");
      btnAddClass.setBackground(new Color(39, 174, 96));
      btnAddClass.setBorderPainted(false);
      btnAddClass.setForeground(Color.WHITE);
      JButton btnUpdateClass = new JButton("Update Selected Class");
      btnUpdateClass.setBackground(new Color(41, 128, 185));
      btnUpdateClass.setBorderPainted(false);
      btnUpdateClass.setForeground(Color.WHITE);
      JButton btnDeleteClass = new JButton("Delete Class");
      btnDeleteClass.setBackground(new Color(192, 57, 43));
      btnDeleteClass.setBorderPainted(false);
      btnDeleteClass.setForeground(Color.WHITE);
      JPanel actionBtnBox = new JPanel(new FlowLayout());
      actionBtnBox.add(btnAddClass);
      actionBtnBox.add(btnUpdateClass);
      actionBtnBox.add(btnDeleteClass);
      JPanel southClassContainer = new JPanel(new BorderLayout());
      southClassContainer.add(editClassPanel, "Center");
      southClassContainer.add(actionBtnBox, "South");
      classPanel.add(southClassContainer, "South");
      tabbedPane.addTab("1. Manage Classes, Times & Target Years", classPanel);

      javax.swing.JLabel tab1Label = new javax.swing.JLabel("1. Manage Classes, Times & Target Years");
      tab1Label.setOpaque(true);
      tab1Label.setBackground(new Color(173, 216, 230));
      tab1Label.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
      tabbedPane.setTabComponentAt(0, tab1Label);

      JPanel payPanel = new JPanel(new BorderLayout(10, 10));
      payPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      String[] payCols = new String[]{"Payment ID", "Student Email", "Date", "Subject", "Target Year", "Lesson", "Amount", "Slip Path", "Status"};
      this.paymentsModel = new DefaultTableModel(payCols, 0);
      this.tblPayments = new JTable(this.paymentsModel);
      this.tblPayments.setRowHeight(28);
      payPanel.add(new JScrollPane(this.tblPayments), "Center");
      JPanel payActionPanel = new JPanel(new FlowLayout());
      JButton btnApproveSlip = new JButton("Approve Selected Payment (Mark Enrolled)");
      btnApproveSlip.setBackground(new Color(39, 174, 96));
      btnApproveSlip.setBorderPainted(false);
      btnApproveSlip.setForeground(Color.WHITE);
      JButton btnRejectSlip = new JButton("Reject Invalid Slip");
      btnRejectSlip.setBackground(new Color(192, 57, 43));
      btnRejectSlip.setBorderPainted(false);
      btnRejectSlip.setForeground(Color.WHITE);
      JButton btnRefreshPay = new JButton("Refresh Payments");
      payActionPanel.add(btnApproveSlip);
      payActionPanel.add(btnRejectSlip);
      payActionPanel.add(btnRefreshPay);
      payPanel.add(payActionPanel, "South");
      tabbedPane.addTab("2. All Student Payments & Bank Slips", payPanel);

      javax.swing.JLabel tab2Label = new javax.swing.JLabel("2. All Student Payments & Bank Slips");
      tab2Label.setOpaque(true);
      tab2Label.setBackground(new Color(144, 238, 144));
      tab2Label.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
      tabbedPane.setTabComponentAt(1, tab2Label);

      JPanel usersPanel = new JPanel(new BorderLayout(10, 10));
      usersPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      String[] userCols = new String[]{"ID", "Full Name", "NIC", "Target Year", "Mobile", "Email", "Institute", "City", "Role"};
      this.usersModel = new DefaultTableModel(userCols, 0);
      this.tblUsers = new JTable(this.usersModel);
      this.tblUsers.setRowHeight(28);
      usersPanel.add(new JScrollPane(this.tblUsers), "Center");
      JButton btnRefreshUsers = new JButton("Refresh Registered Students List");
      usersPanel.add(btnRefreshUsers, "South");
      tabbedPane.addTab("3. All Signed-Up Student Accounts", usersPanel);

      javax.swing.JLabel tab3Label = new javax.swing.JLabel("3. All Signed-Up Student Accounts");
      tab3Label.setOpaque(true);
      tab3Label.setBackground(new Color(255, 255, 153));
      tab3Label.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
      tabbedPane.setTabComponentAt(2, tab3Label);

      this.add(tabbedPane, "Center");
      this.tblClasses.getSelectionModel().addListSelectionListener((e) -> {
         int row = this.tblClasses.getSelectedRow();
         if (row >= 0) {
            this.txtClassId.setText(this.classesModel.getValueAt(row, 0).toString());
            this.cbSubject.setSelectedItem(this.classesModel.getValueAt(row, 1).toString());
            this.cbTargetYear.setSelectedItem(this.classesModel.getValueAt(row, 2).toString());
            this.txtLesson.setText(this.classesModel.getValueAt(row, 3).toString());
            this.txtTime.setText(this.classesModel.getValueAt(row, 4).toString());
            this.txtFee.setText(this.classesModel.getValueAt(row, 5).toString());
         }

      });
      btnAddClass.addActionListener((e) -> {
         if (this.userController.addNewClassByAdmin(this.cbSubject.getSelectedItem().toString(), this.cbTargetYear.getSelectedItem().toString(), this.txtLesson.getText().trim(), this.txtTime.getText().trim(), this.txtFee.getText().trim())) {
            JOptionPane.showMessageDialog(this, "New Class Added Successfully!");
            this.loadAllAdminData();
            this.txtClassId.setText("");
            this.txtLesson.setText("");
            this.txtTime.setText("");
            this.txtFee.setText("");
         }

      });
      btnUpdateClass.addActionListener((e) -> {
         if (this.txtClassId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a class from the table first!");
         } else {
            int id = Integer.parseInt(this.txtClassId.getText());
            if (this.userController.updateClassByAdmin(id, this.cbSubject.getSelectedItem().toString(), this.cbTargetYear.getSelectedItem().toString(), this.txtLesson.getText().trim(), this.txtTime.getText().trim(), this.txtFee.getText().trim())) {
               JOptionPane.showMessageDialog(this, "Class Details Updated Successfully!");
               this.loadAllAdminData();
            }

         }
      });
      btnDeleteClass.addActionListener((e) -> {
         if (!this.txtClassId.getText().isEmpty()) {
            int id = Integer.parseInt(this.txtClassId.getText());
            if (this.userController.deleteClassByAdmin(id)) {
               JOptionPane.showMessageDialog(this, "Class Deleted!");
               this.txtClassId.setText("");
               this.loadAllAdminData();
            }

         }
      });
      btnApproveSlip.addActionListener((e) -> {
         int row = this.tblPayments.getSelectedRow();
         if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a payment record from the table first!");
         } else {
            int payId = Integer.parseInt(this.paymentsModel.getValueAt(row, 0).toString());
            if (this.userController.updatePaymentStatusByAdmin(payId, "Approved & Enrolled")) {
               JOptionPane.showMessageDialog(this, "Payment Approved!");
               this.loadAllAdminData();
            }

         }
      });
      btnRejectSlip.addActionListener((e) -> {
         int row = this.tblPayments.getSelectedRow();
         if (row >= 0) {
            int payId = Integer.parseInt(this.paymentsModel.getValueAt(row, 0).toString());
            if (this.userController.updatePaymentStatusByAdmin(payId, "Rejected - Contact Admin")) {
               JOptionPane.showMessageDialog(this, "Payment Marked as Rejected!");
               this.loadAllAdminData();
            }

         }
      });
      btnRefreshPay.addActionListener((e) -> this.loadAllAdminData());
      btnRefreshUsers.addActionListener((e) -> this.loadAllAdminData());
      btnLogout.addActionListener((e) -> {
         Session.clearSession();
         this.dispose();
         (new LoginForm()).setVisible(true);
      });
      this.loadAllAdminData();
   }

   private void loadAllAdminData() {
      this.classesModel.setRowCount(0);

      for(Object[] r : this.userController.getAllClassesForAdmin()) {
         this.classesModel.addRow(r);
      }

      this.paymentsModel.setRowCount(0);

      for(Object[] r : this.userController.getAllPaymentsForAdmin()) {
         this.paymentsModel.addRow(r);
      }

      this.usersModel.setRowCount(0);

      for(Object[] r : this.userController.getAllUsersForAdmin()) {
         this.usersModel.addRow(r);
      }

   }
}
