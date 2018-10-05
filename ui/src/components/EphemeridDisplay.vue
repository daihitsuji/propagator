<template>
  <div class="ephemerids">
      <h1>Ephemerids</h1>
      <table class="ui striped table">
          <tr><th>Date</th>
          <th>a</th>
          <th>e</th>
          <th>i</th>
          <th>PA</th>
          <th>raan</th>
          <th>lM</th>
          </tr>
        <tr class="ui list" v-for="(item, index) in this.ephemeridsSort">
        <td data-label="Date">{{item}} </td> <td label="a">{{ephemerids[item].a}}</td> <td label="e"> {{ephemerids[item].e}} </td> <td>{{ephemerids[item].i}}</td> <td label="Pa">{{ephemerids[item].omega}}</td>  <td label="raan"> {{ephemerids[item].raan}} </td> <td label="lM">{{ephemerids[item].lM0}}</td> </tr>
      </tr>
      </table>
     
  </div>
</template>


<script>
export default {
   props:['ephemerids'],
   data:() => {
       return {
           ephemeridsSort: [],
       }
   },
    watch:{
        ephemerids:function(){
            this.ephemeridsSort =  Object.keys(this.ephemerids).sort();
            console.log(this.ephemeridsSort);
        }
    }
}
</script>
