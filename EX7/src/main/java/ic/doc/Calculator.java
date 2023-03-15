package ic.doc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
  List<Updatable> observers = new ArrayList<>();
  Stack<Integer> inputs = new Stack<>();
  private int count = 0;

  public void addObserver(Updatable observer) {
    observers.add(observer);
  }

  public void addInput(int i) {
    inputs.push(i);
    notifyObserver(i);
  }

  public void compute(Operation operation) {
    this.carryoutComputation(operation);
  }

  public void carryoutComputation(Operation operation) {
    if (operation != null & inputs.size() >= 2) {
      switch (operation) {
        case PLUS:
          this.count = inputs.pop() + inputs.pop();
          inputs.push(this.count);
          break;
        case MINUS:
          this.count = -inputs.pop() + inputs.pop();
          inputs.push(this.count);
          break;
        case MULTIPLY:
          this.count = inputs.pop() * inputs.pop();
          inputs.push(this.count);
          break;
        case DIVIDE:
          this.count = (int) ((float) 1 / inputs.pop() * inputs.pop());
          inputs.push(this.count);
          break;
        default:
          break;

      }
      notifyObserver(this.count);
    }
  }

  private void notifyObserver(int count) {
    for (var observer : observers) {
      observer.update(count);
    }
  }
}
