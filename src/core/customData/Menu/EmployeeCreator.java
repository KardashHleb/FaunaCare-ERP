/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за создание объектов Employee
 * - Единственная ответственность: интерактивное создание сотрудников
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций процесса создания
 * - Открыт для расширения через наследование или стратегию
 *
 * LSP (Liskov Substitution) - АРХИТЕКТУРНО ПОДДЕРЖИВАЕМ:
 * - Позволяет подстановку наследников
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет минимальный интерфейс (1 публичный метод)
 * - Не заставляет клиентов зависеть от неиспользуемых методов
 *
 * DIP (Dependency Inversion) - ЧАСТИЧНО НАРУШЕН:
 * - Scanner передан через конструктор (соблюдено)
 * - Прямая зависимость от System.out (нарушено)
 * - Зависит от конкретных исключений (EmployeeCreationException)
 */
package core.customData.Menu;

import core.entities.Employee;
import core.enums.EmployeeRole;

import java.util.Scanner;

public class EmployeeCreator {
    private final Scanner scanner;

    public EmployeeCreator(Scanner scanner) {
        this.scanner = scanner;
    }

    public Employee createEmployee(String id, String fullName) throws EmployeeCreationException {
        try {
            // Выбор должности
            System.out.println("\nВыберите должность:");
            System.out.println("1. Смотритель (KEEPER)");
            System.out.println("2. Ветеринар (VETERINARIAN)");
            System.out.println("3. Дрессировщик (TRAINER)");
            System.out.println("4. Администратор (ADMINISTRATOR)");
            System.out.print("Ваш выбор: ");

            int roleChoice = Integer.parseInt(scanner.nextLine());

            EmployeeRole role;
            switch (roleChoice) {
                case 1:
                    role = EmployeeRole.KEEPER;
                    break;
                case 2:
                    role = EmployeeRole.VETERINARIAN;
                    break;
                case 3:
                    role = EmployeeRole.TRAINER;
                    break;
                case 4:
                    role = EmployeeRole.CURATOR;
                    break;
                default:
                    role = EmployeeRole.KEEPER;
            }

            // Создание сотрудника
            return new Employee(id, fullName, role);

        } catch (NumberFormatException e) {
            throw new EmployeeCreationException("Неверный формат числа", e);
        } catch (Exception e) {
            throw new EmployeeCreationException("Ошибка при создании сотрудника: " + e.getMessage(), e);
        }
    }
}