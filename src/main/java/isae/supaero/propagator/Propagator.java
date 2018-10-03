package isae.supaero.propagator;

import org.hipparchus.util.FastMath;
import org.orekit.data.DataProvidersManager;

import isae.supaero.propagator.model.Orbit;
import isae.supaero.propagator.model.OrbitalDataMessage;

public class Propagator {
    /**
     * @brief Classe métier, posséde les méthode pour propagé une orbite.
     * @param Orbit
     * */

    KeplerianPropagator kepler;
    public Propagator(Orbit newOrbit) {
        this.kepler = new KeplerianPropagator(initialOrbit);
        initialOrbit = newOrbit;
    }

    public OrbitalDataMessage propagate(int epochFrom, int epochTo, int interval ){
        double duration = 600.;
        AbsoluteDate finalDate = initialDate.shiftedBy(duration);
        double stepT = 60.;
        int cpt = 1;
        for (AbsoluteDate extrapDate = initialDate;
             extrapDate.compareTo(finalDate) <= 0;
             extrapDate = extrapDate.shiftedBy(stepT))  {
            SpacecraftState currentState = kepler.propagate(extrapDate);
            System.out.println(" time : " + currentState.getDate());
            System.out.println(" " + currentState.getOrbit());
        }

        return new OrbitalDataMessage();
    }

}
