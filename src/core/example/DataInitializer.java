/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - ✅ СОБЛЮДЕН
 *    - Основная ответственность: координация процесса инициализации данных ✅
 *    - UI-логика вывода делегирована: AnimalPrinter, EmployeeInitializer ✅
 *    - Создание данных делегировано: AnimalInitializer, EmployeeInitializer ✅
 *    - Очистка данных: единственная ответственность в рамках координации ⚠️ (приемлемо)
 *    - Проверка пользовательских данных: логика принятия решения ✅
 *
 * 2. OCP (Open/Closed) - ✅ СОБЛЮДЕН
 *    - Закрыт для модификаций: не нужно менять при добавлении новых животных/сотрудников
 *    - Открыт для расширения:
 *      - Можно добавить новые инициализаторы через композицию
 *      - Можно расширить вывод через внедрение зависимостей
 *      - Новые типы данных добавляются в отдельных классах
 *
 * 3. LSP (Liskov Substitution) - ✅ СОБЛЮДЕН
 *    - Работает с абстракциями List<Animal>, List<Employee>
 *    - Использует полиморфизм через вызовы методов инициализаторов
 *    - Любые наследники AnimalInitializer/EmployeeInitializer могут быть подставлены
 *
 * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *    - Класс имеет минимальный публичный API (1 метод)
 *    - Зависит только от необходимых интерфейсов/классов
 *    - Нет "толстых" интерфейсов или избыточных зависимостей
 *
 * 5. DIP (Dependency Inversion) - ⚠️ ЧАСТИЧНО СОБЛЮДЕН
 *    - ✅ Зависит от абстракций AnimalInitializer, EmployeeInitializer
 *    - ✅ Внедрение зависимостей через конструктор
 *    - ⚠️ Прямая зависимость от Database (статический импорт)
 *    - ⚠️ Прямая зависимость от System.out (вывод UI информации) для учебного проекта считаю приемлемым
 *    - ⚠️ Создание зависимостей внутри класса (new AnimalInitializer())
 */
package core.example;

import core.com.zoo.Database;
import core.entities.Animal;

import core.entities.Employee;



import java.util.List;

import static core.com.zoo.Database.getAnimals;
import static core.com.zoo.Database.getEmployees;

public class DataInitializer {


    // Экземпляр инициализатора сотрудников
    private final EmployeeInitializer employeeInitializer;
    private final AnimalInitializer animalInitializer;

    public DataInitializer() {
        this.employeeInitializer = new EmployeeInitializer();
        this.animalInitializer = new AnimalInitializer();
    }



    public void initializeData(boolean showDetails) {
        if (Database.isUsingCustomData()) {
            if (showDetails) {
                System.out.println("\n📋 ИСПОЛЬЗУЮТСЯ ПОЛЬЗОВАТЕЛЬСКИЕ ДАННЫЕ");
                System.out.println("Животных: " + Database.getAnimals().size());
                System.out.println("Сотрудников: " + Database.getEmployees().size());
            }
            return; // Не загружаем стандартные данные
        }



        if (showDetails) {
            System.out.println("\n📋 ИНИЦИАЛИЗАЦИЯ ДАННЫХ:");
            System.out.println("──────────────────────────────────────────────────────");
        }

        // Очищаем статические списки из Datebase
        List<Animal> animals = getAnimals();
        List<Employee> employees = getEmployees();
        animals.clear();
        employees.clear();

        animalInitializer.initializeAnimals(animals, showDetails);
        // Инициализация сотрудников через отдельный класс
        employeeInitializer.initializeEmployees(employees, showDetails);
        if (showDetails) {
            System.out.println("──────────────────────────────────────────────────────");
            System.out.println("✅ Инициализация завершена: " + animals.size() + " животных, " +
                    employees.size() + " сотрудников");

    }
}
}