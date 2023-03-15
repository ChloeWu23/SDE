package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CalculatorTest {
  private final Calculator calculator = new Calculator();
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();
  Updatable display = context.mock(Updatable.class);

  @Before
  public void setup() {
    calculator.addObserver(display);
  }

  @Test
  public void updateDisplayWhenAddInputs() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(display).update(1);
            exactly(1).of(display).update(2);
          }
        });
    calculator.addInput(1);
    calculator.addInput(2);
  }

  @Test
  public void supportAddingTwoValues() {
    context.checking(
        new Expectations() {
          {
            allowing(display).update(1);
            allowing(display).update(1);
            exactly(1).of(display).update(2);
          }
        });

    calculator.addInput(1);
    calculator.addInput(1);
    calculator.compute(Operation.PLUS);
  }

  @Test
  public void supportSuccessiveAdding() {
    context.checking(
        new Expectations() {
          {
            allowing(display).update(1);
            allowing(display).update(2);
            allowing(display).update(3);
            allowing(display).update(1);
            exactly(1).of(display).update(4);
          }
        });

    calculator.addInput(1);
    calculator.addInput(2);
    calculator.compute(Operation.PLUS);
    calculator.addInput(1);
    calculator.compute(Operation.PLUS);
  }

  @Test
  public void supportMinus() {
    context.checking(
        new Expectations() {
          {
            allowing(display).update(1);
            allowing(display).update(2);
            exactly(1).of(display).update(-1);
          }
        });
    calculator.addInput(1);
    calculator.addInput(2);
    calculator.compute(Operation.MINUS);
  }

  @Test
  public void supportMultiply() {
    context.checking(
        new Expectations() {
          {
            allowing(display).update(2);
            allowing(display).update(3);
            exactly(1).of(display).update(6);
          }
        });

    calculator.addInput(2);
    calculator.addInput(3);
    calculator.compute(Operation.MULTIPLY);
  }

  @Test
  public void supportDivision() {
    context.checking(
        new Expectations() {
          {
            allowing(display).update(12);
            allowing(display).update(3);
            exactly(1).of(display).update(4);
          }
        });

    calculator.addInput(12);
    calculator.addInput(3);
    calculator.compute(Operation.DIVIDE);
  }

  @Test
  public void supportMixedArithmeticCalculation() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(display).update(1);
            exactly(1).of(display).update(6);
            exactly(1).of(display).update(6);
            exactly(1).of(display).update(3);
            exactly(1).of(display).update(2);
          }
        });

    calculator.addInput(1);
    calculator.addInput(6);
    calculator.compute(Operation.MULTIPLY);
    calculator.addInput(3);
    calculator.compute(Operation.DIVIDE);
  }

  @Test
  public void clearTheCalculatorWhenTypingZero() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(display).update(1);
            exactly(1).of(display).update(0);
            exactly(1).of(display).update(3);
            exactly(1).of(display).update(6);
            exactly(1).of(display).update(9);
          }
        });
    calculator.addInput(1);
    calculator.addInput(0);
    calculator.addInput(3);
    calculator.addInput(6);
    calculator.compute(Operation.PLUS);
  }
}
