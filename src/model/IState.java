package model;

import util.Location;


/**
 * Interface that provides information on the state of the object implemented
 * 
 * @author Henrique Moraes
 * 
 */
public interface IState {
    public Location getCenter ();

    /**
     * @return the absolute heading of the object on the perspective of
     *         the user
     */
    public int getHeading ();

    public boolean isPenWriting ();

    public boolean isHiding ();
}
