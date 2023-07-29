package task2;

public class Circle {
    private double radius;
    private Coord coord;

    Circle(double radius, Coord coord) {
        this.radius = radius;
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    public double getRadius() {
        return radius;
    }

    public coordPosition checkCoordPosition(Coord toCheck) {
        double equationRes = (coord.getX() - toCheck.getX())
                * (coord.getX() - toCheck.getX())
                + (coord.getY() - toCheck.getY())
                * (coord.getY() - toCheck.getY()) - radius * radius;

        if (equationRes < 0)
            return coordPosition.INSIDE_CIRCLE;
        else if (equationRes == 0)
            return coordPosition.ON_CIRCLE;
        else
            return coordPosition.OUT_CIRCLE;
    }
}
