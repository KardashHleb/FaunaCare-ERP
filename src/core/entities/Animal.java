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

    /**
     * Конструктор животного
     * @param id уникальный идентификатор
     * @param name имя животного
     * @param type тип животного (из enum)
     * @param age возраст в годах
     */
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