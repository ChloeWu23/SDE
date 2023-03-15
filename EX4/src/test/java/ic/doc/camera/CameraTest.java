package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {
  private final byte[] imagaData = new byte[8];
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();
  Sensor sensor = context.mock(Sensor.class);
  MemoryCard memoryCard = context.mock(MemoryCard.class);
  private final Camera camera = new Camera(sensor, memoryCard);

  @Test
  public void switchingTheCameraOnPowersUpTheSensor() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(sensor).powerUp();
          }
        });
    camera.powerOn();
  }

  @Test
  public void switchingTheCameraOffPowersDownTheSensor() {
    switchPower();
    context.checking(
        new Expectations() {
          {
            // ignoring(sensor).powerUp();
            exactly(1).of(sensor).powerDown();
          }
        });
    // camera.powerOn();
    camera.powerOff();
  }

  @Test
  public void pressingShutterWhenPowerOffDoNoting() {
    context.checking(
        new Expectations() {
          {
            never(sensor);
            never(memoryCard);
          }
        });
    camera.pressShutter();
  }

  @Test
  public void pressingShutterWhenPowerOnCopyDataToMemoryCard() {
    switchPower();
    context.checking(
        new Expectations() {
          {
            exactly(1).of(sensor).readData();
            will(returnValue(imagaData));
            exactly(1).of(memoryCard).write(imagaData);
          }
        });
    camera.pressShutter();
  }

  @Test
  public void switchingCameraOfNotPowerDownSensorWhenWritting() {
    switchPower();
    context.checking(
        new Expectations() {
          {
            exactly(1).of(sensor).readData();
            will(returnValue(imagaData));
            exactly(1).of(memoryCard).write(imagaData);
            exactly(0).of(sensor).powerDown();
          }
        });
    camera.pressShutter();
    camera.powerOff();
  }

  @Test
  public void cameraPowersDownSensorOnceWriteComplete() {
    switchPower();
    context.checking(
        new Expectations() {
          {
            exactly(1).of(sensor).powerDown();
          }
        });
    camera.writeComplete();
  }

  private void switchPower() {
    context.checking(
        new Expectations() {
          {
            // exactly(1).of(sensor).powerUp();
            ignoring(sensor).powerUp();
          }
        });
    camera.powerOn();
  }
}
