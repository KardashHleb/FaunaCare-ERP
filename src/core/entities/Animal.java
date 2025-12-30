
/**
 * Абстрактный базовый класс для всех животных в зоопарке.
 *
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - СОБЛЮДЕН
 *    - Класс отвечает только за представление базовых данных животного
 *    - Четкая ответственность: хранение и управление состоянием животного
 *    - Не смешивает логику кормления, лечения или других аспектов
 *
 * 2. OCP (Open/Closed) - СОБЛЮДЕН
 *    - Закрыт для модификаций базовой структуры животного
 *    - Открыт для расширения через наследование (абстрактный класс)
 *    - Можно создавать новые типы животных без изменения этого класса
 *
 * 3. LSP (Liskov Substitution) - СОБЛЮДЕН
 *    - Правильно спроектирован как базовый класс
 *    - Все наследники могут корректно заменять Animal
 *    - Абстрактные методы четко определены для реализации
 *
 * 4. ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ
 *    - Не реализует никаких интерфейсов напрямую
 *    - Предоставляет минимальный набор методов для представления животного
 *    - Функциональность разделена через отдельные интерфейсы (Feedable, HealthCheckable и т.д.)
 *
 * 5. DIP (Dependency Inversion) - СОБЛЮДЕН
 *    - Зависит только от абстракций (enum AnimalType, enum HealthStatus)
 *    - Не зависит от конкретных реализаций
 *    - Использует композицию через перечисления вместо наследования
 */




package core.entities;

import core.enums.AnimalType;
import core.enums.HealthStatus;

/**
 * Абстрактный базовый класс для всех животных в зоопарке.
 * Следует принципу OCP (Open/Closed) - закрыт для модификации,
 * но открыт для расширения через создание новых подклассов.
 */
public abstract class Animal {
    // Приватные поля - инкапсуляция
    private String id;
    private String name;
    private AnimalType type;
    private int age;
    private HealthStatus healthStatus;


    public Animal(String id, String name, AnimalType type, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.healthStatus = HealthStatus.HEALTHY; // По умолчанию здоров
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    /**
     * Абстрактный метод - каждый вид животных издает свой звук.
     * Реализация будет в конкретных подклассах.
     * @return звук, который издает животное
     */
    public abstract String makeSound();

    /**
     * Метод для отображения информации о животном
     * @return строковое представление животного
     */
    public String getInfo() {
        return String.format("[%s] %s, %d лет, тип: %s, здоровье: %s",
                id, name, age, type, healthStatus);
    }

    /**
     * Обновляет возраст животного на 1 год (например, в день рождения)
     */
    public void haveBirthday() {
        this.age++;
        System.out.println(name + " отмечает день рождения! Теперь ему " + age + " лет.");
    }

    @Override
    public String toString() {
        return getInfo();
    }
}