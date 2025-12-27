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
