package Q1;
public class LaTeXList implements ListStyle {

    @Override
    public String formatHeader() {
        return "\\begin{itemize}"; }
    @Override
    public String formatItem(String item) {
        return " \\item " + item; }
    @Override
    public String formatFooter() {
        return "\\end{itemize}"; }
}
/*
public class LaTeXList implements ListStyle {
    /*
    public LaTeXList(String... items) {
        //super(items);
        //
        }


    @Override
    public String formatHeader() {
        return "\\begin{itemize}";
    }
    @Override
    public String formatItem(String item) {
        return " \\item " + item; }
    @Override
    public String formatFooter() {
        return "\\end{itemize}";
    }
}
*/

