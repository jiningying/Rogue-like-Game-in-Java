package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Class that can create different types of Action based on for the character.
 */
public class FollowBehaviour implements ActionFactory {

	private Actor target;
	int currentDistance = 0; //initialise distance
	 
	/**
     * Constructor.
     * 
     * @param subject the target audience of the behaviour
     */
	public FollowBehaviour(Actor subject) {
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
		
		//check if player still has its position on the board
		if(consciousnessTest(target)) {
			currentDistance = distance(here, there);
		}
		
		//loop through different (4) directions 
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination(); //One possible destination
			if (destination.canActorEnter(actor)) {
				//calculate new distance if the destination is possible to get to
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) { //makes the character always get closer to the player
					return new MoveActorAction(destination, exit.getName());
				}
				//only possible and always to attack others when they are adjacent on the map
				if(currentDistance == 1) {
					return new AttackAction(actor,target);			
				}
			}
		}
		return null;
	}
	
	private boolean consciousnessTest (Actor actor) {
		boolean isConsciousFlag;
		isConsciousFlag = actor.isConscious();
		return isConsciousFlag;
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}