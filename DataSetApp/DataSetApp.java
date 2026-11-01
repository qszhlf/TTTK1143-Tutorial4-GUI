import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DataSetApp {
    private static JTextField field(String value, JPanel panel, String label) {
        panel.add(new JLabel(label));
        JTextField f = new JTextField(value, 25);
        panel.add(f);
        return f;
    }
    private static DataSet fromNumbers(String input, String kind) {
        DataSet data = new DataSet();
        String[] parts = input.split(",");
        for (String part : parts) {
            if (part.trim().isEmpty()) continue;
            double n = Double.parseDouble(part.trim());
            if (kind.equals("bank")) data.add(new BankAccount(n));
            else data.add(new Coin(n, "Coin " + n));
        }
        if (data.getMax() == null) throw new IllegalArgumentException("Enter at least one value.");
        return data;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tutorial 4 - DataSet UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 12));
            panel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
            JTextField bank = field("0, 10000, 2000", panel, "Bank balances (comma separated):");
            JTextField coin = field("0.25, 0.10, 0.05", panel, "Coin values (comma separated):");
            JTextField names = field("Ali, Alex, Maria", panel, "Student names:");
            JTextField heights = field("157.50, 165.00, 147.00", panel, "Student heights (cm):");
            JTextArea result = new JTextArea(12, 48);
            result.setEditable(false);
            result.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            JButton calculate = new JButton("Calculate Average & Maximum");
            calculate.addActionListener(e -> {
                try {
                    DecimalFormat df = new DecimalFormat("0.00");
                    DataSet banks = fromNumbers(bank.getText(), "bank");
                    DataSet coins = fromNumbers(coin.getText(), "coin");
                    String[] n = names.getText().split(",");
                    String[] h = heights.getText().split(",");
                    if (n.length != h.length || n.length == 0) throw new IllegalArgumentException("Student names and heights must match.");
                    DataSet students = new DataSet();
                    for (int i = 0; i < n.length; i++) students.add(new Student(n[i].trim(), Double.parseDouble(h[i].trim())));
                    result.setText("BANK ACCOUNTS\nAverage Balance: " + df.format(banks.getAverage())
                        + "\nHighest Balance: " + df.format(banks.getMax().getMeasure())
                        + "\n\nCOINS\nAverage Coin Value: " + df.format(coins.getAverage())
                        + "\nHighest Coin Value: " + df.format(coins.getMax().getMeasure())
                        + "\nHighest Coin Name: " + coins.getMax().getName()
                        + "\n\nSTUDENTS\nAverage Height: " + df.format(students.getAverage())
                        + " cm\nTallest Height: " + df.format(students.getMax().getMeasure())
                        + " cm\nTallest Student: " + students.getMax().getName());
                } catch (Exception ex) { JOptionPane.showMessageDialog(frame, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE); }
            });
            frame.add(panel, BorderLayout.NORTH);
            frame.add(new JScrollPane(result), BorderLayout.CENTER);
            frame.add(calculate, BorderLayout.SOUTH);
            frame.pack(); frame.setLocationRelativeTo(null); frame.setVisible(true);
            calculate.doClick();
        });
    }
}
