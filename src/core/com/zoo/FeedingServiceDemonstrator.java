package core.com.zoo;

import core.entities.Animal;
import core.interfaces.Feedable;

public class FeedingServiceDemonstrator {

    // Ваш метод
    public static void demonstrateFeedingService() {
        System.out.println("\n🍎 ДЕМОНСТРАЦИЯ СЕРВИСА КОРМЛЕНИЯ:");
        System.out.println("─────────────────────────────────────");
        System.out.println("1. Кормление животных:");
        for (Animal animal : Database.getAnimals()) {
            if (animal instanceof Feedable) {
                Database.getFeedingService().feedAnimal(animal, "специальный корм", 500);
                System.out.println("   • " + animal.getName() + " покормлен");
            }
        }
    }
}
