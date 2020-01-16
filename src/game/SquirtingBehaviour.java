package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Range;
import edu.monash.fit2099.engine.Weapon;

/**
 * Child class for Actions, represents the squirting water for pistol action
 */
public class SquirtingBehaviour extends Action {
	private Actor target;
	private int CurrentLocation;
	private WaterPistol weapon;

	
	 /**
     * Constructor for SquirtingBehaviour class
     * @param subject the subject that it shoots to
     * @param waterPistol the water pistol as a weapon
     */
	public SquirtingBehaviour(Actor subject, WaterPistol waterPistol) {
		this.target = subject;
		this.weapon = waterPistol;
	}

	/**
	 * Perform the Action of squirting water from player's pistol to YugoMaxx
	 * Which has 70% chance of removing the exoskeleton of YugoMaxx to make it vulnerable
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		int CurrentLocation = distance(here,there);
		this.CurrentLocation = CurrentLocation;
		if(weapon.isfilled) {
			if(CurrentLocation < 3) {
				if (Math.random() > 0.3) {
					target.removeSkill(Exoskeleton.Exoskeleton);
					weapon.shoot();
					actor.removeItemFromInventory((Item) actor.getWeapon());
					return actor + " Exoskeleton is distoryed, water pistol is worn out after successful use.";
				}
				else {
					weapon.shoot();
					return "It doesn't destory the exoskeleton";
				}
		
			}
			
		}
		else {
			return "Not water!";
		}
		weapon.shoot();
		return target.toString() + " is too far! must near the boss.";
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		
		return actor + " Squirting Water to Boss!";
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
	
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
