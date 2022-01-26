package ru.academits.yudina.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    @Override
    public String toString() {
        return "(" + from +
                ", " + to + ")";
    }

    public Range getIntersection(Range range) {
        if (to > range.from && range.to > from){
            return new Range(Math.max(from, range.from),Math.min(to, range.to));
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (to >= range.from && range.to >= from) {
            return new Range[]{new Range(Math.min(from, range.from),Math.max(to, range.to))};
        }
        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (to > range.from && range.to > from) {
            if (from >= range.from) {
                if (range.to >= to) {
                    return new Range[0];
                }
                return new Range[]{new Range(range.to, to)};
            } else {
                if (to <= range.to) {
                    return new Range[]{new Range(from, range.from)};
                }
                return new Range[]{new Range(from, range.from), new Range(range.to, to)};
            }
        } else {
            return new Range[]{new Range(range.from, to)};
        }
    }
}


