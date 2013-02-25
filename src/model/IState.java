package model;

import util.Location;
import util.Vector;

public interface IState {
    public Location getLocation();
    
    public Vector getHeading();
}
