package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing specific terrain type of Ground 
 * RocketPad as the final location to combine all parts to win game for user
 */
public class RocketPad extends Ground{

	/**
	 * Constructor.
	 * a displayChar character representing RocketPad to display for this type of terrain
	 */
	public RocketPad() {
		super('X');
		// TODO Auto-generated constructor stub
	}
		
	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor
	 * @return true
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return true;
	}
	
	/**
	 * Returns an CreateRocket Action created for this class as the only possible action.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return
	 */
	@Override 
	public Actions allowableActions(Actor actor,Location location,String direction) {
		return new Actions(new CreateRocket(location,direction));
	}
	
	/**
	 * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return false;
	}

}
