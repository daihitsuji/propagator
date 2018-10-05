package isae.supaero.propagator;

import isae.supaero.propagator.model.OrbitSimplified;
import isae.supaero.propagator.model.PropagationRq;
import org.hipparchus.util.FastMath;
import org.omg.PortableInterceptor.ORBIdHelper;
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
import java.util.Date;
import java.util.Locale;

public class Propagator {
    /**
     * @brief Classe métier, posséde les méthode pour propagé une orbite.
     * @param Orbit
     * */
    KeplerianPropagator kepler;
    OrbitSimplified newOribt;
    AbsoluteDate absoluteDate;
    double duration;
    double stepT;
    double mu = 3.986004415e+14;
    long loadingFileTime;
    long calculationTime;

    public Propagator(PropagationRq orbitRq) {
        try {
            this.loadingFileTime = System.nanoTime();
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

            this.loadingFileTime = System.nanoTime() - this.loadingFileTime;

            this.calculationTime = System.nanoTime();

            Date initialDate = orbitRq.getInitialDate();
            System.out.println(initialDate.getYear()+1900 +"-"+initialDate.getMonth()+1+"-"+initialDate.getDay()+" "+initialDate.getHours()+"-"+
                    initialDate.getMinutes()+"-"+initialDate.getSeconds());
            this.newOribt = new OrbitSimplified(orbitRq.getA(), orbitRq.getE(), orbitRq.getI(), orbitRq.getOmega(), orbitRq.getRaan(), orbitRq.getlM0());
            this.duration = orbitRq.getDuration();
            this.stepT = orbitRq.getStepT();


            TimeScale utc = null;
            try {
                utc = TimeScalesFactory.getUTC();
            } catch (OrekitException e) {
                e.printStackTrace();
            }

            this.absoluteDate = new AbsoluteDate(initialDate.getYear()+1900, initialDate.getMonth()+1, initialDate.getDate(), initialDate.getHours(),
                    initialDate.getMinutes(), initialDate.getSeconds(), utc);


            // Orbit construction as Keplerian
            Orbit initialOrbit = new KeplerianOrbit(orbitRq.getA(), orbitRq.getE(), orbitRq.getI(),
                    FastMath.toRadians(orbitRq.getOmega()),  FastMath.toRadians(orbitRq.getRaan()), FastMath.toRadians(orbitRq.getlM0()),
                    PositionAngle.MEAN, inertialFrame, absoluteDate, mu);


            this.kepler = new KeplerianPropagator(initialOrbit);



        } catch (OrekitException e) {
            e.printStackTrace();
        }
    }

    public OrbitalDataMessage propagate(){
        OrbitalDataMessage resultData = new OrbitalDataMessage();


        AbsoluteDate finalDate = absoluteDate.shiftedBy(duration);
        for (AbsoluteDate extrapDate = absoluteDate;
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
        this.calculationTime = System.nanoTime() - this.calculationTime;
        System.out.println("Total time : " + (this.calculationTime + this.loadingFileTime)/1000000 + " ms");
        System.out.println("Loading file time : " + this.loadingFileTime / 1000000 + " ms");
        System.out.println("Calculation time : " + this.calculationTime/ 1000000 + " ms")    ;
        return resultData;
    }

}
