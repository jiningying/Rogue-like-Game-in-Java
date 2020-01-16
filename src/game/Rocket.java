package game;

import edu.monash.fit2099.engine.Item;

/**
 * Child class of Item that instantiates a specific type of item - Rocket
 */
public class Rocket extends Item{
	/**
	 * Constructor.
	 * Initialise Rocket
	 * @param name name for the rocket
	 */
	public Rocket(String name, char displayChar) {
		super(name, 'R');
		
	}

}
