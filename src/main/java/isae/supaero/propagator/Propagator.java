package isae.supaero.propagator;

import isae.supaero.propagator.model.OrbitSimplified;
import org.hipparchus.util.FastMath;
import org.orekit.data.DataProvidersManager;
import org.orekit.data.DirectoryCrawler;
import org.orekit.errors.OrekitException;
import org.orekit.frames.Frame;
import org.orekit.frames.FramesFactory;
import org.orekit.orbits.KeplerianOrbit;
import org.orekit.orbits.Orbit;
import org.orekit.orbits.PositionAngle;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.analytical.KeplerianPropagator;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeScale;
import org.orekit.time.TimeScalesFactory;

import isae.supaero.propagator.model.OrbitalDataMessage;

import java.io.File;
import java.util.Locale;

public class Propagator {
    /**
     * @brief Classe métier, posséde les méthode pour propagé une orbite.
     * @param Orbit
     * */
    KeplerianPropagator kepler;

    public Propagator(OrbitSimplified newOrbit) {
        try {

            File orekitData = new File("orekit-data");
            if (!orekitData.exists()) {
                System.err.format(Locale.US, "Failed to find %s folder%n",
                        orekitData.getAbsolutePath());
                System.err.format(Locale.US, "You need to download %s from the %s page and unzip it in %s for this tutorial to work%n",
                        "orekit-data.zip", "https://www.orekit.org/forge/projects/orekit/files");
                System.exit(1);
            }
            DataProvidersManager manager = DataProvidersManager.getInstance();
            manager.addProvider(new DirectoryCrawler(orekitData));

            Frame inertialFrame = FramesFactory.getEME2000();

            // Initial date in UTC time scale
            TimeScale utc = TimeScalesFactory.getUTC();
            AbsoluteDate initialDate = new AbsoluteDate(2004, 01, 01, 23, 30, 00.000, utc);

            // gravitation coefficient
            double mu =  3.986004415e+14;

            // Orbit construction as Keplerian
            Orbit initialOrbit = new KeplerianOrbit(newOrbit.getA(), newOrbit.getE(), newOrbit.getI(),
                    FastMath.toRadians(newOrbit.getOmega()),  FastMath.toRadians(newOrbit.getRaan()), FastMath.toRadians(newOrbit.getlM0()),
                    PositionAngle.MEAN, inertialFrame, initialDate, mu);

            this.kepler = new KeplerianPropagator(initialOrbit);

        } catch (OrekitException e) {
            e.printStackTrace();
        }
    }

    public OrbitalDataMessage propagate(int epochFrom, int epochTo, int interval ){
        OrbitalDataMessage resultData = new OrbitalDataMessage();
        TimeScale utc = null;
        try {
            utc = TimeScalesFactory.getUTC();
        } catch (OrekitException e) {
            e.printStackTrace();
        }
        AbsoluteDate initialDate = new AbsoluteDate(2004, 01, 01, 23, 30, 00.000, utc);
        double duration = 600.;
        AbsoluteDate finalDate = initialDate.shiftedBy(duration);
        double stepT = 60.;
        int cpt = 1;
        for (AbsoluteDate extrapDate = initialDate;
             extrapDate.compareTo(finalDate) <= 0;
             extrapDate = extrapDate.shiftedBy(stepT))  {
            SpacecraftState currentState = null;
            try {
                currentState = kepler.propagate(extrapDate);
                Orbit temp = currentState.getOrbit();
                KeplerianOrbit tempKep = new KeplerianOrbit(temp);
                OrbitSimplified currentOrbitSimplified = new OrbitSimplified(temp.getA(), temp.getE(), temp.getI(),
                        tempKep.getPerigeeArgument()*180.0/Math.PI, tempKep.getRightAscensionOfAscendingNode()*180.0/Math.PI, tempKep.getMeanAnomaly()*180.0/Math.PI);

                resultData.addBodyState(currentState.getDate().toString(),currentOrbitSimplified);
            } catch (OrekitException e) {
                e.printStackTrace();
            }

        }

        return resultData;
    }

}
