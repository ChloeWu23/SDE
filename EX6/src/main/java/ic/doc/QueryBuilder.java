package ic.doc;

import ic.doc.catalogues.Library;

public class QueryBuilder {

    private String name1;
    private String name2;
    private String title;
    private Integer date1;
    private Integer date2;
    private final Library library;

    private QueryBuilder(Library library) {
        this.library = library;
    }

    public static QueryBuilder aQueryBuilder(Library library) {
        return new QueryBuilder(library);
    }

    public QueryBuilder withSurname(String Surname) {
        this.name2 = Surname;
        return this;
    }

    public QueryBuilder withFirstName(String firstname) {
        this.name1 = firstname;
        return this;
    }

    public QueryBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public QueryBuilder beforePublicationYear(int date2) {
        this.date2 = date2;
        return this;
    }

    public QueryBuilder afterPublicationYear(int date1) {
        this.date1 = date1;
        return this;
    }

    public BookSearchQuery build() {
        return new BookSearchQuery(name1, name2, title, date1, date2, library);

    }

}