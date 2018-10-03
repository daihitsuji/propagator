package isae.supaero.propagator.model;

public class Orbit {
    double a;               // semi major axis in meters
    double e;               // eccentricity
    double i;               // inclination
    double omega;           // perigee argument
    double raan;            // right ascension of ascending node
    double lM;              // mean anomaly

    public Orbit(double a, double e, double i, double omega, double raan, double lM) {
        this.a = a;
        this.e = e;
        this.i = i;
        this.omega = omega;
        this.raan = raan;
        this.lM = lM;
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

    public double getlM() {
        return lM;
    }

    public void setlM(double lM) {
        this.lM = lM;
    }

    @Override
    public String toString() {
        return "Orbit{" +
                "a=" + a +
                ", e=" + e +
                ", i=" + i +
                ", omega=" + omega +
                ", raan=" + raan +
                ", lM=" + lM +
                '}';
    }
}
