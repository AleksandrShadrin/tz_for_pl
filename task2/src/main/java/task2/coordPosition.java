package task2;

public enum coordPosition {
    INSIDE_CIRCLE(1),
    ON_CIRCLE(0),
    OUT_CIRCLE(2);

    private int code;

    coordPosition(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
