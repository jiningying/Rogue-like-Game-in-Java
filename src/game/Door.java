package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing specific terrain type of Ground 
 */
 public class Door extends Ground{
	 
	 /**
	 * Constructor.
	 * a displayChar character representing door to display for this type of terrain
	 */
	public Door() {
		super('+');
	}
	
	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	/**
	 * Returns an openDoor Action created for this class as the only possible action.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return
	 */
	@Override
	public Actions allowableActions(Actor actor,Location location,String direction) {
		return new Actions(new OpenDoor(direction,location));
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
