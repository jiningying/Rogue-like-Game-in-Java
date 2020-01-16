package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Child class for Actor that creats different types of actors with different functionality
 */
//follow the player around and slap them, but don’t do much damage
public class Grunt extends Actor {
	
	private GameMap gamemap;
	private Actor player;
	/**
	 * Constructor.
	 * Create Grunt including name, displayChar, priority, hitpoints
	 * Assign it with the item it has
	 * 
	 * 
	 * @param name the name of ACTOR
	 * @param player the actor(Grunt)
	 */
	// Grunts have 50 hitpoints and are always represented with a g
	public Grunt(String name, Actor player,GameMap gamemap) {
		super(name, 'g', 5 , 50 );
		this.player = player;
		this.gamemap = gamemap;
		this.addItemToInventory(Key.newInventoryItem("Key", 'k'));
		addBehaviour(new FollowBehaviour(player)); //behaviour that this type of character specifically has

	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>(); // list of Actions implemented with interface ActionFactory

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	//don't do much damage
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(4, "slaps");
    }
	
	/**
     * Override playTurn method to get the actions from this actor
     * if no action is specifically defined for this actor, return one of the default actions randomly
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed
     */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		if(gamemap == ((PlayerStatus)player).CurrentMap){
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		}
		
		return new SkipTurnOtherPlanet();
	}
}
