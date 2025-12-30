/**
 * КООРДИНАТОР МЕНЮ УПРАВЛЕНИЯ ПОЛЬЗОВАТЕЛЬСКИМИ ДАННЫМИ
 *
 * ПРИНЦИПЫ SOLID:
 *
 * ✅ SRP (Single Responsibility) - ЧЕТКО СОБЛЮДЕН:
 *    - Единственная ответственность: меню и маршрутизация
 *    - Бизнес-логика делегирована специализированным сервисам
 *
 * ✅ OCP (Open/Closed) - ПОЛНОСТЬЮ СОБЛЮДЕН:
 *    - Закрыт для модификаций существующего кода
 *    - Открыт для расширений через добавление новых сервисов
 *
 * 🔄 LSP (Liskov Substitution) - АРХИТЕКТУРНО ПОДДЕРЖИВАЕМ:
 *    - Текущая реализация не требует иерархии наследования
 *    - Структура допускает легкое введение интерфейсов при необходимости
 *
 * 🔄 ISP (Interface Segregation) - ПРАГМАТИЧНЫЙ БАЛАНС:
 *    - На текущем уровне абстракции прямой зависимости от конкретных реализаций
 *      является осознанным упрощением
 *    - При росте сложности системы может быть легко трансформирован
 *      через введение интерфейсов IService
 *    - "Не создавай абстракций заранее, но будь готов к их введению"
 *
 * ✅ DIP (Dependency Inversion) - ЧАСТИЧНО СОБЛЮДЕН:
 *    - Зависимости явно передаются через конструктор
 *    - Для полного соблюдения достаточно добавить интерфейсы
 */

package core.customData.Menu;

import core.com.zoo.Database;

import core.customData.Service.AnimalService;
import core.customData.Service.EmployeeService;
import core.customData.Service.AnimalDisplayService;
import core.customData.Service.EmployeeDisplayService;
import core.customData.Service.AnimalRemovalService;
import core.customData.Service.EmployeeRemovalService;
import core.customData.Service.DataCleanupService;
import core.customData.Service.DataModeService;

import java.util.Scanner;

public class CustomDataManager {
    private Scanner scanner;
    private final AnimalService animalService;
    private final EmployeeService employeeService;
    private final AnimalDisplayService animalDisplayService;
    private final EmployeeDisplayService employeeDisplayService;
    private final AnimalRemovalService animalRemovalService;
    private final EmployeeRemovalService employeeRemovalService;
    private final DataCleanupService dataCleanupService;
    private final DataModeService dataModeService;

    public CustomDataManager(Scanner scanner) {
        this.scanner = scanner;
        this.animalService = new AnimalService(scanner);
        this.employeeService = new EmployeeService(scanner);
        this.animalDisplayService = new AnimalDisplayService();
        this.employeeDisplayService = new EmployeeDisplayService();
        this.animalRemovalService = new AnimalRemovalService(scanner);
        this.employeeRemovalService = new EmployeeRemovalService(scanner);
        this.dataCleanupService = new DataCleanupService(scanner);
        this.dataModeService = new DataModeService(scanner);
    }

    /**
     * Меню управления пользовательскими данными
     */
    public void showCustomDataMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("УПРАВЛЕНИЕ ПОЛЬЗОВАТЕЛЬСКИМИ ДАННЫМИ");
            System.out.println("=".repeat(60));
            System.out.println("Текущий режим: " +
                    (Database.isUsingCustomData() ? "Пользовательские данные" : "Стандартные данные"));
            System.out.println("Количество животных: " + Database.getAnimals().size());
            System.out.println("Количество сотрудников: " + Database.getEmployees().size());
            System.out.println("=".repeat(60));
            System.out.println("1. Создать новое животное");
            System.out.println("2. Создать нового сотрудника");
            System.out.println("3. Просмотреть всех животных");
            System.out.println("4. Просмотреть всех сотрудников");
            System.out.println("5. Удалить животное");
            System.out.println("6. Удалить сотрудника");
            System.out.println("7. Очистить все пользовательские данные");
            System.out.println("8. Вернуться к стандартным данным");
            System.out.println("0. Назад в главное меню");
            System.out.println("=".repeat(60));
            System.out.print("Выберите пункт: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        animalService.createNewAnimal();
                        break;
                    case 2:
                        employeeService.createNewEmployee();
                        break;
                    case 3:
                        animalDisplayService.showAllAnimals();
                        break;
                    case 4:
                        employeeDisplayService.showAllEmployees();
                        break;
                    case 5:
                        animalRemovalService.deleteAnimal();
                        break;
                    case 6:
                        employeeRemovalService.deleteEmployee();
                        break;
                    case 7:
                        dataCleanupService.clearAllCustomData();
                        break;
                    case 8:
                        dataModeService.resetToDefaultData();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Неверный выбор!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число!");
            }
        }
    }
}
