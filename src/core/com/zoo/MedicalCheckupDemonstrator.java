/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - СОБЛЮДЕН:
 *    - Класс отвечает только за демонстрацию медицинских осмотров
 *    - Координирует: получение животных → проверка статуса → вывод истории
 *    - Чистая демонстрационная логика без смешивания ответственностей
 *
 * 2. OCP (Open/Closed) - СОБЛЮДЕН:
 *    - Закрыт для модификаций демонстрационной логики
 *    - Открыт для расширения через добавление новых статусов HealthStatus
 *    - Можно добавить новые типы отчетов без изменения существующего кода
 *
 * 3. LSP (Liskov Substitution) - СОБЛЮДЕН:
 *    - Работает с любыми подтипами Animal через HealthCheckable интерфейс
 *    - Все животные корректно обрабатываются в общем списке
 *    - Полиморфизм используется через проверку instanceof и приведение типа
 *
 * 4. ISP (Interface Segregation) - СОБЛЮДЕН:
 *    - Использует минимальный интерфейс HealthCheckable (только getMedicalHistory)
 *    - Не зависит от ненужных методов других интерфейсов
 *    - Интерфейс имеет одну четкую ответственность - медицинские данные
 *
 * 5. DIP (Dependency Inversion) - ЧАСТИЧНО НАРУШЕН:
 *    - Зависит от абстракций Animal и HealthCheckable (соблюдено)
 *    - НО: Прямая зависимость от Database.getAnimals() (нарушено)
 *    - Нет внедрения зависимости через конструктор или параметры
 */
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