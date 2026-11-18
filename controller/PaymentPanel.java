package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.User;

public class PaymentPanel extends JPanel {
   private JTable table;
   private DefaultTableModel tableModel;
   private UserController userController = new UserController();
   private JLabel lblSubHeader;

   public PaymentPanel() {
      this.setLayout(new BorderLayout(10, 10));
      this.setBackground(Color.WHITE);
      this.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
      JPanel topPanel = new JPanel(new GridLayout(2, 1));
      topPanel.setBackground(Color.WHITE);
      JLabel lblTitle = new JLabel("MY PAYMENT & ENROLLMENT HISTORY", 0);
      lblTitle.setFont(new Font("Arial", 1, 22));
      topPanel.add(lblTitle);
      this.lblSubHeader = new JLabel("", 0);
      this.lblSubHeader.setFont(new Font("Arial", 2, 13));
      this.lblSubHeader.setForeground(new Color(41, 128, 185));
      topPanel.add(this.lblSubHeader);
      this.add(topPanel, "North");
      String[] columns = new String[]{"Date", "Subject & Target Year", "Enrolled Lesson / Month", "Method", "Amount", "Status"};
      this.tableModel = new DefaultTableModel(columns, 0) {
         @Override
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      this.table = new JTable(this.tableModel);
      this.table.setRowHeight(30);
      JScrollPane scrollPane = new JScrollPane(this.table);
      this.add(scrollPane, "Center");
      JPanel bottomPanel = new JPanel();
      bottomPanel.setBackground(Color.WHITE);
      JButton btnRefresh = new JButton("Refresh My Payments");
      bottomPanel.add(btnRefresh);
      this.add(bottomPanel, "South");
      btnRefresh.addActionListener((e) -> this.loadLoggedInUserPayments());
      this.loadLoggedInUserPayments();
   }

   public void loadLoggedInUserPayments() {
      this.tableModel.setRowCount(0);
      User user = Session.getCurrentUser();
      if (user != null) {
         JLabel var10000 = this.lblSubHeader;
         String var10001 = user.getFirstName();
         var10000.setText("Showing exclusive payment records for: " + var10001 + " " + user.getLastName() + " (" + user.getEmail() + ")");

         for(Object[] row : this.userController.getUserPayments(user.getEmail())) {
            this.tableModel.addRow(row);
         }
      }

   }
}
