package task1;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        if(n < 1
            || m < 1)
            return;

        System.out.print(1);

        int lastIdx = (m - 1) % n;

        while (lastIdx != 0) {
            System.out.print(lastIdx + 1);
            lastIdx = (lastIdx + (m - 1)) % n;
        }
    }
}