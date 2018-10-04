package isae.supaero.propagator.model;

import org.orekit.orbits.Orbit;
import org.orekit.time.AbsoluteDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbitalDataMessage {

    Map<String, OrbitSimplified> OrbitalPosToEpoch;

    public OrbitalDataMessage() {
        OrbitalPosToEpoch = new HashMap<String, OrbitSimplified>();
    }

    public void addBodyState(String date, OrbitSimplified orbit){
        OrbitalPosToEpoch.put(date,orbit);
    }

    public Map<String, OrbitSimplified> getOrbitalPosToEpoch() {
        return OrbitalPosToEpoch;
    }

    public void setOrbitalPosToEpoch(Map<String, OrbitSimplified> orbitalPosToEpoch) {
        OrbitalPosToEpoch = orbitalPosToEpoch;
    }

    @Override
    public String toString() {
        return "OrbitalDataMessage{" +
                "OrbitalPosToEpoch=" + OrbitalPosToEpoch +
                '}';
    }
}
