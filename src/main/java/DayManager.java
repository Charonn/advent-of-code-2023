package main.java;

import java.util.ArrayList;
import java.util.List;

public class DayManager {
    private final List<Day> days;

    public DayManager() {
        this.days = new ArrayList<>();
    }

    public DayManager addDay(Day d) {
        this.days.add(d);
        return this;
    }

    public void run() {
        this.days.forEach(Day::start);
    }
}
