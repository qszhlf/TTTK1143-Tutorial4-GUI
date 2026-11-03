import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/** Main menu for the four independent Tutorial 4 Swing projects. */
public class Tutorial4App {
    private static final String[][] APPS = {
        {"DataSetApp", "DataSetApp", "Calculate averages and maximum values"},
        {"EdibleApp", "EdibleApp", "Explore the Edible interface"},
        {"MobileApp", "MobileApp", "Explore devices and radio tuners"},
        {"PriceApp", "PriceApp", "Check products using the original Price rules"}
    };

    private static void open(String folder, String mainClass, JFrame owner) {
        try {
            File directory = new File(folder);
            if (!directory.isDirectory()) {
                throw new IOException("Folder not found: " + directory.getAbsolutePath());
            }
            String java = new File(new File(System.getProperty("java.home"), "bin"),
                System.getProperty("os.name").toLowerCase().contains("win") ? "java.exe" : "java").getAbsolutePath();
            // Separate JVM processes prevent duplicate default-package class names (SmartPhone).
            new ProcessBuilder(java, "-cp", ".", mainClass)
                .directory(directory)
                .inheritIO()
                .start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(owner,
                "Unable to open " + mainClass + ".\nRun run.cmd to compile all projects first.\n\n" + ex.getMessage(),
                "Launch Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tutorial 4 - Java Interface & Polymorphism");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel root = new JPanel(new BorderLayout(0, 18));
            root.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
            JLabel title = new JLabel("Tutorial 4 - Main Menu", SwingConstants.CENTER);
            title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            root.add(title, BorderLayout.NORTH);

            JPanel choices = new JPanel(new GridLayout(4, 1, 0, 12));
            for (String[] app : APPS) {
                JButton button = new JButton("Open " + app[0] + "  -  " + app[2]);
                button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
                button.setFocusPainted(false);
                button.setPreferredSize(new Dimension(540, 58));
                button.addActionListener(e -> open(app[0], app[1], frame));
                choices.add(button);
            }
            root.add(choices, BorderLayout.CENTER);
            JLabel footer = new JLabel("Select any application. Close its window to return to this menu.", SwingConstants.CENTER);
            footer.setForeground(Color.DARK_GRAY);
            root.add(footer, BorderLayout.SOUTH);
            frame.setContentPane(root);
            frame.pack();
            frame.setMinimumSize(new Dimension(620, 410));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
