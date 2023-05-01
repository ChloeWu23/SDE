package Q1;

public class HtmlList implements ListStyle {
    /*
    public HtmlList(String... items) {
        super(items); }

     */
    @Override
    public String formatHeader() {
        return "<ul>"; }
    @Override
    public String formatItem(String item) {
        return " <li>" + item + "</li>"; }
    @Override
    public String formatFooter() {
        return "</ul>"; }
}