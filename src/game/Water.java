package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing specific terrain type of Ground - water
 */
public class Water extends Ground{
	public Water() {
		super('>');
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
		 * Returns an CreateRocket Action created for this class as the only possible action.
		 *
		 * @param actor the Actor acting
		 * @param location the current Location
		 * @param direction the direction of the Ground from the Actor
		 * @return FillWater action(s)
		 */
		@Override 
		public Actions allowableActions(Actor actor,Location location,String direction) {
			return new Actions(new FillWater(direction,location));
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
