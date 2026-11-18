package controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.User;

public class MainForm extends JFrame {
   private JPanel contentPanel;
   private CardLayout cardLayout;
   private HomePanel homePanel;
   private AccountPanel accountPanel;
   private PaymentPanel paymentPanel;

   public MainForm() {
      User currentUser = Session.getCurrentUser();
      if (currentUser == null) {
         this.dispose();
         (new LoginForm()).setVisible(true);
      } else {
         String var10001 = currentUser.getFirstName();
         this.setTitle("Education Application - Student Portal (" + var10001 + " " + currentUser.getLastName() + ")");
         this.setSize(1050, 750);
         this.setDefaultCloseOperation(3);
         this.setLocationRelativeTo((Component)null);
         this.setLayout(new BorderLayout());
         JPanel navBar = new JPanel(new BorderLayout());
         navBar.setBackground(new Color(41, 128, 185));
         navBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
         JLabel lblLogo = new JLabel("  EDUCATION PORTAL ");
         lblLogo.setIcon(new ImageIcon("main_logo_placeholder.png"));
         lblLogo.setFont(new Font("Arial", 1, 18));
         lblLogo.setForeground(Color.WHITE);
         navBar.add(lblLogo, "West");
         JPanel menuButtons = new JPanel(new FlowLayout(2, 15, 0));
         menuButtons.setOpaque(false);
         JButton btnHome = new JButton("HOME (Classes & Buy)");
         JButton btnAccount = new JButton("MY ACCOUNT");
         JButton btnPayment = new JButton("MY PAYMENTS");
         JButton btnLogout = new JButton("LOGOUT");
         btnLogout.setBackground(new Color(192, 57, 43));
         btnLogout.setBorderPainted(false);
         btnLogout.setForeground(Color.WHITE);
         menuButtons.add(btnHome);
         menuButtons.add(btnAccount);
         menuButtons.add(btnPayment);
         menuButtons.add(btnLogout);
         navBar.add(menuButtons, "East");
         this.add(navBar, "North");
         this.cardLayout = new CardLayout();
         this.contentPanel = new JPanel(this.cardLayout);
         this.homePanel = new HomePanel();
         this.accountPanel = new AccountPanel();
         this.paymentPanel = new PaymentPanel();
         this.contentPanel.add(this.homePanel, "Home");
         this.contentPanel.add(this.accountPanel, "Account");
         this.contentPanel.add(this.paymentPanel, "Payment");
         this.add(this.contentPanel, "Center");
         btnHome.addActionListener((e) -> {
            this.homePanel.refreshClassesFromDB();
            this.cardLayout.show(this.contentPanel, "Home");
         });
         btnAccount.addActionListener((e) -> {
            this.accountPanel.loadLoggedInUserData();
            this.cardLayout.show(this.contentPanel, "Account");
         });
         btnPayment.addActionListener((e) -> {
            this.paymentPanel.loadLoggedInUserPayments();
            this.cardLayout.show(this.contentPanel, "Payment");
         });
         btnLogout.addActionListener((e) -> {
            Session.clearSession();
            this.dispose();
            (new LoginForm()).setVisible(true);
         });
      }
   }
}
