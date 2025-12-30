/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP  - СОБЛЮДЕН:
 *    - Класс отвечает за демонстрацию теории SOLID
 *
 * 2. OCP  - СОБЛЮДЕН:
 *    - Закрыт для модификаций демонстрационной логики
 *    - Открыт для расширения через параметры (список животных)
 *    - Можно добавить новые демонстрации без изменения существующих методов
 *
 * 3. LSP  - СОБЛЮДЕН:
 *    - Корректно работает с любыми подтипами Animal
 *    - Использует полиморфизм через интерфейс Feedable
 *    - Все животные могут быть обработаны в общем списке
 *
 * 4. ISP  - СОБЛЮДЕН:
 *    - Использует минимальный интерфейс Feedable для демонстрации
 *    - Не зависит от ненужных методов животных
 *    - Каждый используемый интерфейс имеет одну ответственность
 *
 * 5. DIP  - СОБЛЮДЕН:
 *    - Зависит от абстракции Feedable, а не конкретных классов животных
 *    - Параметры методов используют интерфейсы и абстрактные классы
 *    - Легко можно заменить реализацию без изменения кода демонстрации
 */

package core.com.zoo;

import core.entities.Animal;
import core.interfaces.Feedable;

import java.util.ArrayList;
import java.util.List;

public class SOLIDPrinciples {

    /**
     * Демонстрация теории SOLID принципов
     */
    public static void demonstrateTheory() {
        System.out.println("\n ДЕМОНСТРАЦИЯ ТЕОРИИ ПРИНЦИПОВ SOLID:");
        System.out.println("──────────────────────────────────────────────────────");

        System.out.println("1. S (Single Responsibility):");
        System.out.println("   • Feedable - отвечает только за кормление");
        System.out.println("   • HealthCheckable - отвечает только за медосмотры");
        System.out.println("   • ExhibitionParticipant - отвечает только за выставки");

        System.out.println("\n2. O (Open/Closed):");
        System.out.println("   • Можно добавить новый класс Fish extends Animal");
        System.out.println("   • Не нужно изменять существующий код");
        System.out.println("   • Пример: Создаем новое животное без изменения интерфейсов");

        System.out.println("\n3. L (Liskov Substitution):");
        System.out.println("   • Bird может использоваться везде, где ожидается Animal");
        System.out.println("   • Mammal может использоваться везде, где ожидается Animal");
        System.out.println("   • Пример: Все животные могут быть обработаны в общем списке");

        System.out.println("\n4. I (Interface Segregation):");
        System.out.println("   • Mammal реализует только Feedable и HealthCheckable");
        System.out.println("   • Bird реализует Feedable, HealthCheckable и ExhibitionParticipant");
        System.out.println("   • Каждый интерфейс - одна ответственность");

        System.out.println("\n5. D (Dependency Inversion):");
        System.out.println("   • Сервисы зависят от интерфейсов, а не конкретных классов");
        System.out.println("   • Пример: FeedingServiceImpl работает с Feedable, а не с конкретными животными");
        System.out.println("   • Можно легко заменить реализацию без изменения кода сервиса");
    }

    /**
     * Практическая демонстрация SOLID принципов на реальных животных
     */
    public static void demonstratePractice(List<Animal> animals) {
        System.out.println("\n6. ПРАКТИЧЕСКАЯ ДЕМОНСТРАЦИЯ SOLID:");
        System.out.println("══════════════════════════════════════════════════════════════");

        // Создаем список Feedable животных (разные типы, но общий интерфейс)
        List<Feedable> feedableAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Feedable) {
                feedableAnimals.add((Feedable) animal);
            }
        }

        // Используем полиморфизм - один метод для разных типов
        System.out.println("   • Массовое кормление разных типов животных:");
        for (Feedable animal : feedableAnimals) {
            animal.feed("специальный корм", 1000);
        }

        // Демонстрация расширяемости
        System.out.println("   • Пример расширения: можно добавить Reptile без изменения существующего кода");

        System.out.println("\n══════════════════════════════════════════════════════════════");
        System.out.println("✅ Демонстрация завершена успешно!");
        System.out.println("✅ Животные сыты и здоровы!");
        System.out.println("✅ Принципы SOLID соблюдены!");
    }

    /**
     * Комплексная демонстрация SOLID принципов
     */
    public static void demonstrateAll(List<Animal> animals) {
        demonstrateTheory();
        demonstratePractice(animals);
    }
}