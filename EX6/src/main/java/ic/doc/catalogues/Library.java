package ic.doc.catalogues;

import ic.doc.Book;

import java.util.List;

public interface Library {
    List<Book> searchFor(String query);
}
