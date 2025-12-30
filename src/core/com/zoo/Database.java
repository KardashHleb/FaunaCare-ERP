/**
 Анализ с точки зрения SOLID :

 SRP  - УМЕРЕННО НАРУШЕН:
 В учебном проекте допустимо небольшое нарушение для упрощения архитектуры.
 Класс действительно совмещает:
 Хранение данных (животные, сотрудники)
 Управление состоянием (флаг пользовательских данных)
 Базовую бизнес-логику (статистика)
 Однако это оправдано для небольшой учебной системы -
 разделение создало бы избыточную сложность.

 OCP - УМЕРЕННО НАРУШЕН:
 Класс закрыт для модификаций при изменении требований.
 В ПРОМЫШЛЕННОМ проекте это было бы проблемой, но для УЧЕБНОГО:
 Простота важнее расширяемости

 LSP  - СОБЛЮДЕН:
 Работает с любыми наследниками Animal и Employee
 Корректно использует интерфейсы Feedable и HealthCheckable
 Не делает предположений о конкретных реализациях

 ISP  - НЕ ПРИМЕНИМ:
 Класс не реализует внешние интерфейсы, он сам предоставляет API.
 В данном случае это нормально - класс сам определяет свой контракт.

 DIP  - ЧАСТИЧНО НАРУШЕН:
 Зависит от конкретных реализаций (FeedingServiceImpl).
 Однако :
 Система небольшая, замена сервисов маловероятна
 Статическая природа класса упрощает использование
 Можно легко модернизировать при необходимости

 Соответствует принципу KISS (Keep It Simple, Stupid)
 */

package core.com.zoo;

import core.entities.Animal;
import core.entities.Employee;
import core.interfaces.Feedable;
import core.interfaces.HealthCheckable;
import services.impl.FeedingServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static List<Animal> animals = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static FeedingServiceImpl feedingService = new FeedingServiceImpl();
    private static boolean useCustomData = false;


    public static List<Animal> getAnimals() {
        return animals;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static FeedingServiceImpl getFeedingService() {
        return feedingService;
    }

    // ОДИН метод addAnimal - объединяем функционал
    public static void addAnimal(Animal animal) {
        // Проверяем уникальность ID
        for (Animal a : animals) {
            if (a.getId().equals(animal.getId())) {
                System.out.println("⚠️ Предупреждение: Животное с ID " + animal.getId() + " уже существует!");
                return;
            }
        }

        animals.add(animal);
        useCustomData = true; // Помечаем как пользовательские данные
        updateHungerStats(); // Обновляем статистику
        updateHealthStats(); // Обновляем статистику здоровья
    }

    // ОДИН метод addEmployee - объединяем функционал
    public static void addEmployee(Employee employee) {
        // Проверяем уникальность ID
        for (Employee e : employees) {
            if (e.getId().equals(employee.getId())) {
                System.out.println("⚠️ Предупреждение: Сотрудник с ID " + employee.getId() + " уже существует!");
                return;
            }
        }

        employees.add(employee);
        useCustomData = true; // Помечаем как пользовательские данные
    }

    // Методы для установки данных (для совместимости со старым кодом)
    public static void setAnimals(List<Animal> animalsList) {
        animals = new ArrayList<>(animalsList);
        // Если устанавливаем список животных, возможно это пользовательские данные
        if (!animalsList.isEmpty()) {
            useCustomData = true;
        }
        updateHungerStats();
        updateHealthStats();
    }


    private static int healthyCount = 0;
    private static int hungryCount = 0;

    /**
     * Обновить статистику здоровья
     */
    public static void updateHealthStats() {
        healthyCount = 0;
        List<Animal> animals = getAnimals();

        for (Animal animal : animals) {
            if (animal instanceof HealthCheckable) {
                HealthCheckable hc = (HealthCheckable) animal;
                if (hc.isHealthy()) {
                    healthyCount++;
                }
            }
        }
    }

    /**
     * Обновить статистику голода
     */
    public static void updateHungerStats() {
        hungryCount = 0;
        List<Animal> animals = getAnimals();

        for (Animal animal : animals) {
            if (animal instanceof Feedable) {
                Feedable feedable = (Feedable) animal;
                if (feedable.isHungry()) {
                    hungryCount++;
                }
            }
        }


    }

    public static int getHealthyCount() {
        updateHealthStats(); // Обновляем перед возвратом
        return healthyCount;
    }

    /**
     * Получить количество голодных животных
     */
    public static int getHungryCount() {
        updateHungerStats(); // Обновляем перед возвратом
        return hungryCount;
    }

    /**
     * Получить количество больных животных
     */
    public static int getSickCount() {
        return getAnimals().size() - getHealthyCount();
    }

    /**
     * Получить количество накормленных животных
     */
    public static int getFedCount() {
        return getAnimals().size() - getHungryCount();
    }
    public static void setUseCustomData(boolean useCustom) {
        useCustomData = useCustom;
    }

    public static boolean isUsingCustomData() {
        return useCustomData;
    }

    /**
     * Полностью очистить данные
     */
    public static void clearAllData() {
        animals.clear();
        employees.clear();
        useCustomData = false;
    }

    /**
     * Добавить животное (для пользовательских данных)
     */


    /**
     * Удалить животное по ID
     */
    public static boolean removeAnimalById(String id) {
        return animals.removeIf(animal -> animal.getId().equals(id));
    }

    /**
     * Удалить сотрудника по ID
     */
    public static boolean removeEmployeeById(String id) {
        return employees.removeIf(emp -> emp.getId().equals(id));
    }
}