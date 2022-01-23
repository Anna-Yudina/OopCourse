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
        return "[from=" + from +
                ", to=" + to + "]";
    }

    public Range getIntervalIntersectionTwoIntervals(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double from2 = range2.getFrom();
        double to1 = range1.getTo();
        double to2 = range2.getTo();

        if (to1 > from2 && to2 > from1) {
            if (from1 <= from2) {
                if (to1 >= to2) {
                    return new Range(from2, to2);
                } else {
                    return new Range(from2, to1);
                }
            } else {
                if (to1 < to2) {
                    return new Range(from1, to1);
                } else {
                    return new Range(from1, to2);
                }
            }
        }
        return null;
    }

    public Range[] getIntervalUnionTwoIntervals(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double from2 = range2.getFrom();
        double to1 = range1.getTo();
        double to2 = range2.getTo();

        Range[] ranges = new Range[2];

        if (to1 > from2 && to2 > from1) {
            if (from1 <= from2) {
                if (to1 >= to2) {
                    ranges[0] = new Range(from1, to1);
                } else {
                    ranges[0] = new Range(from1, to2);
                }
            } else {
                if (to1 < to2) {
                    ranges[0] = new Range(from2, to2);
                } else {
                    ranges[0] = new Range(from2, to1);
                }
            }
            return ranges;
        } else {
            if (to2 == from1) {
                ranges[0] = new Range(from2, to1);
                return ranges;
            } else if (to1 == from2) {
                ranges[0] = new Range(from1, to2);
                return ranges;
            } else {
                ranges[0] = range1;
                ranges[1] = range2;
                return ranges;
            }
        }
    }

    public Range[] getIntervalDifferenceTwoIntervals(Range range1, Range range2) {
        double from1 = range1.getFrom();
        double from2 = range2.getFrom();
        double to1 = range1.getTo();
        double to2 = range2.getTo();

        Range[] ranges = new Range[2];

        if (to1 > from2 && to2 > from1) {
            if (from1 == from2) {
                if (to1 <= to2) {
                    ranges[0] = null;
                } else {
                    ranges[0] = new Range(to2, to1);
                }
                return ranges;
            } else if (from1 > from2) {
                if (to2 >= to1) {
                    ranges[0] = null;
                } else {
                    ranges[0] = new Range(to2, to1);
                }
                return ranges;
            } else {
                ranges[0] = new Range(from1, from2);
                if (to1 > to2) {
                    ranges[1] = new Range(to2, to1);
                }
                return ranges;
            }
        } else {
            ranges[0] = range1;
            return ranges;
        }
    }
}


