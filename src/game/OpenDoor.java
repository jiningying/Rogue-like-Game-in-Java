package game;

import java.util.Random;

import edu.monash.fit2099.demo.Floor;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Child class for Actions, represents the opening door action
 */
public class OpenDoor extends Action{
	
	
	private Actor targer;
	private String direction;
	private Location DoorLocation;
	private Random rand = new Random();
	
	 /**
     * Constructor for OpenDoor class
     * @param direction the direction that characters try to open
     * @param doorLocation the direction that the door is at
     */
	public OpenDoor(String direction, Location doorLocation) {
		this.direction = direction;
		this.DoorLocation = doorLocation;
	}
	
	/**
	 * Perform the Action of opening door
	 * Make sure only key can open the door and after opening, remove from the inventory
	 * 
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		for(Item item:actor.getInventory()){
			if(item.toString() == "Key") {
				map.add(new Floor(), DoorLocation);
				actor.removeItemFromInventory(item);
				return "The door is open";
			}
		}
		return actor +"You need a key";
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " Open the door towards " + direction;
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
