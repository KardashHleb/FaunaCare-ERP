/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за создание животных
 * - Координирует процесс: ввод → валидация → создание → сохранение
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций процесса создания
 * - Открыт для расширения через композицию (AnimalCreator)
 *
 * LSP (Liskov Substitution) - ЧАСТИЧНО СОБЛЮДЕН:
 * - Зависит от AnimalCreator, который может иметь подтипы
 * - Может работать с любым наследником AnimalCreator
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет только один публичный метод
 * - Минимальный интерфейс для создания животных
 *
 * DIP (Dependency Inversion) - ЧАСТИЧНО НАРУШЕН:
 * - Scanner и AnimalCreator переданы через конструктор (соблюдено)
 * - Прямая зависимость от Database (нарушено)
 * - Прямая зависимость от System.out (нарушено)
 * - Зависит от конкретных исключений (AnimalCreationException)
 */

package core.customData.Service;

import core.customData.Menu.AnimalCreationException;
import core.customData.Menu.AnimalCreator;
import core.customData.Menu.AnimalValidator;
import core.entities.Animal;
import core.com.zoo.Database;

import java.util.Scanner;

public class AnimalService {
    private final Scanner scanner;
    private final AnimalCreator animalCreator;

    public AnimalService(Scanner scanner) {
        this.scanner = scanner;
        this.animalCreator = new AnimalCreator(scanner);
    }

    public void createNewAnimal() {
        System.out.println("\n--- СОЗДАНИЕ НОВОГО ЖИВОТНОГО ---");

        try {
            // 1. Вводим все базовые данные
            System.out.print("Введите ID животного: ");
            String id = scanner.nextLine().trim();

            System.out.print("Введите имя животного: ");
            String name = scanner.nextLine().trim();

            System.out.print("Введите возраст животного: ");
            int age = Integer.parseInt(scanner.nextLine());

            // 2. Валидируем ВСЕ данные сразу
            AnimalValidator.validateAnimalData(id, name, age, Database.getAnimals());

            // 3. Создаем животное через AnimalCreator, передавая ВСЕ данные
            Animal animal = animalCreator.createAnimal(id, name, age);

            // 4. Добавляем в базу
            Database.addAnimal(animal);

            System.out.println("✅ Животное успешно создано!");
            System.out.println(animal.getInfo());

        } catch (NumberFormatException e) {
            System.out.println("❌ Ошибка: Неверный формат числа!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Ошибка валидации: " + e.getMessage());
        } catch (AnimalCreationException e) {
            System.out.println("❌ Ошибка при создании животного: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Неожиданная ошибка: " + e.getMessage());
        }
    }
}