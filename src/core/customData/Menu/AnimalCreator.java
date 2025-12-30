/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за создание объектов Animal
 * - Единственная ответственность: интерактивное создание животных
 *
 * OCP (Open/Closed) - ЧАСТИЧНО НАРУШЕН:
 * - Нарушение: switch-case для типов животных (закрыт для новых типов)
 * - Открыт для расширения конфигурации существующих типов
 *
 * LSP (Liskov Substitution) - ЧАСТИЧНО СОБЛЮДЕН:
 * - Соблюдено: работа с Animal и его подтипами (Mammal, Bird)
 * - Нарушение: явная проверка instanceof для HealthCheckable
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет минимальный интерфейс (1 публичный метод)
 * - Скрывает сложность внутренней реализации
 *
 * DIP (Dependency Inversion) - ЧАСТИЧНО СОБЛЮДЕН:
 * - Scanner передан через конструктор (соблюдено)
 * - Прямая зависимость от конкретных классов животных (Mammal, Bird)
 * - Зависит от конкретных исключений (AnimalCreationException)
 */

package core.customData.Menu;

import core.entities.Animal;
import core.entities.Bird;
import core.entities.Mammal;
import core.enums.HealthStatus;
import core.interfaces.HealthCheckable;

import java.util.Scanner;

/**
 * Фабрика для создания животных
 * Отвечает только за создание объектов Animal
 */
public class AnimalCreator {
    private final Scanner scanner;

    public AnimalCreator(Scanner scanner) {
        this.scanner = scanner;
    }


    // НОВЫЙ метод - принимает готовые данные
    public Animal createAnimal(String id, String name, int age) throws AnimalCreationException {
        try {
            // Создаем DTO с переданными данными
            AnimalData basicData = new AnimalData(id, name, age);

            // Создаем животное по типу
            Animal animal = createAnimalByType(basicData);

            // Настраиваем дополнительные параметры
            configureAnimalProperties(animal);

            return animal;

        } catch (NumberFormatException e) {
            throw new AnimalCreationException("Неверный формат числа", e);
        } catch (Exception e) {
            throw new AnimalCreationException("Ошибка при создании животного: " + e.getMessage(), e);
        }
    }


    private Animal createAnimalByType(AnimalData data) {
        System.out.println("\nВыберите тип животного:");
        System.out.println("1. Млекопитающее");
        System.out.println("2. Птица");
        System.out.print("Ваш выбор: ");

        int typeChoice = Integer.parseInt(scanner.nextLine());

        switch (typeChoice) {
            case 1:
                return createMammal(data);
            case 2:
                return new Bird(data.id(), data.name(), data.age());
            default:
                throw new IllegalArgumentException("Неверный выбор типа!");
        }
    }

    private Mammal createMammal(AnimalData data) {
        Mammal mammal = new Mammal(data.id(), data.name(), data.age());
        System.out.print("Введите суточную норму еды (кг): ");
        double foodReq = Double.parseDouble(scanner.nextLine());
        mammal.setDailyFoodRequirement(foodReq);
        return mammal;
    }

    private void configureAnimalProperties(Animal animal) {
        configureHealthStatus(animal);
        configureMedicalHistory(animal);
    }

    private void configureHealthStatus(Animal animal) {
        System.out.println("\nВыберите статус здоровья:");
        System.out.println("1. ЗДОРОВ");
        System.out.println("2. БОЛЕН");
        System.out.println("3. На лечении");
        System.out.print("Ваш выбор: ");

        int healthChoice = Integer.parseInt(scanner.nextLine());

        switch (healthChoice) {
            case 1:
                animal.setHealthStatus(HealthStatus.HEALTHY);
                break;
            case 2:
                animal.setHealthStatus(HealthStatus.SICK);
                break;
            case 3:
                animal.setHealthStatus(HealthStatus.UNDER_TREATMENT);
                break;
            default:
                animal.setHealthStatus(HealthStatus.HEALTHY);
        }
    }

    private void configureMedicalHistory(Animal animal) {
        System.out.print("\nДобавить медицинскую историю? (да/нет): ");
        String addHistory = scanner.nextLine().trim().toLowerCase();

        if (!(addHistory.equals("да") || addHistory.equals("д"))) {
            return;
        }

        if (!(animal instanceof HealthCheckable)) {
            System.out.println("⚠️ Это животное не поддерживает медицинскую историю");
            return;
        }

        System.out.println("Введите медицинскую историю (для завершения введите пустую строку):");
        StringBuilder history = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).isEmpty()) {
            history.append(line).append("\n");
        }

        ((HealthCheckable) animal).setMedicalHistory(history.toString());
    }

    // DTO для передачи данных
    private record AnimalData(String id, String name, int age) {}
}