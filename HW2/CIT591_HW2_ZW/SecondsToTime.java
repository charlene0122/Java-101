/**
 * CIT 5910 HW1 Program 3
 * Converts a given number of seconds (as an int) to hours:minutes:seconds.
 *
 * @author zhifeiwu
 */
public class SecondsToTime {

	public static void main(String[] args) {
		
		//number of seconds
		int seconds = 1234;
		
		
		//TODO my code as below
		//calculate the numbers
		int minutes = seconds / 60;				// calculate the whole minutes
		int remainingSeconds = seconds % 60;	// seconds that could not round to a minute remain as seconds
		
		int hours = minutes / 60;				// calculate the whole hours
		int remainingMinutes = minutes % 60;	// seconds that could not round to an hour remain as minutes
		
		//print the result
		System.out.println(hours +":" + remainingMinutes + ":" + remainingSeconds);

	}
}
