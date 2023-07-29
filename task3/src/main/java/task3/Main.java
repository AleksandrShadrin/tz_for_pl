package task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<TestEntity> tests = GetTests(args[0]);
        List<ValueEntity> values = GetValues(args[1]);

        flatten(tests).forEach(t -> {
            values.stream()
                    .filter(v -> v.getId() == t.getId())
                    .findFirst().ifPresent(v -> t.setValue(v.getValue()));
        });
        Map<String, List<TestEntity>> map = new TreeMap<String, List<TestEntity>>(){{put("tests", tests);}};
        System.out.println(new Gson().toJson(map));
    }

    private static List<TestEntity> flatten(List<TestEntity> test) {
        return test.stream().map(t -> {
           List<TestEntity> collection = new ArrayList<>();
           return flatten(t, collection);
        }).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static List<TestEntity> flatten(TestEntity test, List<TestEntity> result) {
        result.add(test);
        if(test.getValues() != null
            && test.getValues().length > 0)
        {
            for (TestEntity testEntity:
                 test.getValues()) {
                flatten(testEntity, result);
            }
        }

        return result;
    }

    private static List<TestEntity> GetTests(String testsPath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader testsReader = new JsonReader(new FileReader(testsPath));

        Type testsList = new TypeToken<Map<String, ArrayList<TestEntity>>>(){}.getType();
        Map<String, ArrayList<TestEntity>> tests = gson.fromJson(testsReader, testsList);

        return tests.get("tests");
    }

    private static ArrayList<ValueEntity> GetValues(String valuesPath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader valuesReader = new JsonReader(new FileReader(valuesPath));

        Type valuesList = new TypeToken<Map<String, ArrayList<ValueEntity>>>(){}.getType();
        Map<String, ArrayList<ValueEntity>> values = gson.fromJson(valuesReader, valuesList);

        return values.get("values");
    }
}
