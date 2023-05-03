package ic.doc;

import ic.doc.movies.Movie;
import ic.doc.streaming.PlaybackEventLog;
import ic.doc.streaming.StreamTracker;
import ic.doc.streaming.VideoStream;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class VideoStreamer implements TimeService,PlayBackEventInterface{
  private final PlayBackEventInterface playbackEvents;
  private final Map<VideoStream, StreamTracker> currentStreams = new HashMap<>();
  private final Library library;
  private final TimeService timeService;
  public VideoStreamer(PlaybackEventLog playbackEvents, PlayBackEventInterface playbackEvents1, Library library1, TimeService timeService) {
    this.playbackEvents = playbackEvents1;

    library = library1;
    this.timeService = timeService;
  }

  public List<Movie> getSuggestedMovies(User user) {
    //List<Movie> recommendations = new MediaLibrary().recommendedMoviesFor(user);
    //List<Movie> recommendations = MediaLibrary.getInstance().recommendedMoviesFor(user);
    //question b: create an interface for MediaLibrary
    List<Movie> recommendations = library.recommendedMoviesFor(user);
    // sort the list of suggestions in descending order of number of views
    List<Movie> suggestions =  new ArrayList<>(recommendations);
    suggestions.sort(Comparator.comparing(Movie::numberOfViews).reversed());
    return suggestions;
  }

  public VideoStream startStreaming(Movie movie, User user) {
    VideoStream stream = new VideoStream(movie);
    currentStreams.put(stream, new StreamTracker(user));
    return stream;
  }

  //to make it more testable, add an paramter time in stopstraming method
  public void stopStreaming(VideoStream stream) {
    StreamTracker streamTracker = currentStreams.remove(stream);
    LocalTime endTime = timeService.now();
    long minutesWatched = ChronoUnit.MINUTES.between(streamTracker.startTime(), endTime);
    if (minutesWatched > 15) {
      playbackEvents.logWatched(streamTracker.user(), stream.movie());
    }
  }


  @Override
  public LocalTime now() {
    return LocalTime.now();
  }


  @Override
  public void logWatched(User user, Movie movie) {
    System.out.printf("%s enjoyed %s %n", user, movie.title());

  }
}


