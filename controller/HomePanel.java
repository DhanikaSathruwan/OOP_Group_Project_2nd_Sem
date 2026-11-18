package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ClassItem;
import model.User;

public class HomePanel extends JPanel {
   private JComboBox<String> cbSubject;
   private JComboBox<String> cbYear;
   private JComboBox<ClassItem> cbLesson;
   private JPanel paymentSectionPanel;
   private JLabel lblSelectedClassInfo;
   private JLabel lblSlipStatus;
   private String uploadedSlipPath = "";
   private UserController userController = new UserController();

   public HomePanel() {
      this.setLayout(new BorderLayout(15, 15));
      this.setBackground(Color.WHITE);
      this.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
      JPanel topBannerPanel = new JPanel(new BorderLayout());
      topBannerPanel.setBackground(new Color(236, 240, 241));
      topBannerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      JLabel lblHomeBannerPhoto = new JLabel(new ImageIcon("home_banner_placeholder.png"));
      lblHomeBannerPhoto.setHorizontalAlignment(0);
      topBannerPanel.add(lblHomeBannerPhoto, "North");
      JLabel lblTitle = new JLabel("CLASS ENROLLMENT & LESSON PURCHASE", 0);
      lblTitle.setFont(new Font("Arial", 1, 22));
      lblTitle.setForeground(new Color(44, 62, 80));
      topBannerPanel.add(lblTitle, "Center");
      this.add(topBannerPanel, "North");
      JPanel centerGrid = new JPanel(new GridLayout(1, 2, 30, 0));
      centerGrid.setBackground(Color.WHITE);
      JPanel selectPanel = new JPanel(new GridLayout(7, 1, 8, 8));
      selectPanel.setBackground(Color.WHITE);
      selectPanel.setBorder(BorderFactory.createTitledBorder("Step 1: Choose Your Class & Lesson"));
      selectPanel.add(new JLabel("1. Select Class / Subject:"));
      this.cbSubject = new JComboBox(new String[]{"AL SFT (Science for Technology)", "AL ICT (Information Comm. Tech.)","AL ET (Engineering Tech.)","AL BST (Bio System Tech.)"});
      selectPanel.add(this.cbSubject);
      selectPanel.add(new JLabel("2. Select Target Year:"));
      this.cbYear = new JComboBox(new String[]{"2026", "2027", "2028"});
      selectPanel.add(this.cbYear);
      selectPanel.add(new JLabel("3. Select Relevant Month / Lesson & Time:"));
      this.cbLesson = new JComboBox();
      selectPanel.add(this.cbLesson);
      JButton btnProceedBuy = new JButton("BUY NOW (Proceed to Payment)");
      btnProceedBuy.setBackground(new Color(41, 128, 185));
      btnProceedBuy.setBorderPainted(false);
      btnProceedBuy.setForeground(Color.WHITE);
      btnProceedBuy.setFont(new Font("Arial", 1, 14));
      selectPanel.add(btnProceedBuy);
      centerGrid.add(selectPanel);
      this.paymentSectionPanel = new JPanel(new BorderLayout(10, 10));
      this.paymentSectionPanel.setBackground(new Color(248, 249, 250));
      this.paymentSectionPanel.setBorder(BorderFactory.createTitledBorder("Step 2: Bank Deposit & Slip Upload Info"));
      String bankInfoHtml = "<html><body style='padding:8px; font-family:Arial;'><h3 style='color:#1f618d;'>Bank Account Details for Deposit:</h3><b>Bank 01:</b> Bank of Ceylon (BOC) - Badulla Branch<br><b>Account No:</b> <span style='color:#c0392b; font-size:12pt;'><b>8845 9632 14</b></span><br><b>Account Name:</b> D.M. Asanka Dissanayaka (SFT Asa Academy)<br><br><b>Bank 02:</b> People's Bank - Badulla Branch<br><b>Account No:</b> <span style='color:#c0392b; font-size:12pt;'><b>112-2-001-4-5678912</b></span><br><b>Account Name:</b> D.M. Asanka Dissanayaka<br><hr><i>Please deposit the class fee and upload a clear photo/PDF of your Bank Slip below.</i></body></html>";
      JLabel lblBankDetails = new JLabel(bankInfoHtml);
      this.paymentSectionPanel.add(lblBankDetails, "North");
      JPanel uploadBox = new JPanel(new GridLayout(4, 1, 8, 8));
      uploadBox.setBackground(new Color(248, 249, 250));
      uploadBox.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
      this.lblSelectedClassInfo = new JLabel("Selected Lesson: None (Click 'BUY NOW' first)");
      this.lblSelectedClassInfo.setFont(new Font("Arial", 1, 12));
      this.lblSelectedClassInfo.setForeground(new Color(39, 174, 96));
      uploadBox.add(this.lblSelectedClassInfo);
      JButton btnUploadSlip = new JButton("1. Upload Bank Slip Photo / PDF");
      this.lblSlipStatus = new JLabel("Slip Status: Not Uploaded Yet");
      JButton btnConfirmEnroll = new JButton("2. SUBMIT SLIP & COMPLETE ENROLLMENT");
      btnConfirmEnroll.setBackground(new Color(39, 174, 96));
      btnConfirmEnroll.setBorderPainted(false);
      btnConfirmEnroll.setForeground(Color.WHITE);
      btnConfirmEnroll.setFont(new Font("Arial", 1, 13));
      uploadBox.add(btnUploadSlip);
      uploadBox.add(this.lblSlipStatus);
      uploadBox.add(btnConfirmEnroll);
      this.paymentSectionPanel.add(uploadBox, "Center");
      this.paymentSectionPanel.setVisible(false);
      centerGrid.add(this.paymentSectionPanel);
      this.add(centerGrid, "Center");
      JPanel bottomHelpPanel = new JPanel(new BorderLayout());
      bottomHelpPanel.setBackground(new Color(254, 249, 231));
      bottomHelpPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
      JLabel lblAdminHelp = new JLabel("Having issues with Class Purchase or Bank Slip Upload? You can re-select your class and try again, or Contact Admin Support: 071-2345678 / 055-2223344 (WhatsApp Available)", 0);
      lblAdminHelp.setFont(new Font("Arial", 1, 12));
      lblAdminHelp.setForeground(new Color(183, 149, 11));
      bottomHelpPanel.add(lblAdminHelp, "Center");
      this.add(bottomHelpPanel, "South");
      this.cbSubject.addActionListener((e) -> this.refreshClassesFromDB());
      this.cbYear.addActionListener((e) -> this.refreshClassesFromDB());
      this.refreshClassesFromDB();
      btnProceedBuy.addActionListener((e) -> {
         ClassItem selected = (ClassItem)this.cbLesson.getSelectedItem();
         if (selected == null) {
            JOptionPane.showMessageDialog(this, "No lessons available for this selection! Please select another Target Year/Subject or contact Admin.", "Selection Error", 2);
         } else {
            JLabel var10000 = this.lblSelectedClassInfo;
            String var10001 = selected.getLessonMonth();
            var10000.setText("Buying: " + var10001 + " | Fee: " + selected.getFee());
            this.paymentSectionPanel.setVisible(true);
            this.revalidate();
            this.repaint();
         }
      });
      btnUploadSlip.addActionListener((e) -> {
         JFileChooser chooser = new JFileChooser();
         if (chooser.showOpenDialog(this) == 0) {
            File slipFile = chooser.getSelectedFile();
            this.uploadedSlipPath = slipFile.getAbsolutePath();
            this.lblSlipStatus.setText("Uploaded: " + slipFile.getName());
         }

      });
      btnConfirmEnroll.addActionListener((e) -> {
         ClassItem selected = (ClassItem)this.cbLesson.getSelectedItem();
         User user = Session.getCurrentUser();
         if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a valid Class and Lesson first!", "Error", 0);
         } else if (this.uploadedSlipPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bank Slip Not Found!\nPlease upload your Bank Slip image/PDF to complete the payment, or try selecting the lesson again.\nIf this issue persists, contact Admin: 071-2345678", "Payment Incomplete - Try Again", 2);
         } else {
            boolean saved = this.userController.savePayment(user.getEmail(), selected.getSubject(), selected.getTargetYear(), selected.getLessonMonth(), "Bank Slip Deposit", selected.getFee(), this.uploadedSlipPath);
            if (saved) {
               JOptionPane.showMessageDialog(this, "SUCCESSFUL / ENROLLED!\nYour Bank Slip has been submitted and your enrollment is recorded.\nYou can view it under 'MY PAYMENTS' tab.", "Enrollment Successful", 1);
               this.uploadedSlipPath = "";
               this.lblSlipStatus.setText("Slip Status: Not Uploaded Yet");
               this.paymentSectionPanel.setVisible(false);
            } else {
               JOptionPane.showMessageDialog(this, "Payment Processing Error!\nPlease re-select your Subject, Target Year, and Lesson to try again.\nIf you still face this error, please contact Admin immediately: 071-2345678", "Transaction Failed - Contact Admin", 0);
            }

         }
      });
   }

   public void refreshClassesFromDB() {
      this.cbLesson.removeAllItems();
      String sub = this.cbSubject.getSelectedItem().toString();
      String yr = this.cbYear.getSelectedItem().toString();

      for(ClassItem item : this.userController.getFilteredClasses(sub, yr)) {
         this.cbLesson.addItem(item);
      }

   }
}
