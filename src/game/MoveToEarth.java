package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Child class for Actions, represents the moving to Earth action
 */
public class MoveToEarth extends Action{
	private Rocket rocket;
	
	MoveToEarth(Rocket rocket){
		this.rocket = rocket;
	}
	
	/**
	 * Perform the Action of moving to earth
	 * Change the current map to Earth map
	 * 
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		((PlayerStatus)actor).CurrentMap = ((PlayerStatus)actor).EMap;
		return menuDescription(actor);
		
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " Move to Earth ";
	}

	/**
	 * Returns the empty string, as no hotkey is permanently assigned to pickup.
	 * @return the empty string
	 */
	@Override
	public String hotKey() {
		return "";
	}

}
