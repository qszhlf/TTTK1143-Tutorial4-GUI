import javax.swing.*;
import java.awt.*;

public class EdibleApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tutorial 4 - Edible UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel form = new JPanel(new GridLayout(0, 2, 10, 12));
            form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            form.add(new JLabel("Choose food:"));
            JComboBox<String> food = new JComboBox<>(new String[]{"Chicken", "Red Apple", "Navel Orange"});
            form.add(food);
            form.add(new JLabel("Person name:"));
            JTextField name = new JTextField("Ali");
            form.add(name);
            JTextArea output = new JTextArea(7, 40);
            output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            output.setEditable(false);
            JButton show = new JButton("Show How to Eat");
            show.addActionListener(e -> {
                Edible selected;
                switch (food.getSelectedIndex()) {
                    case 0: selected = new Animal("Chicken"); break;
                    case 1: selected = new Apple("Red Apple"); break;
                    default: selected = new Orange("Navel Orange");
                }
                Person p = new Person(name.getText().trim().isEmpty() ? "Guest" : name.getText().trim());
                output.setText(p.eating(selected));
            });
            food.addActionListener(e -> name.setText(food.getSelectedIndex() == 0 ? "Ali" : "Sarah"));
            JButton all = new JButton("Show All Original Examples");
            all.addActionListener(e -> {
                Edible[] objects = {new Animal("Chicken"), new Apple("Red Apple"), new Orange("Navel Orange")};
                StringBuilder b = new StringBuilder();
                for (Edible obj : objects) {
                    Person p = obj instanceof Animal ? new Person("Ali") : new Person("Sarah");
                    b.append(p.eating(obj)).append("\n\n");
                }
                output.setText(b.toString());
            });
            JPanel buttons = new JPanel(); buttons.add(show); buttons.add(all);
            frame.add(form, BorderLayout.NORTH);
            frame.add(new JScrollPane(output), BorderLayout.CENTER);
            frame.add(buttons, BorderLayout.SOUTH);
            frame.pack(); frame.setLocationRelativeTo(null); frame.setVisible(true);
            all.doClick();
        });
    }
}
