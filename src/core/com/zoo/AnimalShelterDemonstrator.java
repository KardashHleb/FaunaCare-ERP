/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - СОБЛЮДЕН:
 *    - Класс отвечает только за демонстрацию ухода за животными
 *    - Четкая ответственность: показать процесс кормления животных
 *    - Не смешивает логику с другими аспектами (лечение, выставки и т.д.)
 *
 * 2. OCP (Open/Closed) - СОБЛЮДЕН:
 *    - Закрыт для модификаций демонстрационной логики
 *    - Открыт для расширения через новые типы животных (Animal, Feedable)
 *    - Можно добавить новые этапы ухода без изменения существующего кода
 *
 * 3. LSP (Liskov Substitution) - СОБЛЮДЕН:
 *    - Работает с любыми животными, реализующими интерфейс Feedable
 *    - Все Feedable объекты корректно обрабатываются через общий интерфейс
 *    - Метод feed() работает с любым подтипом Feedable
 *
 * 4. ISP (Interface Segregation) - СОБЛЮДЕН:
 *    - Использует минимальный интерфейс Feedable
 *
 * 5. DIP (Dependency Inversion) - ПРАКТИЧЕСКИ СОБЛЮДЕН:
 *    - Зависит от абстракций Animal и Feedable (соблюдено)
 *    - НО: Прямая зависимость от Database.getAnimals() через статический импорт (нарушено)
 *    - Нет внедрения зависимости через конструктор или параметры метода
 */


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
