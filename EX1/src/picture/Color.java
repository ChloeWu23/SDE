package picture;

public class Color {
  private final int red; //this is attribute with name red with type int
  private final int green;
  private final int blue;

  public Color(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public int red() {
    return red;
  }

  public int green() {
    return green;
  }

  public int blue() {
    return blue;
  }
/*
  public void setred(int num){
    this.red= num;
  }

  public void setgreen(int num){
    this.green= num;
  }

  public void setblue(int num){
   this.blue = num;
  }
  */


}
