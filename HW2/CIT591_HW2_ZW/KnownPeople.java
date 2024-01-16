/**
 * CIT 5910 HW1 Program 5
 * Based on a given number of your friends, calculates the total number of known people.
 *
 * @author zhifeiwu
 */
public class KnownPeople {
	
	public static void main(String[] args) {

		//number of your friends
		int yourFriends = 3;
		
		//calculate the total number of known people
		int totalKnownPeople = 0;
		
		
		//TODO my code as below
		int knownPeople_1 = (int) Math.pow(yourFriends, 2);	//each of your friends also knows 3 people
		int knownPeople_2 = (int) Math.pow(yourFriends, 3); //each of those people also knows 3 people
		totalKnownPeople = (int) (1 + yourFriends + knownPeople_1 + knownPeople_2); //calculate total number of people
		
		//print the result
		System.out.println("You have " + yourFriends + " friends.");
		System.out.println("Each of them knows " + yourFriends + " people, which means " + knownPeople_1 + " more people.");
		System.out.println("Then each of those people also knows " + yourFriends + " people, which means " + knownPeople_2 + " more people.");
		System.out.println("This means, there are a total of " + totalKnownPeople + " known people (including yourself).");
	}
}