package ic.doc.camera;

public class Camera implements WriteListener {

  private final Sensor sensor;
  private final MemoryCard memoryCard;
  private boolean powerStatus;
  private boolean write;

  public Camera(Sensor sensor, MemoryCard memoryCard) {
    this.sensor = sensor;
    this.memoryCard = memoryCard;
    this.powerStatus = false;
    this.write = false;
  }

  public void pressShutter() {
    if (powerStatus) {
      this.write = true;
      byte[] imageData = sensor.readData();
      memoryCard.write(imageData);
    }
  }

  public void powerOn() {
    this.powerStatus = true;
    sensor.powerUp();
  }

  public void powerOff() {
    this.powerStatus = false;
    if (!write) {
      sensor.powerDown();
    }
  }

  @Override
  public void writeComplete() {
    this.write = false;
    if (powerStatus) {
      sensor.powerDown();
    }
  }
}
