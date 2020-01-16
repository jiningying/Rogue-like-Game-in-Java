package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;

/**
 * Class that can create different types of Action based on for the character.
 */
public class DoctorBehaviour implements ActionFactory {

    private Actor target;
    int currentDistance = 0; //initialise distance

    /**
     * Constructor.
     * 
     * @param subject the target audience of the behaviour
     */
	public DoctorBehaviour(Actor subject) {
		this.target = subject;
	}
	
	
	/**
     * getAction method to get actions:
     * 1. When defeated, must drop a rocket engine
     * 2. does not move at all
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
		
		//two characters can only attack when they are next to each other
		if(currentDistance == 1) { 
			return new AttackAction(actor,target);
		}
		
		//when defeated, DoctorMaybe must drop a rocket engine
		if(!consciousnessTest(actor)) {
			for (Item item : actor.getInventory()) {
				return new DropItemAction(item);
			}
		}
		//if none of the above occured, Doctor does not move (at all)
		else {
			return new SkipTurnAction();
		}
		return null;
	}
	
	private boolean consciousnessTest (Actor actor) {
		boolean isConsciousFlag;
		isConsciousFlag = actor.isConscious();
		return isConsciousFlag;
	}
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}
