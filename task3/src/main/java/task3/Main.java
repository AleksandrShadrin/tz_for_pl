package task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<TestEntity> tests = GetTests(args[0]);
        Map<Integer, String> values = GetValues(args[1]);

        flatten(tests).forEach(t -> {
            if (values.containsKey(t.getId()))
                t.setValue(values.get(t.getId()));
        });

        String testsFilePath = Paths.get(getParentFolder(args[0]), "report.json").toString();
        String currentFilePath = Paths.get("report.json").toFile().getAbsolutePath();

        Map<String, List<TestEntity>> map = new TreeMap<String, List<TestEntity>>() {{
            put("tests", tests);
        }};
        String content = new Gson().toJson(map);

        if (testsFilePath.equals(currentFilePath))
            writeToFile(testsFilePath, content);
        else {
            writeToFile(testsFilePath, content);
            writeToFile(currentFilePath, content);
        }
    }

    private static void writeToFile(String filePath, String content) {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        Files.newOutputStream(Paths.get(filePath)), StandardCharsets.UTF_8))
        ) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getParentFolder(String path) {
        File file = new File(path);
        return file.getAbsoluteFile().getParent().toString();
    }

    private static List<TestEntity> flatten(List<TestEntity> test) {
        return test.stream().map(t -> {
            List<TestEntity> collection = new ArrayList<>();
            return flatten(t, collection);
        }).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static List<TestEntity> flatten(TestEntity test, List<TestEntity> result) {
        result.add(test);

        if (test.getValues() != null)
            for (TestEntity testEntity :
                    test.getValues())
                flatten(testEntity, result);

        return result;
    }

    private static List<TestEntity> GetTests(String testsPath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader testsReader = new JsonReader(new FileReader(testsPath));

        Type testsList = new TypeToken<Map<String, ArrayList<TestEntity>>>() {
        }.getType();
        Map<String, ArrayList<TestEntity>> tests = gson.fromJson(testsReader, testsList);

        return tests.get("tests");
    }

    private static Map<Integer, String> GetValues(String valuesPath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader valuesReader = new JsonReader(new FileReader(valuesPath));

        Type valuesList = new TypeToken<Map<String, ArrayList<ValueEntity>>>() {
        }.getType();
        Map<String, ArrayList<ValueEntity>> values = gson.fromJson(valuesReader, valuesList);

        Map<Integer, String> dict = new HashMap<>();
        values.get("values")
                .forEach(v -> dict.put(v.getId(), v.getValue()));

        return dict;
    }
}