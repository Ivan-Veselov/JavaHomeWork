package ru.spbau.bachelor2015.veselov.hw08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream()
                    .flatMap(s -> {
                        try {
                            return Files.lines(Paths.get(s));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(s -> s.contains(sequence))
                    .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random generator = new Random();
        return Stream.generate(() ->
                                {
                                    if (Math.hypot(generator.nextDouble(), generator.nextDouble()) <= 1.0) return 1;
                                    else return 0;
                                })
                     .limit(1000000)
                     .collect(Collectors.averagingInt(a -> a));
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet()
                           .stream()
                           .collect(Collectors.maxBy(
                                   Comparator.comparingInt(e -> e.getValue()
                                             .stream()
                                             .collect(Collectors.summingInt(String::length)))))
                           .get()
                           .getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().reduce(new HashMap<>(),
                                      (acc, map) -> {
                                                map.entrySet()
                                                   .forEach(e -> acc.merge(e.getKey(),
                                                                           e.getValue(),
                                                                           (a, b) -> a + b));
                                                return acc;
                                      });
    }
}
