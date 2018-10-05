package isae.supaero.propagator.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PropagationRq extends OrbitSimplified {
    Date initialDate;
    double duration;
    double stepT;

    public PropagationRq(double a, double e, double i, double omega, double raan, double lM0, String initialDate, double duration, double stepT) {
        super(a, e, i, omega, raan, lM0);
        //2004-01-22T22:32:00.000Z
        //2004-01-25T22:32:00.000Z
        //2004-01-21T22:32:00.000Z
        System.out.println(initialDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        try {
            this.initialDate = format.parse(initialDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        this.duration = duration;
        this.stepT = stepT;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getStepT() {
        return stepT;
    }

    public void setStepT(double stepT) {
        this.stepT = stepT;
    }

    @Override
    public String toString() {
        return "PropagationRq{" +
                "initialDate=" + initialDate +
                ", duration=" + duration +
                ", stepT=" + stepT +
                ", a=" + a +
                ", e=" + e +
                ", i=" + i +
                ", omega=" + omega +
                ", raan=" + raan +
                ", lM0=" + lM0 +
                '}';
    }
}
