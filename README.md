# propagator
REST - Propagator quick sample using Spring boot and orekit

/Propagation -- Post request
```
{
    "initialDate": "2004-01-01T23:30:00.000",
    "duration": 1200 ,
    "stepT": 60,
    "a": 24396159,
    "e": 0.72831215,
    "i": 7,
    "omega":180,
    "raan": 261,
    "lM": 0
}
```
Where :
- initialDate is a string
- duration is the time of the progpagation in sec
- stepT is the interval of each calculation in sec
- a semi major axis in meters
- e eccentricity
- i inclination in degree
- omega perigee argument in degree
- raan right ascension of ascending node in degree
- lM mean anomaly in degree

# Launch the server

```
java -jar target/propagator-0.0.1-SNAPSHOT.jar
```
