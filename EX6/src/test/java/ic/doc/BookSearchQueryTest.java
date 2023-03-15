package ic.doc;

import ic.doc.catalogues.BritishLibraryCatalogue;
import ic.doc.catalogues.ImperialCollegeLibraryCatalogue;
import ic.doc.catalogues.Library;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static ic.doc.QueryBuilder.aQueryBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BookSearchQueryTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Library britishCatalogue = BritishLibraryCatalogue.getInstance();
    Library imperialCatalogue = ImperialCollegeLibraryCatalogue.getInstance();
    Library libraryMock = context.mock(Library.class);

    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

        List<Book> books = aQueryBuilder(britishCatalogue).withSurname("dickens").build().execute();
        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }

    @Test
    public void searchesForBooksInImperialLibraryCatalogueByAuthorSurname() {
        List<Book> books = aQueryBuilder(imperialCatalogue).withSurname("dickens").build().execute();
        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

        List<Book> books = aQueryBuilder(britishCatalogue).withFirstName("Jane").build().execute();
        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("Austen"));
    }

    @Test
    public void searchesForBooksInImperialLibraryCatalogueByAuthorFirstname() {

        List<Book> books = aQueryBuilder(imperialCatalogue).withFirstName("Jane").build().execute();
        assertThat(books.size(), is(2));
        assertTrue(books.get(0).matchesAuthor("Austen"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueByTitle() {

        List<Book> books = aQueryBuilder(britishCatalogue).withTitle("Two Cities").build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("dickens"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {
        List<Book> books = aQueryBuilder(britishCatalogue).beforePublicationYear(1700).build().execute();
        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Shakespeare"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

        List<Book> books = aQueryBuilder(britishCatalogue).afterPublicationYear(1950).build().execute();
        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("Golding"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

        //List<Book> books = new BookSearchQuery(null, "dickens", null, null, 1840).execute();
        List<Book> books = aQueryBuilder(britishCatalogue).withSurname("dickens").beforePublicationYear(1840).build().execute();

        assertThat(books.size(), is(1));
        assertTrue(books.get(0).matchesAuthor("charles dickens"));
    }

    @Test
    public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

        //List<Book> books = new BookSearchQuery(null, null, "of", 1800, 2000).execute();
        List<Book> books = aQueryBuilder(britishCatalogue).withTitle("of").afterPublicationYear(1800).beforePublicationYear(2000).build().execute();
        assertThat(books.size(), is(3));
        assertTrue(books.get(0).matchesAuthor("charles dickens"));
    }
}
