package Q2;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class HttpRequestTest {
    HttpRequestBuilder builder = new HttpRequestBuilder();

    @Test
    public void canMakeSpecificHttpCall(){
        HttpRequest p = builder.withUrl("http://exams.imperial.ac.uk/575").withBody("mark=100")
                .withHeader("Date=02-05-2019").build();
        assertThat(p.getUrl(),is("http://exams.imperial.ac.uk/575"));
    }

}
