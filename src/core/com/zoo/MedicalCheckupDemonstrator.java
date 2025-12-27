package core.com.zoo;

import core.entities.Animal;
import core.enums.HealthStatus;
import core.interfaces.HealthCheckable;

import java.util.List;

public class MedicalCheckupDemonstrator {

    public void demonstrateMedicalCheckups() {
        List<Animal> animals = Database.getAnimals(); // Получаем животных из Database

        if (animals == null || animals.isEmpty()) {
            System.out.println("Список животных пуст!");
            return;
        }

        System.out.println("\n=== СТАТУСЫ ЗДОРОВЬЯ ЖИВОТНЫХ ===");

        boolean foundIssues = false;
        for (Animal animal : animals) {
            HealthStatus status = animal.getHealthStatus();

            switch (status) {
                case CRITICAL:
                    System.out.println("   ⚠️ СРОЧНО: " + animal.getName() +
                            " в критическом состоянии! Требуется немедленная помощь!");
                    foundIssues = true;
                    break;
                case SICK:
                    System.out.println("   🚨 " + animal.getName() +
                            " болен, требуется лечение");
                    foundIssues = true;
                    break;
                case RECOVERING:
                    System.out.println("   💊 " + animal.getName() +
                            " выздоравливает, нужен особый уход");
                    foundIssues = true;
                    break;
                case HEALTHY:
                    System.out.println("   ✅ " + animal.getName() + " здоров");
                    break;
            }
        }

        if (!foundIssues) {
            System.out.println("   Все животные здоровы! 🎉");
        }

        System.out.println("\n=== МЕДИЦИНСКИЕ ИСТОРИИ ===");
        boolean hasHistory = false;

        for (Animal animal : animals) {
            if (animal instanceof HealthCheckable) {
                HealthCheckable healthCheckable = (HealthCheckable) animal;
                String history = healthCheckable.getMedicalHistory();

                // Проверяем, что история не пустая
                if (history != null && !history.trim().isEmpty()) {
                    System.out.println("\n   📋 " + animal.getName() + ":");
                    System.out.println("     " + history.replace("\n", "\n     "));
                    hasHistory = true;
                }
            }
        }

        if (!hasHistory) {
            System.out.println("   У животных нет медицинских записей.");
        }
    }
}