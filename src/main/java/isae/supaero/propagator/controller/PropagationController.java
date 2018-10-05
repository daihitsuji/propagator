package isae.supaero.propagator.controller;

import isae.supaero.propagator.Propagator;
import isae.supaero.propagator.model.OrbitSimplified;
import isae.supaero.propagator.model.PropagationRq;
import org.orekit.orbits.Orbit;

import isae.supaero.propagator.model.OrbitalDataMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PropagationController {

    @PostMapping(value = "/Propagation")
    public OrbitalDataMessage calculateOrbitPropagation(@RequestBody PropagationRq orbitRq){
        Propagator myProp = new Propagator(orbitRq);
        return myProp.propagate();
    }

}
