package game;

import edu.monash.fit2099.engine.*;

/**
 * Child class for Actions, represents the exchanging action by Q
 */
public class Exchange extends Action {

	private Actor NPC;
    private Actor target;
    private Item RocketBody = new RocketBody("RocketBody");
    /**
     * Constructor for communication class
     * @param subject the character that Q(this character) talks to
     */
	public Exchange (Actor subject, Actor NPC) {
		this.target = subject;
		this.NPC = NPC;
	}
	
	
	/**
	 * Perform the Action of exchanging items
	 * Make sure only Player can exchange with to NPC(Q), update Items in the inventory and Map
	 * and process conversation associated with Rocket Plan
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
		//check if player have RocketPlan, then process exchanging process
		for (Item item: actor.getInventory()) {
			if(map == ((PlayerStatus)target).CurrentMap) {
				if ((item) instanceof RocketPlan) {
				
					actor.removeItemFromInventory (item);
					map.locationOf(NPC).addItem(RocketBody);
					map.removeActor(NPC);
					
			
			//If the player does have the rocket plans in their inventory
			//This will cause the plans to be removed and replaced by the rocket body. 
			return "Exchange completed!" +"\n" + "(Q) disappeared with a cheery wave " + "\n"; //Q will then disappear with a cheery wave.
				}
			}
		}
		//if not (RocketPlan is not in the item List)
		return "Q: I can give you something that will help, but I'm going to need the plans."; //Talking to Q without rocket plans in the player inventory should result in Q saying‘can
		//give you something that will help, but I'm going to need the plan
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " to exchange with Q";
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
