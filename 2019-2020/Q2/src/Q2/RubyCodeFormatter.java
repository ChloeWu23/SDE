package Q2;

import java.util.List;

public class RubyCodeFormatter  implements Language{

  @Override
  public List<String> startOfBlock() {
    return List.of("do", "if", "while");
  }

  @Override
  public String endOfBlock() {
    return "end";
  }

  @Override
  public WhiteSpace tabsOrSpaces() {
    return WhiteSpace.TABS;
  }
}
