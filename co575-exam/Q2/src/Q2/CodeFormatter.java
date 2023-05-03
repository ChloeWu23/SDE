package Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeFormatter {
 private LanguageFormatter languageFormatter;

  public CodeFormatter(LanguageFormatter languageFormatter) {
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
