/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за удаление животных
 * - Содержит всю логику процесса удаления
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций логики удаления
 * - Открыт для расширения через новые стратегии удаления
 *
 * LSP (Liskov Substitution) - НЕ ПРИМЕНЯЕТСЯ:
 * - Нет иерархии наследования
 * - Нет переопределения методов
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет только один публичный метод
 * - Минимальный интерфейс для работы с удалением
 */

package core.customData.Service;

import core.com.zoo.Database;
import core.entities.Animal;

import java.util.Scanner;

public class AnimalRemovalService {
    private final Scanner scanner;

    public AnimalRemovalService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void deleteAnimal() {
        System.out.println("\n--- УДАЛЕНИЕ ЖИВОТНОГО ---");

        if (Database.getAnimals().isEmpty()) {
            System.out.println("Нет животных для удаления.");
            return;
        }

        System.out.println("Список животных:");
        for (int i = 0; i < Database.getAnimals().size(); i++) {
            Animal animal = Database.getAnimals().get(i);
            System.out.printf("%d. %s (ID: %s)\n", i + 1, animal.getName(), animal.getId());
        }

        try {
            System.out.print("\nВведите номер животного для удаления (или 0 для отмены): ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) return;

            if (choice > 0 && choice <= Database.getAnimals().size()) {
                Animal animal = Database.getAnimals().get(choice - 1);
                System.out.print("Вы уверены, что хотите удалить " + animal.getName() + "? (да/нет): ");
                String confirm = scanner.nextLine().trim().toLowerCase();

                if (confirm.equals("да") || confirm.equals("д")) {
                    Database.removeAnimalById(animal.getId());
                    System.out.println("✅ Животное удалено!");
                } else {
                    System.out.println("Удаление отменено.");
                }
            } else {
                System.out.println("Неверный номер!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Ошибка: Неверный формат числа!");
        }
    }
}
