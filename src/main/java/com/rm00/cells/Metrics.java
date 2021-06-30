package com.rm00.cells;

public class Metrics {

    private final static double C = Math.PI/180.;
    private static double degreesToRadians(double x) {
        return x*C;
    }

    /**
     * source: en.wikipedia.org/wiki/Great-circle_distance
     * @param pointA initial point
     * @param pointB final point
     * @return the distance between the two points, in km
     */
    public static double distanceInKm(Coordinates2D pointA, Coordinates2D pointB) {
        double R = 6.371E3;                                 // earth mean radius
        double phiA = degreesToRadians(pointA.getLatitude());
        double phiB = degreesToRadians(pointB.getLatitude());
        double psiA = degreesToRadians(pointA.getLongitude());
        double psiB = degreesToRadians(pointB.getLongitude());
        double deltaPhi = phiA - phiB;
        double deltaPsi = psiA - psiB;

        double havPhi = Math.pow( Math.sin(deltaPhi/2.), 2);
        double havPsi = Math.pow( Math.sin(deltaPsi/2.), 2);
        return 2*R*Math.asin( Math.sqrt( havPhi + Math.cos(phiA) * Math.cos(phiB) * havPsi ) );
    }
}
