package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;

/**
 * Child class for Actions, represents the producing oxygen action
 */
public class ProduceOxygen extends Action {

    private Actor target;
    /**
     * Constructor for ProduceOxygen class
     * @param subject the characte that is related to Produce Oxygen action
     */
	public ProduceOxygen (Actor subject) {
		this.target = subject;
	}
	
	
	/**
	 * Perform the Action of building tank at OxygenDispenser
	 * 
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		//if button is pressed, indicates that the tank-buidling process has started
		if ((actor instanceof Player)) {
			((PlayerStatus)actor).tankBuilding();
		}
		return "Oxygen Dispenser is successively producing oxygen...";
	}

	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " to press button: causes the dispenser to produce an oxygen tank";
	}
	
	/**
	 * Returns the key used in the menu to trigger this Action.
	 *
	 * There's no central management system for this, so you need to be careful not to use the same one twice.
	 * See https://en.wikipedia.org/wiki/Connascence
	 *
	 * @return The key we use for this Action in the menu.
	 */
	@Override
	public String hotKey() {
		return "";
	}
}
