# Vessel Name Reader

## Basic implementation of thread pool in java

VesselService.getNames(String mmsi) receives mmsi of a vessel and return all vessels that has this mmsi (Note, mmsi isn't unique - multiple vessels can be returned by the same mmsi).

For each API call, all data files located in /resources are scanned.


### Usage:
Compile and run:
```
javac *.java
java Test
```

Results:
```
Found the following names for mmsi 456:
vesselE
vesselB

Found the following names for mmsi 140:
vesselG

Found the following names for mmsi 999:
```
