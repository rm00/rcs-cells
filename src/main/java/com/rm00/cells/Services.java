package com.rm00.cells;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;

public class Services {
    private static Comparator<Cell> strengthComparator(Coordinates2D target) {
        return comparingDouble(c -> c.getStrengthAtCoordinates(target));
    }

    private static Predicate<Cell> strengthLargerThan(Coordinates2D target, double threshold) {
        return x -> x.getStrengthAtCoordinates(target) > threshold;
    }

    /**
     * Filters a list of cells according to their strength value in a specified target point in the space;
     * then orders the valid cells according to their strength value.
     *
     * @param input is a List of Cells
     * @param target is the Coordinates2D of a target point
     * @param threshold is a threshold
     * @return an ordered map, where keys are the Cells and values are their strength value in the target point.
     */
    public static TreeMap<Cell, Double> filterByStrengthReverseOrder(List<Cell> input, Coordinates2D target, double threshold) {
        Map<Cell, Double> survivors = input.stream()        // filter those whose strength is larger than the threshold
                .filter(strengthLargerThan(target, threshold))
                .collect(Collectors.toMap(x -> x, x -> x.getStrengthAtCoordinates(target)));

        TreeMap<Cell, Double> result = new TreeMap<>(strengthComparator(target).reversed());    // order them
        survivors.forEach(result::put);

        return result;
    }

    /**
     * Calculates the number of occurrences of single Cells in a List of CellEvents;
     * then orders the Cells according to their occurrence.
     *
     * @param input is a List of CellEvents
     * @return an ordered map, where keys are the Cells and values are their occurrences.
     */
    public static LinkedHashMap<Cell, Integer> findMostCommonCells(List<CellEvent> input) {
        Map<Cell, Integer> occurrences = new HashMap<>();
        for(CellEvent ce: input) {                          // calculate occurrences of each Cell (no matter what's the status or timestamp)
            Cell c = ce.getCell();
            occurrences.putIfAbsent(c, 0);
            occurrences.put(c, occurrences.get(c)+1);
        }

        List<Map.Entry<Cell, Integer> > list = new LinkedList<>(occurrences.entrySet());
        list.sort(Map.Entry.comparingByValue());            // order the entries for value and reverse the result
        Collections.reverse(list);

        LinkedHashMap<Cell, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Cell, Integer> cellOccurrence : list) {
            result.put(cellOccurrence.getKey(), cellOccurrence.getValue());
        }
        return result;

    }
}
