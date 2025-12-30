/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за создание сотрудников
 * - Координирует процесс: ввод → валидация → создание → сохранение
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций процесса создания
 * - Открыт для расширения через композицию (EmployeeCreator)
 *
 * LSP (Liskov Substitution) - ЧАСТИЧНО СОБЛЮДЕН:
 * - Зависит от EmployeeCreator, который может иметь подтипы
 * - Может работать с любым наследником EmployeeCreator
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет только один публичный метод
 * - Минимальный интерфейс для создания сотрудников
 *
 * DIP (Dependency Inversion) - ЧАСТИЧНО СОБЛЮДЕН:
 * - Scanner и EmployeeCreator переданы через конструктор (соблюдено)
 * - Прямая зависимость от Database (нарушено)
 * - Прямая зависимость от System.out (нарушено)
 * - Зависит от конкретных исключений (EmployeeCreationException)
 */

package core.customData.Service;

import core.customData.Menu.EmployeeCreationException;
import core.customData.Menu.EmployeeCreator;
import core.customData.Menu.EmployeeValidator;
import core.entities.Employee;
import core.com.zoo.Database;

import java.util.Scanner;

public class EmployeeService {
    private final Scanner scanner;
    private final EmployeeCreator employeeCreator;

    public EmployeeService(Scanner scanner) {
        this.scanner = scanner;
        this.employeeCreator = new EmployeeCreator(scanner);
    }

    public void createNewEmployee() {
        System.out.println("\n--- СОЗДАНИЕ НОВОГО СОТРУДНИКА ---");

        try {
            // Ввод ID
            System.out.print("Введите ID сотрудника: ");
            String id = scanner.nextLine().trim();

            // Ввод ФИО
            System.out.print("Введите ФИО сотрудника: ");
            String fullName = scanner.nextLine().trim();

            // Валидация данных
            EmployeeValidator.validateEmployeeData(id, fullName, Database.getEmployees());

            // Создание сотрудника через EmployeeCreator
            Employee employee = employeeCreator.createEmployee(id, fullName);

            // Добавление в базу
            Database.addEmployee(employee);

            System.out.println("✅ Сотрудник успешно создан!");
            System.out.println(employee.getInfo());

        } catch (NumberFormatException e) {
            System.out.println("❌ Ошибка: Неверный формат числа!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        } catch (EmployeeCreationException e) {
            System.out.println("❌ Ошибка при создании сотрудника: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Неожиданная ошибка: " + e.getMessage());
        }
    }
}