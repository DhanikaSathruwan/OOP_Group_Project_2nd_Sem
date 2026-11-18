package main;

import controller.LoginForm;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {
   public static void main(String[] args) {
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
         e.printStackTrace();
      }

      SwingUtilities.invokeLater(() -> {
         LoginForm login = new LoginForm();
         login.setVisible(true);
      });
   }
}
