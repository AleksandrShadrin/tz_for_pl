package task1;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        Integer[] array = Stream.iterate(1, x -> x + 1)
            .limit(n)
            .toArray(Integer[]::new);

        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);

        int lastIdx = (m - 1) % n;

        while (lastIdx != 0) {
            result.add(lastIdx);
            lastIdx = (lastIdx + (m - 1)) % n;
        }

        result.stream()
                .map(i -> array[i])
                .forEach(System.out::println);
    }
}