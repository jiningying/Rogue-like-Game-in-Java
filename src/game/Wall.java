package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Class representing specific terrain type of Ground 
 */
public class Wall extends Ground {
	/**
	 * Constructor.
	 *
	 * @param displayChar character to display for this type of terrain
	 */
	public Wall() {
		super('#');
	}
	
	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor
	 * @return true
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	/**
	 * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
