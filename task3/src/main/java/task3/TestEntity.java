package task3;

public class TestEntity {
    private int id;
    private String title;
    private String value;
    private TestEntity[] values;

    public TestEntity(int id, String title, String value, TestEntity[] values) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.values = values;
    }

    public TestEntity[] getValues() {
        return values;
    }

    public void setValues(TestEntity[] values) {
        this.values = values;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}