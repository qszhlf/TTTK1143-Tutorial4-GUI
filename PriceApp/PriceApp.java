import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PriceApp {
    private static JTextField field(JPanel p, String label, String value) {
        p.add(new JLabel(label)); JTextField x = new JTextField(value, 20); p.add(x); return x;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tutorial 4 - Price UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel form = new JPanel(new GridLayout(0, 2, 10, 12));
            form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            form.add(new JLabel("Product category:"));
            JComboBox<String> category = new JComboBox<>(new String[]{"SmartPhone", "Laptop", "Car"}); form.add(category);
            JTextField productName = field(form, "Product / Brand:", "Iphone 14 ProMax");
            JTextField productPrice = field(form, "Product price (RM):", "5500");
            JTextField buyer = field(form, "Customer name:", "Sally");
            JTextField salary = field(form, "Customer salary (RM):", "3000");
            category.addActionListener(e -> {
                int i = category.getSelectedIndex();
                productName.setText(i == 0 ? "Iphone 14 ProMax" : i == 1 ? "Lenovo Yoga" : "BMW");
                productPrice.setText(i == 0 ? "5500" : i == 1 ? "6000" : "200000");
                buyer.setText(i == 0 ? "Sally" : i == 1 ? "Aaron" : "Simon");
                salary.setText(i == 0 ? "3000" : i == 1 ? "8000" : "10000");
            });
            JTextArea out = new JTextArea(8, 48); out.setLineWrap(true); out.setWrapStyleWord(true);
            out.setEditable(false); out.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            JButton btn = new JButton("Check Affordability (Original Rules)");
            btn.addActionListener(e -> {
                try {
                    double cost = Double.parseDouble(productPrice.getText());
                    Customer c = new Customer(buyer.getText(), Double.parseDouble(salary.getText()));
                    Price item;
                    if (category.getSelectedIndex() == 0) item = new SmartPhone("SmartPhone", productName.getText(), cost);
                    else if (category.getSelectedIndex() == 1) item = new Laptop("Laptop", productName.getText(), cost);
                    else item = new Car(productName.getText(), cost);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    PrintStream original = System.out;
                    try { System.setOut(new PrintStream(bytes)); item.getPrice(c); }
                    finally { System.setOut(original); }
                    out.setText(bytes.toString());
                } catch (Exception ex) { JOptionPane.showMessageDialog(frame, "Enter valid numeric price and salary."); }
            });
            frame.add(form, BorderLayout.NORTH); frame.add(new JScrollPane(out), BorderLayout.CENTER);
            frame.add(btn, BorderLayout.SOUTH); frame.pack(); frame.setLocationRelativeTo(null); frame.setVisible(true);
            btn.doClick();
        });
    }
}
