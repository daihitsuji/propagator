package isae.supaero.propagator.model;

public class OrbitSimplified {


    public OrbitSimplified(double a, double e, double i, double omega, double raan, double lM0) {
        this.a = a;
        this.e = e;
        this.i = i;
        this.omega = omega;
        this.raan = raan;
        this.lM0 = lM0;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getOmega() {
        return omega;
    }

    public void setOmega(double omega) {
        this.omega = omega;
    }

    public double getRaan() {
        return raan;
    }

    public void setRaan(double raan) {
        this.raan = raan;
    }

    public double getlM0() {
        return lM0;
    }

    public void setlM0(double lM0) {
        this.lM0 = lM0;
    }

    double a ;// semi major axis in meters
    double e ; // eccentricity
    double i;   // inclination
    double omega; // perigee argument
    double raan; // right ascension of ascending node
    double lM0; // mean anomaly
}
