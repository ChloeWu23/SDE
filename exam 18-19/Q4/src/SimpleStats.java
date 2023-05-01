import java.awt.Panel;
import java.awt.event.ActionEvent; import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class SimpleStats implements Updatable {
    private JTextField currentMax;
    private JTextField currentMean;
    private void display() {
        //Debug: need to add observer for this Gui App:
        StatsModel model = new StatsModel();
        model.addObservers(this);
        JFrame frame = new JFrame("Simple Stats");
        frame.setSize(250, 350); Panel panel = new Panel();
         currentMax = new JTextField(11);
         currentMean = new JTextField(11);
        panel.add(new JLabel("Max: value "));
        panel.add(currentMax);
        panel.add(new JLabel("Mean: value "));
        panel.add(currentMean);
        for (int i = 1; i <= 12; i++) {
            final int n = i;
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.pressonebutton(n);
                } });
            panel.add(button);
        }
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); }
    public static void main(String[] args) {
        new SimpleStats().display(); }

    @Override
    public void update(int max, double mean) {
        currentMax.setText(String.valueOf(max));
        currentMean.setText(String.valueOf(mean));
    }
}

/*
Answer for Q4:
Benefits of using MVC:
a) The model can be used independently of the GUI - The model could be used in a web interface,
mobile app or desktop application without changing it.
b) Separation of concerns - The model can be developed using TTD, while the GUI designer does not
need to concern themselves with any model code.

 */