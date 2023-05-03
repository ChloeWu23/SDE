package ic.doc;

import ic.doc.movies.Movie;

public interface PlayBackEventInterface {
    void logWatched(User user, Movie movie);
}
