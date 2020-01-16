package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Child class for Actions, represents the filling water for pistol action
 */

public class FillWater extends Action{
	private Actor targer;
	private String direction;
	private Location WaterLocation;
	private Random rand = new Random();
	
	 /**
     * Constructor for FillWater class
     * @param direction the direction that characters try to fill water
     * @param doorLocation the direction that the water poo; is at
     */
	public FillWater(String direction, Location poolLocation) {
		this.direction = direction;
		this.WaterLocation = poolLocation;
	}
	
	/**
	 * Perform the Action of filling water
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		for(Item item:actor.getInventory()){
			if(item.toString() == "WaterPistol") {
				if (((WaterPistol) item).isFilled() == true) {
					return " the water was full";
				}
				else {
					((WaterPistol) item).isfull();
					return "Water is full";
				}
			}
		}
		return actor +" You must have a water pistol!";
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + "Fill the water " + direction;
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
