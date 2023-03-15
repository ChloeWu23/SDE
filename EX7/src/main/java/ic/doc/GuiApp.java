package ic.doc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiApp implements Updatable {
  private final Calculator calculator;
  private final JFrame frame;
  private final JPanel panel;
  private final JTextField textField;

  public GuiApp() {
    this.calculator = new Calculator();
    this.frame = new JFrame("Reverse Polish notation");
    this.panel = new JPanel();
    this.textField = new JTextField(10);
  }

  public static void main(String[] args) {
    new GuiApp().display();
  }

  private void display() {
    this.frame.setSize(400, 400);
    frame.setBackground(Color.darkGray);
    FlowLayout layout = new FlowLayout();
    this.frame.add(panel);
    layout.setHgap(30);
    layout.setVgap(10);
    panel.setLayout(layout);
    Calculator calculator = new Calculator();
    calculator.addObserver(this);
    addNumberButton(panel, calculator);
    addOperationButton(panel, calculator);
    panel.add(textField);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  private void addOperationButton(JPanel panel, Calculator calculator) {
    for (Operation operation : Operation.values()) {
      JButton button = new JButton(operation.symbol());
      button.addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              calculator.compute(operation);
            }
          });
      panel.add(button);
    }
  }

  private void addNumberButton(JPanel panel, Calculator calculator) {
    for (int i = 0; i <= 9; ++i) {
      JButton button = new JButton(String.valueOf(i));
      int finalI = i;
      button.addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              calculator.addInput(finalI);
            }
          });
      panel.add(button);
    }
  }

  @Override
  public void update(int count) {
    this.textField.setText(String.valueOf(count));
  }
}
