package isae.supaero.propagator.model;

import org.orekit.orbits.Orbit;
import org.orekit.time.AbsoluteDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbitalDataMessage {

    Map<String, OrbitSimplified> Ephemerids;

    public OrbitalDataMessage() {
        Ephemerids = new HashMap<String, OrbitSimplified>();
    }

    public void addBodyState(String date, OrbitSimplified orbit){
        Ephemerids.put(date,orbit);
    }

    public Map<String, OrbitSimplified> getOrbitalPosToEpoch() {
        return Ephemerids;
    }

    public void setOrbitalPosToEpoch(Map<String, OrbitSimplified> orbitalPosToEpoch) {
        Ephemerids = orbitalPosToEpoch;
    }

    @Override
    public String toString() {
        return "OrbitalDataMessage{" +
                "OrbitalPosToEpoch=" + Ephemerids +
                '}';
    }
}
