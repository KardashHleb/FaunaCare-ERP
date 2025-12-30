/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за удаление сотрудников
 * - Содержит всю логику процесса удаления сотрудников
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций логики удаления
 * - Открыт для расширения через новые стратегии удаления сотрудников
 *
 * LSP (Liskov Substitution) - НЕ ПРИМЕНЯЕТСЯ:
 * - Нет иерархии наследования
 * - Нет переопределения методов
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет только один публичный метод
 * - Минимальный интерфейс для работы с удалением сотрудников
 *
 * DIP (Dependency Inversion) - ЧАСТИЧНО СОБЛЮДЕН:
 * - Scanner передан через конструктор (соблюдено)
 * - Прямая зависимость от Database (нарушено)
 * - Прямая зависимость от System.out (нарушено)
 */


package core.customData.Service;

import core.com.zoo.Database;
import core.entities.Employee;

import java.util.Scanner;

public class EmployeeRemovalService {
    private final Scanner scanner;

    public EmployeeRemovalService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void deleteEmployee() {
        System.out.println("\n--- УДАЛЕНИЕ СОТРУДНИКА ---");

        if (Database.getEmployees().isEmpty()) {
            System.out.println("Нет сотрудников для удаления.");
            return;
        }

        System.out.println("Список сотрудников:");
        for (int i = 0; i < Database.getEmployees().size(); i++) {
            Employee emp = Database.getEmployees().get(i);
            System.out.printf("%d. %s (ID: %s)\n", i + 1, emp.getName(), emp.getId());
        }

        try {
            System.out.print("\nВведите номер сотрудника для удаления (или 0 для отмены): ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) return;

            if (choice > 0 && choice <= Database.getEmployees().size()) {
                Employee emp = Database.getEmployees().get(choice - 1);
                System.out.print("Вы уверены, что хотите удалить " + emp.getName() + "? (да/нет): ");
                String confirm = scanner.nextLine().trim().toLowerCase();

                if (confirm.equals("да") || confirm.equals("д")) {
                    Database.removeEmployeeById(emp.getId());
                    System.out.println("✅ Сотрудник удален!");
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
