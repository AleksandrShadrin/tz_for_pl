package task2;

public class Circle {
    private double radius;
    private Coordinate coordinate;

    Circle(double radius, Coordinate coordinate) {
        this.radius = radius;
        this.coordinate = coordinate;
    }

    public Coordinate getCoord() {
        return coordinate;
    }

    public double getRadius() {
        return radius;
    }

    public coordinatePosition checkCoordinatePosition(Coordinate toCheck) {
        double equationRes = (coordinate.getX() - toCheck.getX())
                * (coordinate.getX() - toCheck.getX())
                + (coordinate.getY() - toCheck.getY())
                * (coordinate.getY() - toCheck.getY()) - radius * radius;

        if (equationRes < 0)
            return coordinatePosition.INSIDE_CIRCLE;
        else if (equationRes == 0)
            return coordinatePosition.ON_CIRCLE;
        else
            return coordinatePosition.OUT_CIRCLE;
    }
}
