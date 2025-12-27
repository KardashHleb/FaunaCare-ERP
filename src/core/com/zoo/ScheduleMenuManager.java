package core.com.zoo;

import core.entities.Animal;
import core.interfaces.ScheduleService;
import java.util.List;
import java.util.Scanner;

public class ScheduleMenuManager {
    private final Scanner scanner;
    private final ScheduleService<Animal> scheduleService;

    public ScheduleMenuManager(Scanner scanner, ScheduleService<Animal> scheduleService) {
        this.scanner = scanner;
        this.scheduleService = scheduleService;
    }

    public void demonstrateScheduleManagement() {
        System.out.println("\n=== УПРАВЛЕНИЕ РАСПИСАНИЕМ ЖИВОТНЫХ ===");

        List<Animal> animals = Database.getAnimals();

        if (animals.isEmpty()) {
            System.out.println("Нет животных в базе данных!");
            return;
        }

        System.out.println("\nВыберите животное для работы с расписанием:");
        for (int i = 0; i < Math.min(animals.size(), 5); i++) {
            System.out.printf("%d. %s (%s)%n",
                    i + 1,
                    animals.get(i).getName(),
                    animals.get(i).getType().getRussianName());
        }

        System.out.print("Ваш выбор: ");
        try {
            int animalChoice = Integer.parseInt(scanner.nextLine()) - 1;
            if (animalChoice >= 0 && animalChoice < animals.size()) {
                Animal selectedAnimal = animals.get(animalChoice);
                showScheduleMenu(selectedAnimal);
            } else {
                System.out.println("Неверный выбор!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Введите число!");
        }
    }

    private void showScheduleMenu(Animal animal) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== РАСПИСАНИЕ ДЛЯ " + animal.getName().toUpperCase() + " ===");
            System.out.println("1. Показать расписание на сегодня");
            System.out.println("2. Запланировать мероприятие");
            System.out.println("3. Показать следующее мероприятие");
            System.out.println("4. Назад");
            System.out.print("Выберите действие: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        showDailySchedule(animal);
                        break;
                    case 2:
                        scheduleNewActivity(animal);
                        break;
                    case 3:
                        showNextActivity(animal);
                        break;
                    case 4:
                        back = true;
                        break;
                    default:
                        System.out.println("Неверный выбор!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
            }
        }
    }

    private void showDailySchedule(Animal animal) {
        String today = java.time.LocalDate.now().toString();
        List<String> schedule = scheduleService.getDailySchedule(animal, today);

        System.out.println("\nРасписание на сегодня (" + today + "):");
        if (schedule.isEmpty()) {
            System.out.println("Нет запланированных мероприятий");
        } else {
            for (String activity : schedule) {
                System.out.println("  • " + activity);
            }
        }
    }

    private void scheduleNewActivity(Animal animal) {
        System.out.println("\n=== ПЛАНИРОВАНИЕ МЕРОПРИЯТИЯ ===");

        System.out.print("Тип мероприятия (кормление, осмотр, тренировка): ");
        String type = scanner.nextLine();

        System.out.print("Время (HH:mm): ");
        String time = scanner.nextLine();

        System.out.print("Дата (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Место проведения: ");
        String location = scanner.nextLine();

        try {
            String activityId = scheduleService.scheduleActivity(animal, type, time, date, location);
            System.out.println("Мероприятие успешно запланировано! ID: " + activityId);
        } catch (Exception e) {
            System.out.println("Ошибка при планировании: " + e.getMessage());
        }
    }

    private void showNextActivity(Animal animal) {
        String nextActivity = scheduleService.getNextActivity(animal);
        System.out.println("\nСледующее мероприятие: " + nextActivity);
    }
}