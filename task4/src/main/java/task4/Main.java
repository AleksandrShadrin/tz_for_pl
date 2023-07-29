package task4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> nums = readLinesFromFile(args[0])
                .map(Integer::parseInt)
                .sorted(Integer::compare)
                .collect(Collectors.toList());

        int mid = nums.size() / 2;

        List<Integer> toCheck = nums.size() % 2 == 0
                ? Arrays.asList(mid - 1, mid)
                : Collections.singletonList(mid);

        toCheck.stream()
                .map(nums::get)
                .map(x -> nums.stream()
                        .map(n -> n - x)
                        .map(Math::abs)
                        .mapToInt(Integer::intValue)
                        .sum())
                .min(Integer::compare).ifPresent(System.out::println);
    }

    private static Stream<String> readLinesFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        return reader
                .lines();
    }
}
