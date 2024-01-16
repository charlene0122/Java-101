/**
 * CIT 5910 HW6: Movie Trivia
 * Representing a movie database using classes and ArrayLists
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import file.MovieDB;

/**
 * Test class for Movie Trivia
 */
class MovieTriviaTest {

	// instance of movie trivia object to test
	MovieTrivia mt;
	// instance of movieDB object
	MovieDB movieDB;

	@BeforeEach
	void setUp() throws Exception {
		// initialize movie trivia object
		mt = new MovieTrivia();

		// set up movie trivia object
		mt.setUp("moviedata.txt", "movieratings.csv");

		// get instance of movieDB object from movie trivia object
		movieDB = mt.movieDB;
	}

	@Test
	void testSetUp() {
		assertEquals(6, movieDB.getActorsInfo().size(),
				"actorsInfo should contain 6 actors after reading moviedata.txt.");
		assertEquals(7, movieDB.getMoviesInfo().size(),
				"moviesInfo should contain 7 movies after reading movieratings.csv.");

		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName(),
				"\"meryl streep\" should be the name of the first actor in actorsInfo.");
		assertEquals(3, movieDB.getActorsInfo().get(0).getMoviesCast().size(),
				"The first actor listed in actorsInfo should have 3 movies in their moviesCasted list.");
		assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0),
				"\"doubt\" should be the name of the first movie in the moviesCasted list of the first actor listed in actorsInfo.");

		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName(),
				"\"doubt\" should be the name of the first movie in moviesInfo.");
		assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating(),
				"The critics rating for the first movie in moviesInfo is incorrect.");
		assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating(),
				"The audience rating for the first movie in moviesInfo is incorrect.");
	}

	@Test
	void testInsertActor() {

		// Case 1: try to insert new actor with new movies
		mt.insertActor("test1", new String[] { "testmovie1", "testmovie2" }, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"After inserting an actor, the size of actorsInfo should have increased by 1.");
		assertEquals("test1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName(),
				"After inserting actor \"test1\", the name of the last actor in actorsInfo should be \"test1\".");
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(),
				"Actor \"test1\" should have 2 movies in their moviesCasted list.");
		assertEquals("testmovie1",
				movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0),
				"\"testmovie1\" should be the first movie in test1's moviesCasted list.");

		// Case 2: try to insert existing actor with new movies
		mt.insertActor("   Meryl STReep      ", new String[] { "   DOUBT      ", "     Something New     " },
				movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"Since \"meryl streep\" is already in actorsInfo, inserting \"   Meryl STReep      \" again should not increase the size of actorsInfo.");
		
		// look up and inspect movies for existing actor
		// note, this requires the use of properly implemented selectWhereActorIs method
		// you can comment out these two lines until you have a selectWhereActorIs
		// method
		assertEquals(4, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
				"After inserting Meryl Streep again with 2 movies, only one of which is not on the list yet, the number of movies \"meryl streep\" appeared in should be 4.");
		assertTrue(mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).contains("something new"),
				"After inserting Meryl Streep again with a new Movie \"     Something New     \", \"somenthing new\" should appear as one of the movies she has appeared in.");
		
		
		// Case 3: add a new actor with two new movies
		// see whether the actor and movie are stored in proper format in the actors info (lower case and no leading/trailing white space)
		mt.insertActor("   TOM CRUISE      ", new String[] { "   THE MUMMY      ", "     MISSION IMPOssibLe     " },
				movieDB.getActorsInfo());
		assertEquals(8, movieDB.getActorsInfo().size(),
				"After inserting an actor, the size of actorsInfo should have increased by 1.");
		assertEquals("tom cruise", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName(),
				"\"tom cruise\" should be the name of the last actor in actorsInfo.");
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(),
				"Actor \"tomcruise\" should have 2 movies in their moviesCasted list.");
		assertEquals("the mummy",
				movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0),
				"\"the mummy\" should be the first movie in test1's moviesCasted list.");
		assertEquals("mission impossible",
				movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(1),
				"\"mission impossible\" should be the first movie in test1's moviesCasted list.");
		
		// Case 4: add an existing actor with existing movies, with different input format, make sure actor and movie is not duplicated
		mt.insertActor("ToM CruiSE      ", new String[] { "ThE MumMY      ", "     mIssion impOSSIBLE" },
				movieDB.getActorsInfo());
		assertEquals(8, movieDB.getActorsInfo().size(),
				"Since \"tom cruise\" is already in actorsInfo, inserting \"   ToM CruiSE      \" again should not increase the size of actorsInfo.");
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(),
				"No new movies are add, the movie size should remain the same.");
		assertFalse(mt.selectWhereActorIs("tom cruise", movieDB.getActorsInfo()).contains("ThE MumMY     "),
				"The movie name should be converted to lower case and get rid of leading/trailing white space");
		assertFalse(mt.selectWhereActorIs("tom cruise", movieDB.getActorsInfo()).contains("     mIssion impOSSIBLE"),
				"The movie name should be converted to lower case and get rid of leading/trailing white space");	
	}

	@Test
	void testInsertRating() {

		// Case 1: try to insert new ratings for new movie
		mt.insertRating("testmovie", new int[] { 79, 80 }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"After inserting ratings for a movie that is not in moviesInfo yet, the size of moviesInfo should increase by 1.");
		assertEquals("testmovie", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName(),
				"After inserting a rating for \"testmovie\", the name of the last movie in moviessInfo should be \"testmovie\".");
		assertEquals(79, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating(),
				"The critics rating for \"testmovie\" is incorrect.");
		assertEquals(80, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating(),
				"The audience rating for \"testmovie\" is incorrect.");

		// Case 2: try to insert new ratings for existing movie
		mt.insertRating("doubt", new int[] { 100, 100 }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Since \"doubt\" is already in moviesInfo, inserting ratings for it should not increase the size of moviesInfo.");

		// look up and inspect movies based on newly inserted ratings
		// note, this requires the use of properly implemented selectWhereRatingIs
		// method
		// you can comment out these two lines until you have a selectWhereRatingIs
		// method
		assertEquals(1, mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).size(),
				"After inserting a critic rating of 100 for \"doubt\", there should be 1 movie in moviesInfo with a critic rating greater than 99.");
		assertTrue(mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).contains("doubt"),
				"After inserting the rating for \"doubt\", \"doubt\" should appear as a movie with critic rating greater than 99.");

		// Case 3: try to insert a list of invalid input for a new movie and make sure the movie is not added to the list
		mt.insertRating("the mummy", new int[] {}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Input is null, inserting ratings for it should not increase the size of moviesInfo.");
		mt.insertRating("the mummy", new int[] { 10, 20, 30}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Input rating length is wrong, inserting ratings for it should not increase the size of moviesInfo.");
		mt.insertRating("the mummy", new int[] {-1, 50}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Negative critic rating, inserting ratings for it should not increase the size of moviesInfo.");
		mt.insertRating("the mummy", new int[] {101, 50}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Critic rating surpass 100, inserting ratings for it should not increase the size of moviesInfo.");
		mt.insertRating("the mummy", new int[] {50, -1}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Negative audiance rating, inserting ratings for it should not increase the size of moviesInfo.");
		mt.insertRating("the mummy", new int[] {50,101}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Audience rating surpass 100, inserting ratings for it should not increase the size of moviesInfo.");

		// Case 4: change the input to valid format and see if the new movie can be added
		mt.insertRating("the mummy", new int[] {0, 100}, movieDB.getMoviesInfo());
		assertEquals(9, movieDB.getMoviesInfo().size(),
				"With valid input, the new movie should be added to the list");
		assertEquals("the mummy", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName(),
				"After inserting a rating for \"the mummy\", the name of the last movie in moviessInfo should be \"the mummy\".");
		assertEquals(0, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getCriticRating(), 
				"Critics rating should be 0.");
		assertEquals(100, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getAudienceRating(), 
				"Audience rating should be 100.");
		
		// Case 5: insert an existing movie, see if rating is updated
		mt.insertRating("the mummy", new int[] {100, 0}, movieDB.getMoviesInfo());
		assertEquals(9, movieDB.getMoviesInfo().size(),
				"Movie exist, number of movie should not change.");
		assertEquals(100, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getCriticRating(), 
				"Critics rating should be updated to 100.");
		assertEquals(0, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getAudienceRating(), 
				"Audience rating should be updated to 0.");
		
		// Case 6: insert an existing movie with invalid score, see if rating is not updated
		// insert a null list
		mt.insertRating("the mummy", new int[] {}, movieDB.getMoviesInfo());
		assertEquals(9, movieDB.getMoviesInfo().size(),
				"Movie exist, number of movie should not change.");
		assertEquals(100, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getCriticRating(), 
				"Critics rating should be 100.");
		assertEquals(0, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getAudienceRating(), 
				"Audience rating should be 0.");
		
		// insert a rating list with wrong length
		mt.insertRating("the mummy", new int[] { 1, 2, 3}, movieDB.getMoviesInfo());
		assertEquals(9, movieDB.getMoviesInfo().size(),
		"Movie exist, number of movie should not change.");
		assertEquals(100, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getCriticRating(), 
				"Critics rating should be 100.");
		assertEquals(0, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getAudienceRating(), 
				"Audience rating should be 0.");
		
		// insert a rating list with out of bound score
		mt.insertRating("the mummy", new int[] {-100, 150}, movieDB.getMoviesInfo());
		assertEquals(9, movieDB.getMoviesInfo().size(),
				"Movie exist, number of movie should not change.");
		assertEquals(100, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getCriticRating(), 
				"Critics rating should be 100.");
		assertEquals(0, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size()-1).getAudienceRating(), 
				"Audience rating should be 0.");
	}

	@Test
	void testSelectWhereActorIs() {
		
		// Case 1: see if the method returns the correct number of movies
		assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
				"The number of movies \"meryl streep\" has appeared in should be 3.");
		assertEquals("doubt", mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).get(0),
				"\"doubt\" should show up as first in the list of movies \"meryl streep\" has appeared in.");

		// Case 2: make sure non-exist actor returns an empty list
		assertTrue(mt.selectWhereActorIs("tom cruise", movieDB.getActorsInfo()).isEmpty(),
				"\"tom cruise\" is not in the actor info list, should return empty list.");
		
		// Case 3: make sure actor name is not case sensitive
		assertEquals(4, mt.selectWhereActorIs("AmY ADams", movieDB.getActorsInfo()).size(),
				"The number of movies \"amy adams\" has appeared in should be 4.");
		assertEquals("leap year", mt.selectWhereActorIs("amy adams", movieDB.getActorsInfo()).get(1),
				"\"leap year\" should show up as second in the list of movies \"amy adams\" has appeared in.");
		
		// Case 4: make sure leading/trailing white space doesn't make a difference
		assertEquals(2, mt.selectWhereActorIs("		BRAD Pitt		", movieDB.getActorsInfo()).size(),
				"The number of movies \"brad pitt\" has appeared in should be 2.");
		assertEquals("seven", mt.selectWhereActorIs("brad pitt", movieDB.getActorsInfo()).get(0),
				"\"seven\" should show up as first in the list of movies \"brad pitt\" has appeared in.");
		
	}

	@Test
	void testSelectWhereMovieIs() {
		// Case 1: see if the method returns the correct number of actors and correct actors
		assertEquals(2, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).size(),
				"There should be 2 actors in \"doubt\".");
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("meryl streep"),
				"\"meryl streep\" should be an actor who appeared in \"doubt\".");
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("amy adams"),
				"\"amy adams\" should be an actor who appeared in \"doubt\".");

		// Case 2: make sure non-exist movie returns an empty list
		assertTrue(mt.selectWhereMovieIs("the mummy", movieDB.getActorsInfo()).isEmpty(),
				"\"the mummy\" does not exist, should return empty list.");
		
		// Case 3: make sure movie name is not case sensitive
		assertEquals(2, mt.selectWhereMovieIs("THE POst", movieDB.getActorsInfo()).size(),
				"There should be 2 actors in \"the post\".");
		assertTrue(mt.selectWhereMovieIs("the post", movieDB.getActorsInfo()).contains("meryl streep"),
				"\"meryl streep\" should be an actor who appeared in \"the post\".");
		assertTrue(mt.selectWhereMovieIs("the post", movieDB.getActorsInfo()).contains("tom hanks"),
				"\"tom hanks\" should be an actor who appeared in \"the post\".");
		
		// Case 4: make sure leading/trailing white space doesn't make a difference
		assertEquals(1, mt.selectWhereMovieIs("			ARRIVAL		", movieDB.getActorsInfo()).size(),
				"There should be 1 actor in \"arrival\".");
		assertTrue(mt.selectWhereMovieIs("arrival", movieDB.getActorsInfo()).contains("amy adams"),
				"\"amy adams\" should be an actor who appeared in \"arrival\".");
		
	}

	@Test
	void testSelectWhereRatingIs() {
		
		// Case 1: basic case see if critic rating yields correct output
		assertEquals(6, mt.selectWhereRatingIs('>', 0, true, movieDB.getMoviesInfo()).size(),
				"There should be 6 movies where critics rating is greater than 0.");
		assertEquals(7, mt.selectWhereRatingIs('<', 100, true, movieDB.getMoviesInfo()).size(),
				"There should be 7 movies where critics rating is smaller than 100.");
		assertEquals(2, mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).size(),
				"There should be 2 movies where critics rating is less than 30.");
		assertEquals(1, mt.selectWhereRatingIs('=', 0, true, movieDB.getMoviesInfo()).size(),
				"There should be 1 movies where critics rating is equal to.");
		assertTrue(mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).contains("seven"),
				"\"seven\" should be selected as a movie whose critic rating is < 30.");
		assertTrue(mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).contains("popeye"),
				"\"popeye\" should be selected as a movie whose critic rating is < 30.");
		
		// see if audience rating yields correct output
		assertEquals(6, mt.selectWhereRatingIs('>', 0, false, movieDB.getMoviesInfo()).size(),
				"There should be 6 movies where audience rating is greater than 0.");
		assertEquals(7, mt.selectWhereRatingIs('<', 100, false, movieDB.getMoviesInfo()).size(),
				"There should be 7 movies where audience rating is smaller than 100.");
		assertEquals(4, mt.selectWhereRatingIs('>', 80, false, movieDB.getMoviesInfo()).size(),
				"There should be 4 movies where critics rating is greater than 80.");
		assertEquals(0, mt.selectWhereRatingIs('=', 65, false, movieDB.getMoviesInfo()).size(),
				"There should be no movie where audience rating is equal to 65.");
		assertTrue(mt.selectWhereRatingIs('>', 80, false, movieDB.getMoviesInfo()).contains("jaws"),
				"\"jaws\" should be selected as a movie whose audience rating is > 80.");
		assertTrue(mt.selectWhereRatingIs('>', 80, false, movieDB.getMoviesInfo()).contains("et"),
				"\"et\" should be selected as a movie whose audience rating is > 80.");

		// Case 2: when comparison is non-existent, should return an empty list
		assertTrue(mt.selectWhereRatingIs('?', 50, true, movieDB.getMoviesInfo()).isEmpty(),
				"Invalid comparison symbol, should return empty list.");
		assertTrue(mt.selectWhereRatingIs('.', 60, false, movieDB.getMoviesInfo()).isEmpty(),
				"Invalid comparison symbol, should return empty list.");
		
		// Case 3: see if return empty list when target rating < 0
		assertTrue(mt.selectWhereRatingIs('>', -50, false, movieDB.getMoviesInfo()).isEmpty(),
				"Target rating is out of range, should return empty list.");
		assertTrue(mt.selectWhereRatingIs('>', -10, true, movieDB.getMoviesInfo()).isEmpty(),
				"Target rating is out of range, should return empty list.");
		
		// Case 4: see if return empty list when target rating > 100
		assertTrue(mt.selectWhereRatingIs('<', 150, false, movieDB.getMoviesInfo()).isEmpty(),
				"Target rating is out of range, should return empty list.");
		assertTrue(mt.selectWhereRatingIs('<', 120, true, movieDB.getMoviesInfo()).isEmpty(),
				"Target rating is out of range, should return empty list.");
	}

	@Test
	void testGetCoActors() {
		// Case 1: see if output is correct for an existing actor
		assertEquals(2, mt.getCoActors("meryl streep", movieDB.getActorsInfo()).size(),
				"\"meryl streep\" should have 2 co-actors.");
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("tom hanks"),
				"\"tom hanks\" was a co-actor of \"meryl streep\".");
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("amy adams"),
				"\"amy adams\" was a co-actor of \"meryl streep\".");
		assertFalse(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("meryl streep"),
				"co-actor should not include \"meryl streep\" herself.");
		
		// Case 2: non-existing actor should return empty list
		assertTrue(mt.getCoActors("tom cruise", movieDB.getActorsInfo()).isEmpty(),
				"\"tom cruise\" is not on the list, return empty.");
		
		// Case 3: make sure movie name is not case sensitive
		assertEquals(1, mt.getCoActors("TOM hANkS", movieDB.getActorsInfo()).size(),
				"\"tom hanks\" should have 1 co-actors.");
		assertTrue(mt.getCoActors("toM HanKs", movieDB.getActorsInfo()).contains("meryl streep"),
				"\"meryl streep\" was a co-actor of \"tom hanks\".");
		assertFalse(mt.getCoActors("toM HanKs", movieDB.getActorsInfo()).contains("tom hanks"),
				"co-actor should not include \"tom hanks\" himself.");
				
		// Case 4: make sure leading/trailing white space doesn't make a difference
		assertEquals(1, mt.getCoActors("			amy aDams	", movieDB.getActorsInfo()).size(),
				"\"amy adams\" should have 1 co-actors.");
		assertTrue(mt.getCoActors("amy adAMS 	", movieDB.getActorsInfo()).contains("meryl streep"),
				"\"meryl streep\" was a co-actor of \"amy adams\".");
		assertFalse(mt.getCoActors("amy adAMS 	", movieDB.getActorsInfo()).contains("amy adams"),
				"co-actor should not include \"amy adams\" herself.");
			
	}

	@Test
	void testGetCommonMovie() {
		// Case 1: see if two existing actors generate correct output
		assertEquals(1, mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).size(),
				"\"tom hanks\" and \"meryl streep\" should have 1 movie in common.");
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"),
				"\"the post\" should be a common movie between \"tom hanks\" and \"meryl streep\".");
		
		// Case 2: make sure actor name is not case sensitive, and leading/trailing white space doesn't make difference
		assertEquals(1, mt.getCommonMovie("mERYl strEEP	", "		AMY aDaMs	", movieDB.getActorsInfo()).size(),
				"\"amy adams\" and \"meryl streep\" should have 1 movie in common.");
		assertTrue(mt.getCommonMovie("		MeryL STReep", "	amy AdAmS", movieDB.getActorsInfo()).contains("doubt"),
				"\"doubt\" should be a common movie between \"amy adams\" and \"meryl streep\".");
		
		// Case 3: two existing actors that have not work together should return empty list
		assertTrue(mt.getCommonMovie("brad oitt", "amy adams", movieDB.getActorsInfo()).isEmpty(),
				"\"brad pitt\" and \"amy adams\" never works together.");
		
		// Case 4: if one of or both actors does not exist, return empty list
		assertTrue(mt.getCommonMovie("abd", "cef", movieDB.getActorsInfo()).isEmpty(),
				"\"abd\" and \"cef\" do not exist, should return empty list.");
		assertTrue(mt.getCommonMovie("amy adams", "cef", movieDB.getActorsInfo()).isEmpty(),
				"\"cef\" does not exist, should return empty list.");
		assertTrue(mt.getCommonMovie("abd", "brad pitt", movieDB.getActorsInfo()).isEmpty(),
				"\"abd\" does not exist, should return empty list.");
		
		// Case 5: when actor1 and actor2 are the same, return a list of movie of this actor
		assertEquals(2, mt.getCommonMovie("brad PITT	", "	BRAD pitt	", movieDB.getActorsInfo()).size(),
				"\"brad pitt\" has 2 movies.");
		assertTrue(mt.getCommonMovie("brad PITT	", "	BRAD pitt	", movieDB.getActorsInfo()).contains("seven"),
				"\"seven\" should be a movie of \"brad pitt \".");
		assertTrue(mt.getCommonMovie("brad PITT	", "	BRAD pitt	", movieDB.getActorsInfo()).contains("fight club"),
				"\"fight club\" should be a movie of \"brad pitt \".");
	}

	@Test
	void testGoodMovies() {
		// Case 1: test if the output of original case is correct
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size(),
				"There should be 3 movies that are considered good movies, movies with both critics and audience rating that are greater than or equal to 85.");
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"),
				"\"jaws\" should be considered a good movie, since it's critics and audience ratings are both greater than or equal to 85.");
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("et"),
				"\"et\" should be considered a good movie, since it's critics and audience ratings are both greater than or equal to 85.");
		
		// make sure movies with both critics and audience rating < 85 is not included
		assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("seven"),
				"\"seven\" should not be considered a good movie, since both it's critics and audience ratings are less than 85.");
		
		// make sure movies with one rating >= 85 but the other <= 85 is not included
		assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("arrival"),
				"\"arrival\" should not be considered a good movie, since its audience ratings is less than 85.");
		
		
		// Case 2: add a good movie and see if output is correct
		mt.insertRating("test1", new int[] {90, 90}, movieDB.getMoviesInfo());
		assertEquals(4, mt.goodMovies(movieDB.getMoviesInfo()).size(),
				"There should be 4 movies that are considered good movies.");
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("test1"),
				"\"test1\" should be considered a good movie, since it's critics and audience ratings are both greater than or equal to 85.");
		
		// Case 3: update rating of a good movie, see if result if correct
		mt.insertRating("test1", new int[] {50, 90}, movieDB.getMoviesInfo());
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size(),
				"There should be 3 movies that are considered good movies.");
		assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("test1"),
				"\"test1\" should not be considered a good movie, since it's critics ratings is less than 85.");
		
		// Case 4: add a bad movie and see if output is correct
		mt.insertRating("test2", new int[] {20, 30}, movieDB.getMoviesInfo());
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size(),
				"There should be 3 movies that are considered good movies.");
		assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("test2"),
				"\"test2\" should not be considered a good movie, since it's critics ratings is less than 85.");
		
	}

	@Test
	void testGetCommonActors() {
		// Case 1: see if two existing movie get the correct output
		assertEquals(1, mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).size(),
				"There should be one actor that appeared in both \"doubt\" and \"the post\".");
		assertTrue(mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).contains("meryl streep"),
				"The actor that appeared in both \"doubt\" and \"the post\" should be \"meryl streep\".");
		
		// Case 2: see if non-existing movie return empty list
		assertTrue(mt.getCommonActors("abc", "def", movieDB.getActorsInfo()).isEmpty(),
				"\"abc\" and \"def\" do not exist, should return empty list.");
		assertTrue(mt.getCommonActors("doubt", "def", movieDB.getActorsInfo()).isEmpty(),
				"\"def\" does not exist, should return empty list.");
		assertTrue(mt.getCommonActors("abc", "arrival", movieDB.getActorsInfo()).isEmpty(),
				"\"abc\" does not exist, should return empty list.");
		
		// Case 3: make sure movie names are not case sensitive, leading/trailing white space doesn't make difference
		assertEquals(1, mt.getCommonActors(" SEVeN	", "		Fight clUB", movieDB.getActorsInfo()).size(),
				"There should be one actor that appeared in both \"seven\" and \"fight club\".");
		assertTrue(mt.getCommonActors("	sevEn", "FiGHT club		", movieDB.getActorsInfo()).contains("brad pitt"),
				"The actor that appeared in both \"seven\" and \"fight club\" should be \"brad pitt\".");
		
		// Case 4: when movie1 and movie2 are the same, return a list of actors in the movie
		assertEquals(2, mt.getCommonActors("DOUbt  ", " douBT	", movieDB.getActorsInfo()).size(),
				"There should be one actor that appeared in \"doubt\".");
		assertTrue(mt.getCommonActors("DOUbt  ", " douBT		", movieDB.getActorsInfo()).contains("amy adams"),
				"The actor that appeared in \"doubt\" should be \"amy adams\".");
	}

	@Test
	void testGetMean() {
		// Case 1: test if the average score is correct
		assertEquals(67.857, MovieTrivia.getMean(movieDB.getMoviesInfo())[0], 0.001);
		assertEquals(65.714, MovieTrivia.getMean(movieDB.getMoviesInfo())[1], 0.001);
		
		// Case 2: change the rating an existing movie, test again
		mt.insertRating("arrival",new int[] {10, 10}, movieDB.getMoviesInfo());
		assertEquals(55.857, MovieTrivia.getMean(movieDB.getMoviesInfo())[0], 0.001);
		assertEquals(55.428, MovieTrivia.getMean(movieDB.getMoviesInfo())[1], 0.001);
		
		// Case 3: change the rating of an existing movie, test again
		mt.insertRating("seven",new int[] {81, 82}, movieDB.getMoviesInfo());
		assertEquals(63.286, MovieTrivia.getMean(movieDB.getMoviesInfo())[0], 0.001);
		assertEquals(63.00, MovieTrivia.getMean(movieDB.getMoviesInfo())[1], 0.001);
		
		// Case 4: insert a rating of a new movie, test again
		mt.insertRating("test case",new int[] {15, 25}, movieDB.getMoviesInfo());
		assertEquals(57.250, MovieTrivia.getMean(movieDB.getMoviesInfo())[0], 0.001);
		assertEquals(58.250, MovieTrivia.getMean(movieDB.getMoviesInfo())[1], 0.001);
	}
}
