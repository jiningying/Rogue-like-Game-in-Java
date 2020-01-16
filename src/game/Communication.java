package game;

import edu.monash.fit2099.engine.*;

/**
 * Child class for Actions, represents the specific talking (communicating) action by Q
 */
public class Communication extends Action {

    private Actor target;
    
    /**
     * Constructor for communication class
     * @param subject the character that Q(this character) talks to
     */
	public Communication(Actor subject) {
		this.target = subject;
	}
	
	
	/**
	 * Perform the Action of communicating
	 * Make sure only Player can talk to NPC(Q), and process conversation associated with Rocket Plan
	 * 
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		//make only player and Q can talk 
		if (!(actor instanceof Player)) {
			return null;
		}
		//check if player have RocketPlan
		for (Item item: actor.getInventory()) {
			if ((item) instanceof RocketPlan) {
				//If the player does have the rocket plans in their inventory, Q should say hand them over, I don't have all day!
				return "Q: Hand them over, I don't have all day!" + "\n";
			}
		}
		//if not (RocketPlan is not in the item List)
		return "Q: I can give you something that will help, but I'm going to need the plans."; //Talking to Q without rocket plans in the player inventory should result in Q saying I can
		//give you something that will help, but I'm going to need the plans
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " to communicate with Q";
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
