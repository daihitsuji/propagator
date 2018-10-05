# Propagator
- Propagator quick sample using Spring boot and orekit.
- Serveur is running on the port 8080. Request can be send via postman or curl.
- User interface is also available see UI section
- Note that I didn't check coherency of returns value generated. Some bugs may have to be fixed ! :)

/Propagation -- Post request
```
{
    "initialDate": "2004-01-25T22:32:00.000Z",
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

## Backend

```
java -jar target/propagator-0.0.1-SNAPSHOT.jar
```

## Front end 
- npm and nodejs have to be installed

``` cd ui
    npm install
    npm run serve
```

- A bug is still remaining on the user interface. Date has to be change before each propagation ... sorry :(