import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MobileApp {
    private static JTextField field(JPanel p, String label, String value) {
        p.add(new JLabel(label)); JTextField x = new JTextField(value, 20); p.add(x); return x;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tutorial 4 - Mobile Device UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel form = new JPanel(new GridLayout(0, 2, 10, 12));
            form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            form.add(new JLabel("Device type:"));
            JComboBox<String> kind = new JComboBox<>(new String[]{"SmartPhone", "BasicPhone"}); form.add(kind);
            JTextField maker = field(form, "Manufacturer:", "Samsung");
            JTextField model = field(form, "Model:", "S20");
            JTextField type = field(form, "Phone type (SmartPhone only):", "Galaxy");
            JTextField station = field(form, "Radio station:", "Hot FM");
            JTextField freq = field(form, "Frequency (MHz):", "97.60");
            kind.addActionListener(e -> {
                boolean smart = kind.getSelectedIndex() == 0;
                type.setEnabled(smart);
                maker.setText(smart ? "Samsung" : "Sonny Ericson");
                model.setText(smart ? "S20" : "Walkman");
                station.setText(smart ? "Hot FM" : "Fly FM");
                freq.setText(smart ? "97.60" : "109.8");
            });
            JTextArea out = new JTextArea(9, 42); out.setEditable(false);
            out.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            JButton btn = new JButton("Show Device & Radio Details");
            btn.addActionListener(e -> {
                try {
                    MobileDevice phone = kind.getSelectedIndex() == 0
                        ? new SmartPhone(maker.getText(), model.getText(), type.getText())
                        : new BasicPhone(maker.getText(), model.getText());
                    phone.setStation(station.getText());
                    phone.setFrequency(Double.parseDouble(freq.getText().trim()));
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    PrintStream original = System.out;
                    try { System.setOut(new PrintStream(bytes)); phone.printDetails(); }
                    finally { System.setOut(original); }
                    out.setText(bytes.toString() + "Station: " + phone.station + "\nFrequency: " + phone.frequency + " MHz");
                } catch (Exception ex) { JOptionPane.showMessageDialog(frame, "Enter a valid radio frequency."); }
            });
            frame.add(form, BorderLayout.NORTH); frame.add(new JScrollPane(out), BorderLayout.CENTER);
            frame.add(btn, BorderLayout.SOUTH); frame.pack(); frame.setLocationRelativeTo(null); frame.setVisible(true);
            btn.doClick();
        });
    }
}
