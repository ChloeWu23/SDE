package ic.doc;

import ic.doc.awards.Oscar;
import ic.doc.movies.Certification;
import ic.doc.movies.Movie;
import ic.doc.streaming.PlaybackEventLog;
import ic.doc.streaming.VideoStream;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class VideoStreamerTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private Library library = context.mock(Library.class);
    private PlaybackEventLog playbackEvents;
    private LocalTime endTime = LocalTime.now();
    private TimeService timeService = context.mock(TimeService.class);
    private PlayBackEventInterface playBackEventInterface = context.mock(PlayBackEventInterface.class);
    private VideoStreamer streamer = new VideoStreamer(playbackEvents, playBackEventInterface, library, timeService);

    @Test
    public void allowsUserToStreamSuggestedMovies() {
        //VideoStreamer streamer = new VideoStreamer();
        User user = new User("Adam", 9);
        //List<Movie> movies = streamer.getSuggestedMovies(user);
        context.checking(new Expectations() {{
            exactly(1).of(library).recommendedMoviesFor(user);
            //will(returnValue(defaultMovies));
            exactly(1).of(timeService).now();
            will(returnValue(LocalTime.now()));
            ignoring(playBackEventInterface);
        }});

        List<Movie> movies = streamer.getSuggestedMovies(user);
        VideoStream stream = streamer.startStreaming(movies.get(0), user);
        streamer.stopStreaming(stream);

    }

    @Test
    public void canGetSuggestedMoviesTest(){
        User user = new User("Adam", 9);
        context.checking(new Expectations(){{
            exactly(1).of(library).recommendedMoviesFor(user);
        }});
        streamer.getSuggestedMovies(user);
    }

    @Test
    public void aFilmWatchedForMoreThanFifteenMinutesIsAddedToALog() {
        User user = new User("Adam", 9);
        Movie movie =  new Movie("Shrek",
                "A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on " +
                        "a quest and rescue a princess for the lord in order to get his land back.",
                55554321,
                EMPTY_LIST,
                Collections.EMPTY_SET,
                List.of(Oscar.forBest("Animated Feature")),
                Certification.UNIVERSAL);
        context.checking(new Expectations() {{
            exactly(1).of(timeService).now();
            will(returnValue(LocalTime.now().plus(20,ChronoUnit.MINUTES)));
            //will(returnValue(LocalTime.now().plus(20, ChronoUnit.MINUTES)));
            exactly(1).of(playBackEventInterface).logWatched(user, movie);
        }});

        VideoStream stream = streamer.startStreaming(movie, user);
        streamer.stopStreaming(stream);
    }



}
