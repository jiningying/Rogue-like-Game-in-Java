package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

/**
 * Child class of Item that instantiates a specific type of item - OxygenDispenser
 */
public class OxygenDispenser extends Item{

	/**
	 * Constructor.
	 * Initialise OxygenDispenser
	 * @param name name for the OxygenDispenser
	 * @param player the player that can use the OxygenDispenser
	 */
	public OxygenDispenser(String name, Actor player) {
		super(name, 'O');
		this.allowableActions.clear(); //make Oxygen Dispenser not able to be picked up
		this.getAllowableActions().add(new Actions(new ProduceOxygen(player))); //add specific action for dispenser
	}
}