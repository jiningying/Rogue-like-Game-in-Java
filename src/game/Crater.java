package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Class representing specific terrain type of Ground - Crater designed for the moon map
 */
public class Crater extends Ground{
	/**
	 * Constructor.
	 * a displayChar character representing crater to display for this type of terrain
	 */
	public Crater() {
		super('~');
	}
	
	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor
	 * @return true if actor has SpaceTravller skill
	 */
	@Override
	public boolean canActorEnter(Actor a) {
		return a.hasSkill(SpaceTravelSkill.SPACETRAVELLER);
	}
}
