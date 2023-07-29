package task3;

public class ValueEntity {
    private int id;
    private String value;

    public ValueEntity(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}