/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - СОБЛЮДЕН:
 *    - Класс отвечает только за демонстрацию участия в выставках
 *    - Четкая ответственность: показать возможности животных на выставках
 *    - Не смешивает логику с кормлением, лечением или другими аспектами
 *
 * 2. OCP (Open/Closed) - СОБЛЮДЕН:
 *    - Закрыт для модификаций демонстрационной логики
 *    - Открыт для расширения через новые типы животных (ExhibitionParticipant)
 *    - Можно добавить новые этапы выставок без изменения существующего кода
 *
 * 3. LSP (Liskov Substitution) - СОБЛЮДЕН:
 *    - Работает с любыми животными, реализующими интерфейс ExhibitionParticipant
 *    - Все участники выставок корректно обрабатываются через общий интерфейс
 *    - Методы prepareForExhibition, participateInExhibition, returnFromExhibition
 *      работают с любым подтипом ExhibitionParticipant
 *
 * 4. ISP (Interface Segregation) - СОБЛЮДЕН:
 *    - Использует только интерфейс ExhibitionParticipant
 *    - Интерфейс содержит все необходимые методы для участия в выставках
 *    - Не зависит от ненужных методов других интерфейсов
 *
 * 5. DIP (Dependency Inversion) - ПРАКТИЧЕСКИ СОБЛЮДЕН:
 *    - Зависит от абстракций Animal и ExhibitionParticipant (соблюдено)
 *    - НО: Прямая зависимость от Database.getAnimals() (нарушено)
 *    - Статический метод имеет жесткие зависимости
 *    - Нет внедрения зависимостей через конструктор или параметры
 */
package core.com.zoo;

import core.entities.Animal;
import core.interfaces.ExhibitionParticipant;

public class ExhibitionDemonstrator {

    public static void demonstrateExhibitions() {
        System.out.println("\n🎪 ДЕМОНСТРАЦИЯ УЧАСТИЯ В ВЫСТАВКАХ:");
        System.out.println("──────────────────────────────────────────────────────");

        System.out.println("1. Проверка доступности для выставок:");

        for (Animal animal : Database.getAnimals()) {
            if (animal instanceof ExhibitionParticipant) {
                ExhibitionParticipant participant = (ExhibitionParticipant) animal;

                System.out.print("   • " + animal.getName() + ": ");
                if (participant.isAvailableForExhibition()) {
                    System.out.println("✅ Доступен для выставок");
                    System.out.println("     🎯 Уровень тренировки: " + participant.getTrainingLevel());
                    System.out.println("     📊 Участий в выставках: " + participant.getExhibitionCount());
                } else {
                    System.out.println("❌ Не доступен для выставок");
                    System.out.println("     💡 Рекомендации: " + participant.getExhibitionRecommendations());
                }
            } else {
                System.out.println("   • " + animal.getName() + ": ⚠️  Не участвует в выставках (тип: " +
                        animal.getType().getRussianName() + ")");
            }
        }

        // Подготовка и участие в выставке
        System.out.println("\n2. Подготовка и участие в выставке:");

        for (Animal animal : Database.getAnimals()) {
            if (animal instanceof ExhibitionParticipant) {
                ExhibitionParticipant participant = (ExhibitionParticipant) animal;

                if (participant.isAvailableForExhibition()) {
                    System.out.println("   • Подготовка " + animal.getName() + ":");
                    participant.prepareForExhibition();

                    System.out.println("   • Участие в выставке 'Зоопарк приглашает':");
                    participant.participateInExhibition("Зоопарк приглашает", 45);

                    System.out.println("   • Возвращение с выставки:");
                    participant.returnFromExhibition();
                    System.out.println();
                }
            }
        }
    }
}
