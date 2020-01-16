package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.Weapon;

public class FinalBossBehaviour extends Action{
	
	private Actor target;
	private Actor actor;
	
    int currentDistance = 0; //initialise distance
    
    
    /**
     * Child class for Actions, represents the action for FinalBoss
     */
	public FinalBossBehaviour(Actor target,Actor subject) {
		this.target = target;
		this.actor = subject;
	
	}

	/**
	 * Perform the Action of behaviour of YugoMaxx
	 * Perform the damage checking on whether it is valid - can't be hurt if Yugomaxx has exoskeleton, can drop corpse if it's down
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		//two characters can only attack when they are next to each other
		if(actor instanceof Player) {
			if (this.actor.hasSkill(Exoskeleton.Exoskeleton)) {
				return "Boss has exskeleton! I can't hurt him.";
			}
			else {
				this.actor.hurt(target.getWeapon().damage());
				String result = target.toString() +" hurts " + this.actor.toString() + " damage " + target.getWeapon().damage();

				if (!this.actor.isConscious()) {
					//Drop all the items that are droppable. 
					Item sleepingActor = new Item("Sleeping " + this.actor, '%');
					map.locationOf(this.actor).addItem(sleepingActor);
					for (Item item : this.actor.getInventory()) {
						for (Action action : item.getAllowableActions()) {
							if (action instanceof DropItemAction) {
								action.execute(this.actor, map);
								break;
							}
						}
					}
					map.removeActor(this.actor);
					result += System.lineSeparator() + this.actor + " is knocked out.";
				}

				return result;
				
			}
		}
		return null;
	}
		//when defeated, DoctorMaybe must drop a rocket engine

	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return target.toString() + " Attacks " + this.actor.toString();
	}

	/**
	 * Returns the key used in the menu to trigger this Action.
	 *
	 * There's no central management system for this, so you need to be careful not to use the same one twice.
	 * See https://en.wikipedia.org/wiki/Connascence
	 *
	 * @return The key we use for this Action in the menu.
	 */
	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "";
	}
}
