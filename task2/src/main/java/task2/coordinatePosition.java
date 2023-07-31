package task2;

public enum coordinatePosition {
    INSIDE_CIRCLE(1),
    ON_CIRCLE(0),
    OUT_CIRCLE(2);

    private int code;

    coordinatePosition(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
