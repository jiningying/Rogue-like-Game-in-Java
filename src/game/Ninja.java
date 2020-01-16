package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;

/**
 * Child class for Actor that creats different types of actors with different functionality
 */
public class Ninja extends Actor {
	
	private GameMap gamemap;
	private Actor player;
	
	/**
	 * Constructor.
	 * Create Ninja including name, displayChar, priority, hitpoints
	 * Assign it with the item it has
	 * 
	 * 
	 * @param name the name of ACTOR
	 * @param player the actor(Ninja)
	 */
	//constructor
	public Ninja(String name, Actor player,GameMap gamemap) {
		super( name ,'N', 4 , 50 ); // invoke super Actor constructor
		this.player = player;
		this.addItemToInventory(Key.newInventoryItem("Key", 'k'));
		this.gamemap = gamemap;
		//behaviour that this type of character specifically has

		addBehaviour(new StunBehaviour(player));
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	private void addBehaviour(StunBehaviour stunBehaviour) {
		actionFactories.add(stunBehaviour);
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
