package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.SkipTurnAction;

/**
 * Child class for Actor that creats different types of actors with different functionality
 */
public class DoctorMaybe extends Actor{
	private GameMap gamemap;
	/**
	 * Create doctorMaybe including name, hitpoints, priority and assign the RocketEngine to it
	 * 
	 * 
	 * @param name the name of ACTOR
	 * @param player the actor(DoctorMaybe)
	 */
	public DoctorMaybe(String name, Actor player,GameMap gamemap) {
		
		super(name, 'D', 8 , 25);
		this.gamemap = gamemap;
		//behaviour that this type of character specifically has
		if(gamemap == ((PlayerStatus)player).CurrentMap){
		addBehaviour(new DoctorBehaviour(player));
		}
		
		this.addItemToInventory(RocketEngine.newInventoryItem("RocketEngine", 'E')); //assign specific item for this character
	}

	
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	/**
	 * add the behavior to a list of ActionFactory
	 * 
	 * @param DoctorBehaviour a action for DoctorMaybe
	 */
	private void addBehaviour(ActionFactory DoctorBehaviour) {
		actionFactories.add(DoctorBehaviour);
	}
	
	//does half the damage of a Grunt
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(2, "touches");
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
		
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		return new SkipTurnAction();
	}
}
	

