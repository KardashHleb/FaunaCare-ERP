package core.com.zoo;

import core.entities.Animal;
import core.enums.AnimalType;
import core.interfaces.Feedable;

import java.util.List;

import static core.com.zoo.Database.getAnimals;

public class AnimalShelterDemonstrator {
    /**
     * Демонстрация ухода за животными
     */
    public static void demonstrateAnimalCare() {
        List<Animal> animals = getAnimals();

        System.out.println("\n🐾 ДЕМОНСТРАЦИЯ УХОДА ЗА ЖИВОТНЫМИ:");
        System.out.println("──────────────────────────────────────────────────────");

        System.out.println("1. Кормление животных:");

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            System.out.println("   " + (i + 1) + ". Обработка: " + animal.getName());

            if (animal instanceof Feedable) {
                AnimalType type = animal.getType();
                Feedable feedable = (Feedable) animal;
                String foodType = type.getDefaultFood();
                double amount = feedable.getDailyFoodRequirement();
                feedable.feed(foodType, amount);
            }
        }
    }
}
