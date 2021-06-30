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

    public static TreeMap<Cell, Double> filterByStrengthReverseOrder(List<Cell> input, Coordinates2D target, double threshold) {
        Map<Cell, Double> survivors = input.stream()        // filter those whose strength is larger than the threshold
                .filter(strengthLargerThan(target, threshold))
                .collect(Collectors.toMap(x -> x, x -> x.getStrengthAtCoordinates(target)));

        TreeMap<Cell, Double> result = new TreeMap<>(strengthComparator(target).reversed());    // order them
        survivors.forEach(result::put);

        return result;
    }

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
        for (Map.Entry<Cell, Integer> cellOccurrency : list) {
            result.put(cellOccurrency.getKey(), cellOccurrency.getValue());
        }
        return result;

    }
}
