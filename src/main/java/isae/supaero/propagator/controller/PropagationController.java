package isae.supaero.propagator.controller;

import isae.supaero.propagator.Propagator;
import isae.supaero.propagator.model.OrbitSimplified;
import org.orekit.orbits.Orbit;

import isae.supaero.propagator.model.OrbitalDataMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropagationController {
    @PostMapping(value = "/Orbit")
    public OrbitalDataMessage calculateOrbitPropagation(@RequestBody OrbitSimplified orbit){
        Propagator myProp = new Propagator(orbit);
        return myProp.propagate(0,100,10);
    }

}
