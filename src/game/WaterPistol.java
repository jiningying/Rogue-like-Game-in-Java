package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;


/**
 * Class representing waterpistol item that can be used as a weapon.
 */
public class WaterPistol extends WeaponItem implements Weapon {
	
	/**
	 * set as public to be able to check if pistol is filled or not
	 */
	public boolean isfilled = false;
	private Actor actor;
	
	/** Constructor.
	 *
	 * @param name name of the item
	 * @param actor the actor that possess the item
	 */
	public WaterPistol(String name,Actor actor) {
		super("WaterPistol", '!', 12, "Squirting");
		this.actor = actor;
			
	}
	
	/**
	 * check whether the water pistol is filled
	 */
	public void isfull(){
		isfilled = true;	
	}
	
	/**
	 * can shoot water if the water pistol is filled
	 */
	public void shoot() {
		isfilled = false;
	}
	
	/**
	 * check whether the water pistol is filled
	 * @return true if isfilled is true
	 */
	public boolean isFilled() {
			return isfilled;
			
		}
	}
	
	
