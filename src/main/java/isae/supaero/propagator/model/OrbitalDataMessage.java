package isae.supaero.propagator.model;

import java.util.List;
import java.util.Map;

public class OrbitalDataMessage {

    Map<Integer, Orbit> OrbitalPosToEpoch;

    public void addBodyState(int date, Orbit orbit){
        OrbitalPosToEpoch.put(date,orbit);
    }

}
