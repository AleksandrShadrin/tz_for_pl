package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String circleFile = args[0];
        String coordinatesFile = args[1];
        List<String> circleFileLines = readLinesFromFile(circleFile);
        Circle circle = makeCircleFromLines(circleFileLines);
        List<Coordinate> coordinates = readLinesFromFile(coordinatesFile).stream()
                .map(Main::makeCoordFromLine)
                .collect(Collectors.toList());

        for (Coordinate coordinate :
                coordinates) {
            System.out.println(circle.checkCoordinatePosition(coordinate).getCode());
        }
    }

    private static Circle makeCircleFromLines(List<String> lines) {
        double radius = Double.parseDouble(lines.get(1).trim());
        Coordinate coordinate = makeCoordFromLine(lines.get(0));

        return new Circle(radius, coordinate);
    }

    private static Coordinate makeCoordFromLine(String line) {
        List<Double> args = Arrays
                .stream(line.split(" "))
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        return new Coordinate(args.get(0), args.get(1));
    }

    private static List<String> readLinesFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        return reader
                .lines()
                .collect(Collectors.toList());
    }
}
