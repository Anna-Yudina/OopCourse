package ru.academIts.yudina.range;

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

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getRange(){
        return (this.to - this.from);
    }

    public boolean isInside (double number){
        if ((this.from <= number) && (this.to >= number)){
            return true;
        } else {
            return false;
        }
    }
}
