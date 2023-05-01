package Q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormattedList {
    private final List<String> content = new ArrayList<>();
    private ListStyle list;

    public FormattedList(ListStyle l, String... items) {
        this.list = l;
        content.addAll(Arrays.asList(items));
    }
    public void add(String item) {
        content.add(item); }
    public void print() {
        System.out.println(list.formatHeader());
        for (String item : content) {
        System.out.println(list.formatItem(item)); }
        System.out.println(list.formatFooter()); }

}

/*
Answer for Q1:
1.Design Pattern: Template Method
2.Strategy
4.The strategy pattern is much more preferred than the template pattern.
The latter one introduces tight coupling between the parent and child classes, due to the use of inheritance.  Although duplication is removed using both patterns, when using the latter one, it is not possible to have separated independent reusable components.
The strategy pattern avoids this problem by using composition, introducing more flexibility due to the looser coupling created between components.

* */