package Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeFormatter {
  private final Language languageFormatter;

  public CodeFormatter(Language languageFormatter) {
    this.languageFormatter = languageFormatter;
  }

  public String format(String source) {

    String trimmed = stripBlankLines(source);
    int indentLevel = 0;

    List<String> indentedCode = new ArrayList<>();

    for(String line : linesOf(trimmed)) {
      if (line.contains(languageFormatter.endOfBlock())) {
        indentLevel -= 1;
      }
      indentedCode.add(indentBy(indentLevel, languageFormatter.tabsOrSpaces(), line));
      for (String openBlock : languageFormatter.startOfBlock()) {
        if (line.contains(openBlock)) {
          System.out.println("line contains " + openBlock);
          indentLevel += 1;
        }
      }

    }

    return String.join("\n", indentedCode);
  }



  private String indentBy(int num, WhiteSpace whiteSpace, String line) {
    String indent = "";
    for(int i = 0; i < num; i++) {
      indent = indent + whiteSpace.literal;
    }
    return indent + line.trim();
  }

  private List<String> linesOf(String source) {
    return Arrays.asList(source.split("\n"));
  }

  private String stripBlankLines(String source) {
    return source.trim();
  }


}


/*
answer for 2.Design Pattern:
1. the original code follows template method design pattern
2.Strategy would be another way of factoring out duplication
4.The strategy pattern is much more preferred than the template pattern.
The latter one introduces tight coupling between the parent and child classes, due to the use of inheritance.
Although duplication is removed using both patterns, when using the latter one, it is not possible to have separated independent reusable components.
The strategy pattern avoids this problem by using composition, introducing more flexibility due to the looser coupling created between components.


 */
