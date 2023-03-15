package ic.doc;

enum Operation {
  PLUS("+"),
  MINUS("-"),
  MULTIPLY("*"),
  DIVIDE("/");

  private final String symbol;

  Operation(String symbol) {
    this.symbol = symbol;
  }

  public String symbol() {
    return symbol;
  }
}
