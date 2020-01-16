package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;

/**
 * Child class for Actions, represents the Picking Up Filled Tank action by player
 */
public class PickUpFilledTank extends Action {

    private Actor target;
    /**
     * Constructor for PickUpFilledTank class
     * @param subject the character that is being picked up
     */
	public PickUpFilledTank (Actor subject) {
		this.target = subject;
	}
	
	
	/**
	 * Perform the Action of adding oxygen to player if it is picked up
	 * remove the oxygen tank and enable rebuilding for the new tank at location of OxygenDispenser
	 * 
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> itemList = map.at(map.locationOf(actor).x(), map.locationOf(actor).y()).getItems();
		
		//if tank is picked up, equip player with 10 units of oxygen
		if ((actor instanceof Player)) {
			((PlayerStatus)actor).addOxygen();
			
			//remove oxygen tank
			for (int i = 0; i < itemList.size(); i++) {
				if (itemList.get(i).toString() == "Oxygen Tank") {
					//in case that multiple items exsited on the location
					map.locationOf(actor).removeItem(itemList.get(i));
				}
			}
			
			//when tank is picked up, new tank is avaiable to be built again
			((PlayerStatus)actor).tankRebuildingEnabled();
		}
		return "Oxygen Tank has been picked up! You are possessed with 10 more units of oxygen!";
	}

	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " to pick up the filled oxygen tank -- contains 10 units of oxygen";
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
