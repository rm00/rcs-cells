import com.rm00.cells.*;
import com.rm00.cells.strength.StrengthFactory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class ServicesTest {

    private final static Coordinates2D garbagnateMilanese = new Coordinates2D(45.5774, 9.0728);  // source: comuni-italiani.it
    private final static Coordinates2D arese = new Coordinates2D(45.5532, 9.0776);
    private final static Coordinates2D bollate = new Coordinates2D(45.5474, 9.1216);
    private final static Coordinates2D tradate = new Coordinates2D(45.7119, 8.9087);
    private final static Coordinates2D saronno = new Coordinates2D(45.6288, 9.0357);
    private final static Coordinates2D cesanoMaderno = new Coordinates2D(45.6313, 9.1533);
    private final static Coordinates2D milano = new Coordinates2D(45.4773, 9.1815);
    private final static Coordinates2D lodi = new Coordinates2D(45.3145, 9.5039);
    private final static Coordinates2D bergamo = new Coordinates2D(45.6989, 9.67);

    private final static double distanceArese = Metrics.distanceInKm(arese, garbagnateMilanese);            // 2.71
    private final static double distanceBollate = Metrics.distanceInKm(bollate, garbagnateMilanese);        // 5.05
    private final static double distanceTradate = Metrics.distanceInKm(tradate, garbagnateMilanese);        // 19.65
    private final static double distanceSaronno = Metrics.distanceInKm(saronno, garbagnateMilanese);        // 6.40
    private final static double distanceCesano = Metrics.distanceInKm(cesanoMaderno, garbagnateMilanese);   // 8.66
    private final static double distanceMilano = Metrics.distanceInKm(milano, garbagnateMilanese);          // 13.98
    private final static double distanceLodi = Metrics.distanceInKm(lodi, garbagnateMilanese);              // 44.56
    private final static double distanceBergamo = Metrics.distanceInKm(bergamo, garbagnateMilanese);        // 48.35

    private final static double radiusInKm = 7.;
    private final static Cell areseRadiusCell = new Cell("Arese-Radius", ServicesTest.arese, StrengthFactory.getRadiusStrength(radiusInKm));
    private final static Cell bollateRadiusCell = new Cell("Bollate-Radius", ServicesTest.bollate, StrengthFactory.getRadiusStrength(radiusInKm));
    private final static Cell tradateRadiusCell = new Cell("Tradate-Radius", ServicesTest.tradate, StrengthFactory.getRadiusStrength(radiusInKm));
    private final static Cell saronnoRadiusCell = new Cell("Saronno-Radius", ServicesTest.saronno, StrengthFactory.getRadiusStrength(radiusInKm));
    private final static Cell cesanoRadiusCell = new Cell("Cesano-Radius", ServicesTest.cesanoMaderno, StrengthFactory.getRadiusStrength(radiusInKm));
    private final static Cell milanoRadiusCell = new Cell("Milano-Radius", ServicesTest.milano, StrengthFactory.getRadiusStrength(radiusInKm));
    private final static Cell lodiRadiusCell = new Cell("Lodi-Radius", ServicesTest.lodi, StrengthFactory.getRadiusStrength(radiusInKm));
    private final static Cell bergamoRadiusCell = new Cell("Bergamo-Radius", ServicesTest.bergamo, StrengthFactory.getRadiusStrength(radiusInKm));

    private final static double power = 2.5;
    private final static Cell aresePowerCell = new Cell("Arese-Power", ServicesTest.arese, StrengthFactory.getPowerStrength(power));            // 8.34E-2
    private final static Cell bollatePowerCell = new Cell("Bollate-Power", ServicesTest.bollate, StrengthFactory.getPowerStrength(power));      // 9.78E-3
    private final static Cell tradatePowerCell = new Cell("Tradate-Power", ServicesTest.tradate, StrengthFactory.getPowerStrength(power));      // 1.5E-8
    private final static Cell saronnoPowerCell = new Cell("Saronno-Power", ServicesTest.saronno, StrengthFactory.getPowerStrength(power));      // 2.83E-3
    private final static Cell cesanoPowerCell = new Cell("Cesano-Power", ServicesTest.cesanoMaderno, StrengthFactory.getPowerStrength(power));  // 3.57E-4
    private final static Cell milanoPowerCell = new Cell("Milano-Power", ServicesTest.milano, StrengthFactory.getPowerStrength(power));         // 2.73E-6
    private final static Cell lodiPowerCell = new Cell("Lodi-Power", ServicesTest.lodi, StrengthFactory.getPowerStrength(power));               // 1.85E-18
    private final static Cell bergamoPowerCell = new Cell("Bergamo-Power", ServicesTest.bergamo, StrengthFactory.getPowerStrength(power));      // 5.75E-20

    @Test
    public void filterByStrengthReverseOrderRadiusCellsTest() {
        List<Cell> inputCells = new ArrayList<>() {{
            add(tradateRadiusCell);
            add(saronnoRadiusCell);
            add(cesanoRadiusCell);
            add(milanoRadiusCell);
            add(lodiRadiusCell);
            add(bergamoRadiusCell);
            add(bollateRadiusCell);
            add(areseRadiusCell);
        }};

        TreeMap<Cell, Double> activeCellsInGarbagnate = Services.filterByStrengthReverseOrder(inputCells, garbagnateMilanese, 1.E-2);
        Assert.assertEquals(3, activeCellsInGarbagnate.size());
        System.out.println(activeCellsInGarbagnate);
    }

    @Test
    public void filterByStrengthReverseOrderPowerCellsTest() {
        List<Cell> inputCells = new ArrayList<>() {{
            add(aresePowerCell);
            add(bollatePowerCell);
            add(tradatePowerCell);
            add(saronnoPowerCell);
            add(cesanoPowerCell);
            add(milanoPowerCell);
            add(lodiPowerCell);
            add(bergamoPowerCell);
        }};

        TreeMap<Cell, Double> activeCellsInGarbagnateLargerThan1em5 = Services.filterByStrengthReverseOrder(inputCells, garbagnateMilanese, 1.E-5);
        Assert.assertEquals(4, activeCellsInGarbagnateLargerThan1em5.size());
        System.out.println(activeCellsInGarbagnateLargerThan1em5);

        TreeMap<Cell, Double> activeCellsInGarbagnateLargerThan1em8 = Services.filterByStrengthReverseOrder(inputCells, garbagnateMilanese, 1.E-8);
        Assert.assertEquals(6, activeCellsInGarbagnateLargerThan1em8.size());
        System.out.println(activeCellsInGarbagnateLargerThan1em8);

    }

    @Test
    public void filterByStrengthReverseOrderMixedCellsTest() {
        List<Cell> inputCells = new ArrayList<>() {{
            add(areseRadiusCell);
            add(saronnoPowerCell);
            add(bergamoRadiusCell);
            add(new Cell("Bergamo-SuperRadius", bergamo, StrengthFactory.getRadiusStrength(300.)));
            add(lodiPowerCell);
            add(new Cell("Lodi-SuperPower", lodi, StrengthFactory.getPowerStrength(1.001)));
        }};

        TreeMap<Cell, Double> activeCellsInGarbagnate = Services.filterByStrengthReverseOrder(inputCells, garbagnateMilanese, 1.E-3);
        Assert.assertEquals(4, activeCellsInGarbagnate.size());
        System.out.println(activeCellsInGarbagnate);
    }

    @Test
    public void findMostCommonCells() {
        List<CellEvent> inputCellEvents = new ArrayList<>() {{
            add(new CellEvent(aresePowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2010,2,28,22,45))));
            add(new CellEvent(aresePowerCell, CellEventType.Disconnected, Timestamp.valueOf(LocalDateTime.of(2010,3,1,1,30))));
            add(new CellEvent(aresePowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2010,3,1,1,45))));
            add(new CellEvent(aresePowerCell, CellEventType.Disconnected, Timestamp.valueOf(LocalDateTime.of(2010,3,1,1,58))));
            add(new CellEvent(aresePowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2010,3,1,2,7))));
            add(new CellEvent(aresePowerCell, CellEventType.Disconnected, Timestamp.valueOf(LocalDateTime.of(2010,3,1,2,31))));
            add(new CellEvent(aresePowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2010,3,1,2,32))));

            add(new CellEvent(lodiPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2015, 12, 11, 13, 45))));
            add(new CellEvent(lodiPowerCell, CellEventType.Disconnected, Timestamp.valueOf(LocalDateTime.of(2015, 12, 11, 13, 58))));
            add(new CellEvent(lodiPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2015, 12, 11, 13, 59))));

            add(new CellEvent(bergamoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 13, 59))));
            add(new CellEvent(bergamoPowerCell, CellEventType.Disconnected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 14, 2))));
            add(new CellEvent(bergamoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 14, 4))));
            add(new CellEvent(bergamoPowerCell, CellEventType.Disconnected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 14, 11))));
            add(new CellEvent(bergamoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 14, 32))));
            add(new CellEvent(bergamoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 14, 43))));
            add(new CellEvent(bergamoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 14, 50))));
            add(new CellEvent(bergamoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 15, 12))));
            add(new CellEvent(bergamoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2004, 12, 11, 15, 16))));

            add(new CellEvent(milanoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 30))));
            add(new CellEvent(milanoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 32))));
            add(new CellEvent(milanoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 34))));
            add(new CellEvent(milanoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 36))));
            add(new CellEvent(milanoPowerCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 38))));
            add(new CellEvent(milanoRadiusCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 38))));
            add(new CellEvent(milanoRadiusCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 39))));
            add(new CellEvent(milanoRadiusCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 40))));
            add(new CellEvent(milanoRadiusCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 41))));
            add(new CellEvent(milanoRadiusCell, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2021, 3, 10, 20, 42))));

        }};

        LinkedHashMap<Cell, Integer> mostCommonCells = Services.findMostCommonCells(inputCellEvents);
        Assert.assertEquals(5, (int) mostCommonCells.get(milanoPowerCell));
        Assert.assertEquals(9, (int) mostCommonCells.get(bergamoPowerCell));
        Assert.assertTrue(mostCommonCells.containsKey(milanoRadiusCell));
        Assert.assertTrue(mostCommonCells.containsKey(lodiPowerCell));
        Assert.assertTrue(mostCommonCells.containsKey(aresePowerCell));
        Assert.assertEquals(5, mostCommonCells.size());
    }
}
