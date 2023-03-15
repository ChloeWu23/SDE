package ic.doc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class RecentlyUsedListTest {
    RecentlyUsedList<String> recentlyUsedList = new RecentlyUsedList<>();

    @Test
    public void isEmptyWhenInitialized() {
        assertThat(new RecentlyUsedList<String>().isEmpty(),is(true));
    }

    @Test
    public void canAddItems(){
        recentlyUsedList.add("Hello");
        assertThat(recentlyUsedList.retrive(0),is("Hello"));
        assertThat(recentlyUsedList.retrive(0),is("World"));
    }

    @Test
    public void canRetriveItemTest(){
        recentlyUsedList.add("Hello");
        assertThat(recentlyUsedList.retrive(0),is("Hello"));
    }

    @Test
    public void isMostRecentItemFirstTest(){
        recentlyUsedList.add("Hello");
        recentlyUsedList.add("World");
        assertThat(recentlyUsedList.retrive(0), is("World"));
    }

    @Test
    public void isItemUniqueTest(){
        recentlyUsedList.add("Hello");
        recentlyUsedList.add("World");
        recentlyUsedList.add("Hello");
        assertThat(recentlyUsedList.size(),is(2));

    }


}
