package core.com.zoo;

import core.entities.Animal;
import core.interfaces.ScheduleService;
import java.util.*;

public class AnimalScheduleService implements ScheduleService<Animal> {
    private Map<Animal, List<ScheduleEntry>> schedules = new HashMap<>();

    private static class ScheduleEntry {
        String activityType;
        String time;
        String date;
        String location;

        ScheduleEntry(String activityType, String time, String date, String location) {
            this.activityType = activityType;
            this.time = time;
            this.date = date;
            this.location = location;
        }
    }

    @Override
    public String scheduleActivity(Animal animal, String activityType, String time, String date, String location) {
        ScheduleEntry entry = new ScheduleEntry(activityType, time, date, location);
        List<ScheduleEntry> animalSchedule = schedules.getOrDefault(animal, new ArrayList<>());
        animalSchedule.add(entry);
        schedules.put(animal, animalSchedule);
        return "activity_" + System.currentTimeMillis();
    }

    @Override
    public List<String> getDailySchedule(Animal animal, String date) {
        List<String> dailyActivities = new ArrayList<>();
        List<ScheduleEntry> animalSchedule = schedules.get(animal);

        if (animalSchedule != null) {
            for (ScheduleEntry entry : animalSchedule) {
                if (entry.date.equals(date)) {
                    dailyActivities.add(entry.time + " - " + entry.activityType + " (" + entry.location + ")");
                }
            }
        }

        dailyActivities.sort(String::compareTo);
        return dailyActivities;
    }

    @Override
    public List<String> getWeeklySchedule(Animal animal, String startDate) {
        return new ArrayList<>();
    }

    @Override
    public String getNextActivity(Animal animal) {
        List<ScheduleEntry> animalSchedule = schedules.get(animal);
        if (animalSchedule == null || animalSchedule.isEmpty()) {
            return "Нет запланированных мероприятий";
        }

        ScheduleEntry next = animalSchedule.get(0);
        return next.activityType + " в " + next.time + " (" + next.date + ")";
    }

    @Override
    public String addRecurringActivity(Animal animal, String activityType, String time, String frequency, String days) {
        return "recurring_" + System.currentTimeMillis();
    }
}