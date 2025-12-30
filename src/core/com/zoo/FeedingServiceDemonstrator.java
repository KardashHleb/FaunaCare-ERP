/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - СОБЛЮДЕН:
 *    - Класс отвечает только за демонстрацию сервиса кормления
 *    - Четкая ответственность: показать процесс кормления животных
 *    - Не смешивает логику с другими аспектами системы
 *
 * 2. OCP (Open/Closed) - СОБЛЮДЕН:
 *    - Закрыт для модификаций демонстрационной логики
 *    - Открыт для расширения через новые типы животных (Feedable)
 *    - Можно добавить новые этапы демонстрации без изменения существующего кода
 *
 * 3. LSP (Liskov Substitution) - СОБЛЮДЕН:
 *    - Работает с любыми животными, реализующими интерфейс Feedable
 *    - Все Feedable объекты корректно обрабатываются через общий интерфейс
 *    - Метод feedAnimal работает с любым подтипом Animal
 *
 * 4. ISP (Interface Segregation) - СОБЛЮДЕН:
 *    - Использует минимальный интерфейс Feedable (getDailyFoodRequirement)
 *    - Не зависит от ненужных методов других интерфейсов
 *    - Интерфейс имеет одну четкую ответственность - кормление
 *
 * 5. DIP (Dependency Inversion) - Частично соблюден:
 *    - Зависит от абстракций Animal и Feedable (соблюдено)
 *    - Database - это не внешняя зависимость, а часть предметной области
 *    - Нет внедрения зависимостей через конструктор или параметры
 */
package core.com.zoo;

import core.entities.Animal;
import core.interfaces.Feedable;

public class FeedingServiceDemonstrator {

    // Ваш метод
    public static void demonstrateFeedingService() {
        System.out.println("\n🍎 ДЕМОНСТРАЦИЯ СЕРВИСА КОРМЛЕНИЯ:");
        System.out.println("─────────────────────────────────────");
        System.out.println("Статистика перед кормлением:");
        System.out.println("  • Голодных животных: " + Database.getHungryCount());
        System.out.println("  • Накормленных: " + Database.getFedCount());

        System.out.println("1. Кормление животных:");
        for (Animal animal : Database.getAnimals()) {
            if (animal instanceof Feedable) {
                Feedable feedable = (Feedable) animal;
                double requiredFood = feedable.getDailyFoodRequirement();
                Database.getFeedingService().feedAnimal(feedable, "специальный корм", requiredFood);

                System.out.println("   • " + animal.getName() + " покормлен");
            }
        }
        // Обновляем статистику после кормления
        Database.updateHungerStats();

        System.out.println("\nСтатистика после кормления:");
        System.out.println("  • Голодных животных: " + Database.getHungryCount());
        System.out.println("  • Накормленных: " + Database.getFedCount());
        Database.updateHungerStats();
    }
}
