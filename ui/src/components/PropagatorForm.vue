<template>
  <div class="initialOrbit">
   <h1>Orbit initial Input</h1>
   <div class="input inline">
    <i class="calendar icon"></i>
    <datetime :value="date" v-model="date" type="datetime" input-format="DD-MM-YYYY HH:mm" placeholder="Select an initial date"
    moment-locale="utc"></datetime>
   </div>

    <div class="ui form">
        <div class="field inline">
            <label>Propagation duration in seconds</label>
            <input v-model="duration" type="number" name="duration" placeholder="Ex: 600">
        </div>

        <div class="field inline">
            <label>Duration of each step</label>
            <input v-model="stepT" type="number" name="stepT" placeholder="Ex: 60">
        </div>

        <div class="field inline">
            <label>Semi major axis in meters</label>
            <input v-model="a" type="number" name="a" placeholder="Ex: 24396159">
        </div>

        <div class="field inline">
            <label>eccentricity</label>
            <input v-model="e" type="number" name="eccentricity" placeholder="Ex: 0.72831215">
        </div>

        <div class="field inline">
            <label>inclination in degree</label>
            <input v-model="i" type="number" name="inclination" placeholder="Ex: 7">
        </div>

        <div class="field inline">
            <label>perigee argument in degree</label>
            <input v-model="omega" type="number" name="pa" placeholder="Ex: 180">
        </div>

        <div class="field inline">
            <label>raan</label>
            <input v-model="raan" type="number" name="raan" placeholder="Ex: 261">
        </div>

        <div class="field inline">
            <label>mean anomaly</label>
            <input v-model="lM" type="number" name="lM" placeholder="Ex: 0">
        </div>
        <button @click="propagate" class="ui button primary">Propagate</button>
    </div>
    <EpheView :ephemerids="this.ephemerids"/>
  </div>
</template>

<script>
import { Datetime } from 'vue-datetime';
import EpheView from './EphemeridDisplay.vue'
export default {
    data: () => {
        return {
            date: "2004-01-25T22:32:00.000Z",
            duration: 600,
            stepT: 60,
            a: 24396159,
            e: 0.72831215,
            i: 7,
            omega: 180,
            raan: 261,
            lM: 0,

            ephemerids: {},
            
        }
    },
    methods:{
        propagate: function() {
            const dataToSend = {
                "initialDate": this.date,
                "duration": this.duration,
                "stepT": this.stepT,
                "a": this.a,
                "e": this.e,
                "i": this.i,
                "omega":this.omega,
                "raan": this.raan,
                "lM": this.lM,

                
            }
            this.$http.post('http://127.0.0.1:8080/Propagation',dataToSend).then(response => {

                this.ephemerids = response.body.orbitalPosToEpoch;
            }), response => {
                console.log(response);
            }
        }
    },
    components:{
        datetime : Datetime,
        EpheView,
        
    } 
}
</script>

