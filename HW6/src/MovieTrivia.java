/**
 * CIT 5910 HW6: Movie Trivia
 * Representing a movie database using classes and ArrayLists
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */
import java.util.ArrayList;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	MovieDB movieDB = new MovieDB();
	
	
	public static void main(String[] args) {
		
		//create instance of movie trivia class
		MovieTrivia mt = new MovieTrivia();
		
		//setup movie trivia class
		mt.setUp("moviedata.txt", "movieratings.csv");
	}
	
	/**
	 * Sets up the Movie Trivia class
	 * @param movieData .txt file
	 * @param movieRatings .csv file
	 */
	public void setUp(String movieData, String movieRatings) {
		//load movie database files
		movieDB.setUp(movieData, movieRatings);
		
		//print all actors and movies
		this.printAllActors();
		this.printAllMovies();		
	}
	
	/**
	 * Prints a list of all actors and the movies they acted in.
	 */
	public void printAllActors () {
		System.out.println(movieDB.getActorsInfo());
	}
	
	/**
	 * Prints a list of all movies and their ratings.
	 */
	public void printAllMovies () {
		System.out.println(movieDB.getMoviesInfo());
	}
	
	
	// TODO add additional methods as specified in the instructions PDF
	/**
	 * Inserts given actor and his/her movies into database
	 * @param actor the actor name as a string
	 * @param movies a String array of movie names that the actor has acted in
	 * @param actorsInfo the ArrayList that is to be inserted into/updated
	 */
	public void insertActor (String actor, String [] movies, ArrayList <Actor> actorsInfo)
	{
		//converts all characters to lower case and trims the whitespace before and after
		actor = actor.trim().toLowerCase(); 		 
		Actor newActor = new Actor(actor);		//create a new actor by name
		boolean containsActor = false;			//assume the list doen't contain the actor
		
		//iterate through the actor info array list to find the actor
		for (int i = 0; i < actorsInfo.size(); i++)
		{
			//if the actor exists in the list
			if (actorsInfo.get(i).getName().equals(actor))
				{
					//change contains actor to true
					containsActor = true;
					//change newActor to the existing actor
					newActor = actorsInfo.get(i);
					break;
				}
		}
		
		//if the list doesn't contain the actor, append the actor to the end of the list
		if (!containsActor)
			actorsInfo.add(newActor);
		
		//create a variable to store new actor's movie list
		ArrayList<String> movieList = newActor.getMoviesCast();
		
		//iterate through the movie list passed in
		for (int i = 0; i < movies.length; i++)
		{
			//converts all characters to lower case and trims the whitespace before and after
			movies[i] = movies[i].trim().toLowerCase();
			//if the movie is not in actor's movie list, append it to the end of the list
			if (!movieList.contains(movies[i]))
				movieList.add(movies[i]);
		}
	}
	
	/**
	 * Inserts given ratings for given movie into database
	 * @param movie the movie name as a string
	 * @param ratings an int array with 2 elements: the critics rating at index 0 and the audience
	 * rating at index 1
	 * @param moviesInfo the ArrayList that is to be inserted into/updated
	 */
	public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo)
	{
		// if incorrect number in ratings or missing ratings, do nothing and return
		if (ratings == null || ratings.length !=2)
			return;
		if (ratings[0] < 0 || ratings[0] > 100)
			return;
		if (ratings[1] < 0 || ratings[1] > 100)
			return;
		
		//converts all characters to lower case and trims the whitespace before and after
		movie = movie.trim().toLowerCase();
		
		// create a new movie object, pass the name and rating score into the variable
		Movie newMovie = new Movie(movie, ratings[0], ratings[1]);
		
		//iterate through the list to find the movie
		boolean containsMovie = false;
		for (int i = 0; i < moviesInfo.size(); i++)
		{
			//if movie is in the list, replace it with the new score passed in
			if (moviesInfo.get(i).getName().equals(movie))
				{
					moviesInfo.set(i, newMovie);
					// set contains movie to true
					containsMovie = true;
					break;
				}
		}
		// if the list does not contain the movie, add it to the end of the list
		if (!containsMovie)
			moviesInfo.add(newMovie);
	}
	
	/**
	 * Given an actor, returns the list of all movies
	 * @param actor the name of an actor as a String
	 * @param actorsInfo the ArrayList to get the data from
	 * @return the list of all movies of the given actor
	 */
	public ArrayList <String> selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo)
	{
		// converts all characters to lower case and trims the whitespace before and after
		actor = actor.trim().toLowerCase();
		
		//create an empty list
		ArrayList <String> empty = new ArrayList <String>();
		
		// if actor can be found in the list, return his/her movies cast
		for (int i = 0; i < actorsInfo.size(); i++)
		{
			if (actorsInfo.get(i).getName().equals(actor))
				return actorsInfo.get(i).getMoviesCast();
		}
		
		//otherwise, return an empty list
		return empty;
	}
	
	/**
	 * Given a movie, returns the list of all actors in that movie
	 * @param movie the name of a movie as a String
	 * @param actorsInfo the ArrayList to get the data from
	 * @return the list of all actors in the given movie
	 */
	public ArrayList <String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo)
	{
		//converts all characters to lower case and trims the whitespace before and after
		movie = movie.trim().toLowerCase();
		
		// create an empty list to store actor names
		ArrayList <String> movieActors = new ArrayList <String>();
		
		// iterate through the actor list, 
		for (int i = 0; i < actorsInfo.size(); i++)
		{
			//if the actor's movie cast list contains the current movie, 
			if (actorsInfo.get(i).getMoviesCast().contains(movie))
				//add the actor's name to the list
				movieActors.add(actorsInfo.get(i).getName());
		}
		
		//return the list
		return movieActors;
	}
	
	/**
	 * Select a list of movies that satisfy an inequality or equality, 
	 * based on the comparison argument and the targeted rating argument.
	 * @param comparison either ‘=’, ‘>’, or ‘< ‘, passed in as a char
	 * @param targetRating an integer
	 * @param isCritic a boolean that represents whether we are interested in the critics rating or the
	 * audience rating. true = critic ratings, false = audience ratings.
	 * @param moviesInfo
	 * @return returns a list of movies that satisfy an inequality or equality, 
	 * based on the comparison argument and the targeted rating argument
	 */
	public ArrayList <String> selectWhereRatingIs (char comparison, int targetRating, boolean isCritic, ArrayList <Movie> moviesInfo)
	{
		// create an empty list to store movie names that satisfy specified condition
		ArrayList <String> movieList = new ArrayList <String>();
		
		// if target rating is out of range, return an empty list
		if (targetRating < 0 || targetRating >100)
			return movieList;
		
		// if critic, call get critic rating method
		if (isCritic)
		{
			// iterate through the movie list, find movies that satisfy the condition and add them to the list
			for (int i = 0; i < moviesInfo.size(); i++)
			{
				if (moviesInfo.get(i).getCriticRating() < targetRating && comparison == '<')
					movieList.add(moviesInfo.get(i).getName());
				if (moviesInfo.get(i).getCriticRating() == targetRating && comparison == '=')
					movieList.add(moviesInfo.get(i).getName());
				if (moviesInfo.get(i).getCriticRating() > targetRating && comparison == '>')
					movieList.add(moviesInfo.get(i).getName());
			}
		}
		
		// if not critic, call get audience rating method
		if (!isCritic)
		{
			// iterate through the movie list, find movies that satisfy the condition and add them to the list
			for (int i = 0; i < moviesInfo.size(); i++)
			{
				if (moviesInfo.get(i).getAudienceRating() < targetRating && comparison == '<')
					movieList.add(moviesInfo.get(i).getName());
				if (moviesInfo.get(i).getAudienceRating() == targetRating && comparison == '=')
					movieList.add(moviesInfo.get(i).getName());
				if (moviesInfo.get(i).getAudienceRating() > targetRating && comparison == '>')
					movieList.add(moviesInfo.get(i).getName());
			}
		}
		
		//return the list
		return movieList;
	}
	
	/**
	 * Get a list of all actors that the given actor has ever worked with in any movie except the actor herself/himself.
	 * @param actor the name of an actor as a String
	 * @param actorsInfo the ArrayList to search through
	 * @return a list of all actors that the given actor has ever worked with in any movie except the actor herself/himself.
	 */
	public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo)
	{
		// converts all characters to lower case and trims the whitespace before and after
		actor = actor.trim().toLowerCase();
		// create an empty list
		ArrayList <String> coActors = new ArrayList <String> ();
		
		// get the list of movie that the actor has cast
		ArrayList <String> movies = selectWhereActorIs (actor, actorsInfo);
		
		// iterate through the movie list
		for (int i = 0; i < movies.size(); i++)
		{
			// get the actors in the current movie
			ArrayList <String> currentActors = selectWhereMovieIs (movies.get(i),actorsInfo);
			// if the current actor is not the actor himself/herself, and the actor is not in the list
			// add him/her to the list
			for (int j = 0; j < currentActors.size(); j++)
			{
				if (!currentActors.get(j).equals(actor) && !coActors.contains(currentActors.get(j)))
					coActors.add(currentActors.get(j));
			}
		}
		
		//return the list
		return coActors;
	}
	
	/**
	 * Get a list of movie names where both actors were cast.	
	 * @param actor1 actor name as Strings
	 * @param actor2 actor name as Strings
	 * @param actorsInfo the ArrayList to search through
	 * @return a list of movie names where both actors were cast.	
	 */
	public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor> actorsInfo)
	{
		// get a list of movies that actor 1 was cast
		ArrayList <String> actor1Movies = selectWhereActorIs (actor1, actorsInfo);
		// get a list of movies that actor 2 was cast
		ArrayList <String> actor2Movies = selectWhereActorIs (actor2, actorsInfo);
		
		// create an empty string
		ArrayList <String> commonMovies = new ArrayList <String> ();
		
		// iterate through actor 1's movie list
		for (int i = 0; i < actor1Movies.size(); i++)
		{
			// if actor2's movie list contains this movie, add it to the list
			if (actor2Movies.contains(actor1Movies.get(i)))
				commonMovies.add(actor1Movies.get(i));
		}
		
		// return the list
		return commonMovies;
	}
	
	/**
	 * Get a list of movie names that both critics and the audience have rated above 85 (>= 85).
	 * @param moviesInfo
	 * @return a list of movie names that both critics and the audience have rated above 85 (>= 85).
	 */
	public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo)
	{
		// create an empty list
		ArrayList <String> movies = new ArrayList <String> ();
		
		// iterate through the movie list, if both rating is >= 85, add the movie's name to the new list
		for (int i = 0; i < moviesInfo.size(); i++)
		{
			if (moviesInfo.get(i).getCriticRating() >= 85 && moviesInfo.get(i).getAudienceRating() >= 85)
				movies.add(moviesInfo.get(i).getName());
		}
		
		//return the list
		return movies;
	}
	
	/**
	 * Given a pair of movies, this method returns a list of actors that acted in both movies
	 * @param movie1 the names of movies as Strings
	 * @param movie2 the names of movies as Strings
	 * @param actorsInfo the actor ArrayList
	 * @return a list of actors that acted in both movies
	 */
	public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor> actorsInfo)
	{
		//converts all characters to lower case and trims the whitespace before and after
		movie1 = movie1.trim().toLowerCase();
		movie2 = movie2.trim().toLowerCase();
		
		// get a list of actors in movie1
		ArrayList <String> actors1 = selectWhereMovieIs (movie1, actorsInfo);
		// get a list of actors in movie2
		ArrayList <String> actors2 = selectWhereMovieIs (movie2, actorsInfo);
		// create an empty list
		ArrayList <String> comActors = new ArrayList <String> ();
		
		// iterate through the actors in movie1, if the actor is also in movie2, add him/her to the list 
		for (int i = 0; i < actors1.size(); i++)
		{
			if (actors2.contains(actors1.get(i)))
				comActors.add(actors1.get(i));
		}
		//return the list
		return comActors;
	}
	
	/**
	 * Given the moviesInfo DB, this static method returns 
	 * the mean value of the critics ratings and the audience ratings
	 * @param moviesInfo the moviesInfo DB
	 * @return the mean value of the critics ratings and the audience ratings
	 */
	public static double [] getMean (ArrayList <Movie> moviesInfo)
	{
		// create a variable to store size of array
		int num = moviesInfo.size();
		// create an array of double to return
		double[] meanRating = new double[] {0.0, 0.0};
		
		// if the list is empty, return 0
		if (num == 0)
			return meanRating;
		
		// create two variables to hold the total score
		double totalCritic = 0, totalAudience = 0;
		
		// iterate through the list and add each movie's score to the total score
		for (int i = 0; i < num; i++)
		{
			totalCritic += moviesInfo.get(i).getCriticRating();
			totalAudience += moviesInfo.get(i).getAudienceRating();
		}
		
		// calculate the average score and return them
		meanRating[0] = totalCritic / num;
		meanRating[1] = totalAudience / num;
		return meanRating;
	}
	
	
}
