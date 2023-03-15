package ic.doc;

import ic.doc.catalogues.BritishLibraryCatalogue;
import ic.doc.catalogues.ImperialCollegeLibraryCatalogue;
import ic.doc.catalogues.Library;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static ic.doc.QueryBuilder.aQueryBuilder;

public class BookSearchQueryTestWithoutPassingLibrary {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Library britishCatalogue = BritishLibraryCatalogue.getInstance();
    Library imperialCatalogue = ImperialCollegeLibraryCatalogue.getInstance();
    Library libraryMock = context.mock(Library.class);

    @Test
    public void SearchesForBooksInLibraryCatalogueByAuthorSurname() {
        context.checking(
                new Expectations() {{
                    exactly(1).of(libraryMock).searchFor("LASTNAME='dickens' ");
                }}
        );
        List<Book> books = aQueryBuilder(libraryMock).withSurname("dickens").build().execute();
    }


    @Test
    public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {
        context.checking(
                new Expectations() {{
                    exactly(1).of(libraryMock).searchFor("FIRSTNAME='Jane' ");
                }}
        );
        List<Book> books = aQueryBuilder(libraryMock).withFirstName("Jane").build().execute();

    }

    @Test
    public void searchesForBooksInLibraryCatalogueByTitle() {
        context.checking(
                new Expectations() {{
                    exactly(1).of(libraryMock).searchFor("TITLECONTAINS(Two Cities) ");
                }}
        );
        List<Book> books = aQueryBuilder(libraryMock).withTitle("Two Cities").build().execute();


    }

    @Test
    public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {
        context.checking(
                new Expectations() {{
                    exactly(1).of(libraryMock).searchFor("PUBLISHEDBEFORE(1700) ");
                }}
        );
        List<Book> books = aQueryBuilder(libraryMock).beforePublicationYear(1700).build().execute();


    }

    @Test
    public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {
        context.checking(
                new Expectations() {{
                    exactly(1).of(libraryMock).searchFor("PUBLISHEDAFTER(1950) ");
                }}
        );
        List<Book> books = aQueryBuilder(libraryMock).afterPublicationYear(1950).build().execute();


    }

    @Test
    public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {
        context.checking(
                new Expectations() {{
                    exactly(1).of(libraryMock).searchFor("LASTNAME='dickens' PUBLISHEDBEFORE(1840) ");
                }}
        );
        List<Book> books = aQueryBuilder(libraryMock).withSurname("dickens").beforePublicationYear(1840).build().execute();


    }

    @Test
    public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {
        context.checking(
                new Expectations() {{
                    exactly(1).of(libraryMock).searchFor("TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) ");
                }}
        );
        List<Book> books = aQueryBuilder(libraryMock).withTitle("of").afterPublicationYear(1800).beforePublicationYear(2000).build().execute();

    }
}
