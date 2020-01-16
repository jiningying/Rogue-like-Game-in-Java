package game;

import java.util.Random;
import edu.monash.fit2099.engine.*;

/**
 * Class that can create different types of Action based on for the character.
 *
 */
public class StunBehaviour implements ActionFactory {

	private Actor target;
	
	/**
     * Constructor.
     * 
     * @param subject the target audience of the behaviour
     */
	public StunBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
     * getAction method to get the distance from target to player
     * and always make the character get closer to the player
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @return the Action to be performed
     */
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		boolean distanceVerificationFlag = verifyDistance(here,there);
		
		//execute stun behaviour if only the distance between two characters on the map is within 5 squares (both x and y axes)
			
		if (distanceVerificationFlag) {
			//check if it hitted (1 for hitted, and 2 for missed)and the player is not already stunned
			//Throw a bag of stun powder at the player, get 2 numbers randomly to get 50% chance (of hitting)
			if (Math.random() < 0.5 ) {
				//stun the player for two turns player, player will be unable to successfully perform any actions other than waiting. 
				((PlayerStatus) target).addTwoRounds();
				System.out.println("You have been stunned by Ninja");
				
			}
			//If the player is already stunned, the stun powder has no effect. And move one space away from the player regardlessly	
			int currentDistance = distance(here,there);
			//loop through different (4) directions 
			for (Exit exit : here.getExits()) {
				Location destination = exit.getDestination(); //One possible destination
				if (destination.canActorEnter(actor)) {
					//calculate new distance if the destination is possible to get to
					int newDistance = distance(destination, there);
					if (newDistance > currentDistance) { //makes the character always get away one square to the player
						return new MoveActorAction(destination, exit.getName());
					}
				}
			}
		}
		//if player is out of range of 5 squares, Ninja do nothing but stays still
		else {
			return new SkipTurnAction();
		}
		return null;
	}
	
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
		
	private boolean verifyDistance(Location a, Location b) {
		boolean moveKey = false;
		int x = Math.abs(a.x() - b.x());
		int y = Math.abs(a.y() - b.y());
		if ((x<=5) && (y<=5)){
			moveKey = true;
		}
		return moveKey;
	}
}
