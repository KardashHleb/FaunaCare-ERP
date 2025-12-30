/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - ✅ СОБЛЮДЕН
 *    - Класс отвечает только за инициализацию сотрудников
 *    - Не смешивает ответственности (валидация, логирование, сохранение и т.д.)
 *
 * 2. OCP (Open/Closed) - ✅ СОБЛЮДЕН
 *    - Закрыт для модификаций: DEFAULT_EMPLOYEES - final массив
 *    - Открыт для расширения через:
 *      а) Наследование и переопределение DEFAULT_EMPLOYEES
 *      б) Передачу кастомной employeeFactory в конструктор
 *      в) Создание наследников с другой логикой инициализации
 *
 * 3. LSP (Liskov Substitution) - ✅ СОБЛЮДЕН
 *    - Метод принимает List<Employee> - абстракцию
 *    - Можно передать любую реализацию List
 *    - Наследники могут расширять функциональность без нарушения контракта
 *
 * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *    - Класс имеет минимальный публичный API (только 2 конструктора и 1 метод)
 *    - Не зависит от "толстых" интерфейсов
 *    - Function<EmployeeCreationData, Employee> - минимальный необходимый контракт
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Зависит от абстракции Function<EmployeeCreationData, Employee>
 *    - Не зависит от конкретной реализации создания Employee
 *    - Высокоуровневый модуль (EmployeeInitializer) не зависит от низкоуровневого (Employee)
 *    - Оба зависят от абстракции (Function)
 */

package core.example;

import core.entities.Employee;
import core.enums.EmployeeRole;
import java.util.List;
import java.util.function.Function;

public class EmployeeInitializer {

    // Функция для создания сотрудника - абстракция вместо прямой зависимости
    private final Function<EmployeeCreationData, Employee> employeeFactory;

    // Массив конфигураций сотрудников
    private static final EmployeeCreationData[] DEFAULT_EMPLOYEES = {
            new EmployeeCreationData("EMP001", "Иванов Иван Иванович", EmployeeRole.KEEPER, "👨‍💼"),
            new EmployeeCreationData("EMP002", "Петрова Мария Сергеевна", EmployeeRole.VETERINARIAN, "👩‍⚕️"),
            new EmployeeCreationData("EMP003", "Сидоров Алексей Петрович", EmployeeRole.TRAINER, "🎪")
    };

    // Конструктор по умолчанию с простой фабрикой
    public EmployeeInitializer() {
        this.employeeFactory = data -> new Employee(data.id, data.name, data.role);
    }

    // Конструктор с возможностью передать свою фабрику (для тестов)
    public EmployeeInitializer(Function<EmployeeCreationData, Employee> employeeFactory) {
        this.employeeFactory = employeeFactory;
    }

    public void initializeEmployees(List<Employee> employees, boolean showDetails) {
        if (showDetails) {
            System.out.println("\n2. Создание сотрудников:");
        }

        // Создание сотрудников из конфигурации через фабрику
        for (EmployeeCreationData data : DEFAULT_EMPLOYEES) {
            Employee employee = employeeFactory.apply(data);
            employees.add(employee);

            if (showDetails) {
                System.out.println("   " + data.emoji + " " + employee.getInfo());
            }
        }
    }

    // Внутренний класс для хранения данных создания
    private static class EmployeeCreationData {
        final String id;
        final String name;
        final EmployeeRole role;
        final String emoji;

        EmployeeCreationData(String id, String name, EmployeeRole role, String emoji) {
            this.id = id;
            this.name = name;
            this.role = role;
            this.emoji = emoji;
        }
    }

}