package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


/**
 * Interface for action classes used by actor to get actions
 */
public interface ActionFactory {
	Action getAction(Actor actor, GameMap map);
}
