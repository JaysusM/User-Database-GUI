import javax.swing.*;

public class main {
    public static void main(String[] args)
    {
        JFrame root = new JFrame("User Database");
        root.setContentPane(new mainFrame().getMainPanel());
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.pack();
        root.setVisible(true);
        root.setLocationRelativeTo(null);
    }
}
