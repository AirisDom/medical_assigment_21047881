import controller.SystemController;
import javax.swing.SwingUtilities;
import view.MainFrame;

public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("==============================================");
                System.out.println("Healthcare Management System - Starting...");
                System.out.println("==============================================\n");
                
                System.out.println("Step 1: Initializing SystemController...");
                SystemController controller = new SystemController();
                
                System.out.println("\nStep 2: Creating Main GUI Window...");
                MainFrame frame = new MainFrame(controller);
                frame.setVisible(true);
                
                System.out.println("\n==============================================");
                System.out.println("Healthcare Management System - Ready!");
                System.out.println("==============================================\n");
                
            } catch (Exception e) {
                System.err.println("Error starting application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
