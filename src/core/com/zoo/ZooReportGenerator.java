package core.com.zoo;

import core.entities.Animal;
import core.interfaces.ExhibitionParticipant;
import core.interfaces.Feedable;
import core.interfaces.HealthCheckable;
import core.interfaces.ReportGenerator;

import java.util.List;

public class ZooReportGenerator implements ReportGenerator {

    @Override
    public String generateFeedingReport(String period) {
        List<Animal> animals = Database.getAnimals();

        StringBuilder report = new StringBuilder();
        report.append("=== ОТЧЕТ ПО КОРМЛЕНИЮ ЗА ").append(period.toUpperCase()).append(" ===\n");
        report.append("Всего животных: ").append(animals.size()).append("\n\n");

        int fedCount = 0;
        for (Animal animal : animals) {
            if (animal instanceof Feedable) {
                Feedable feedable = (Feedable) animal;
                report.append(animal.getName()).append(" (").append(animal.getType().getRussianName()).append("):\n");
                report.append("  • Расписание: ").append(feedable.getFeedingSchedule()).append("\n");
                report.append("  • Рекомендуемая пища: ").append(feedable.getRecommendedFoodType()).append("\n");
                report.append("  • Голоден: ").append(feedable.isHungry() ? "Да" : "Нет").append("\n");

                if (!feedable.isHungry()) {
                    fedCount++;
                }
            }
        }

        report.append("\nИТОГО:\n");
        report.append("  • Накормлено: ").append(fedCount).append("\n");
        report.append("  • Требуют кормления: ").append(animals.size() - fedCount).append("\n");

        return report.toString();
    }

    @Override
    public String generateHealthReport(String period) {
        List<Animal> animals = Database.getAnimals();

        StringBuilder report = new StringBuilder();
        report.append("=== МЕДИЦИНСКИЙ ОТЧЕТ ЗА ").append(period.toUpperCase()).append(" ===\n\n");

        int healthyCount = 0;
        int sickCount = 0;

        for (Animal animal : animals) {
            if (animal instanceof HealthCheckable) {
                HealthCheckable healthCheckable = (HealthCheckable) animal;

                report.append(animal.getName()).append(":\n");
                report.append("  • Статус: ").append(animal.getHealthStatus().getName()).append("\n");
                report.append("  • Здоров: ").append(healthCheckable.isHealthy() ? "Да" : "Нет").append("\n");
                report.append("  • Последний осмотр: ").append(
                        healthCheckable.getLastCheckupDate() != null ?
                                healthCheckable.getLastCheckupDate() : "Не проводился").append("\n");

                if (healthCheckable.isHealthy()) {
                    healthyCount++;
                } else {
                    sickCount++;
                }
            }
        }

        report.append("\nСТАТИСТИКА:\n");
        report.append("  • Здоровых: ").append(healthyCount).append("\n");
        report.append("  • Больных: ").append(sickCount).append("\n");
        report.append("  • Процент здоровых: ").append(
                animals.size() > 0 ? (healthyCount * 100) / animals.size() : 0).append("%\n");

        return report.toString();
    }

    @Override
    public String generateExhibitionReport(String period) {
        List<Animal> animals = Database.getAnimals();

        StringBuilder report = new StringBuilder();
        report.append("=== ОТЧЕТ ПО ВЫСТАВКАМ ЗА ").append(period.toUpperCase()).append(" ===\n\n");

        int availableCount = 0;
        int totalExhibitions = 0;

        for (Animal animal : animals) {
            if (animal instanceof ExhibitionParticipant) {
                ExhibitionParticipant participant = (ExhibitionParticipant) animal;

                report.append(animal.getName()).append(":\n");
                report.append("  • Доступен для выставок: ").append(participant.isAvailableForExhibition() ? "Да" : "Нет").append("\n");
                report.append("  • Уровень тренировки: ").append(participant.getTrainingLevel()).append("\n");
                report.append("  • Участий в выставках: ").append(participant.getExhibitionCount()).append("\n");

                if (participant.isAvailableForExhibition()) {
                    availableCount++;
                }
                totalExhibitions += participant.getExhibitionCount();
            } else {
                report.append(animal.getName()).append(": Не участвует в выставках\n");
            }
        }

        report.append("\nИТОГО:\n");
        report.append("  • Доступно для выставок: ").append(availableCount).append("\n");
        report.append("  • Всего участий: ").append(totalExhibitions).append("\n");

        return report.toString();
    }

    // Демонстрационный метод
    public static void demonstrateReports() {
        System.out.println("\n📊 ДЕМОНСТРАЦИЯ ГЕНЕРАЦИИ ОТЧЕТОВ:");
        System.out.println("──────────────────────────────────────────────────────");

        // Создаем генератор отчетов
        ZooReportGenerator reportGenerator = new ZooReportGenerator();

        // Генерируем отчеты
        System.out.println("1. Отчет по кормлению:");
        String feedingReport = reportGenerator.generateFeedingReport("дневной");
        System.out.println(feedingReport);

        System.out.println("\n2. Медицинский отчет:");
        String healthReport = reportGenerator.generateHealthReport("дневной");
        System.out.println(healthReport);

        System.out.println("\n3. Отчет по выставкам:");
        String exhibitionReport = reportGenerator.generateExhibitionReport("дневной");
        System.out.println(exhibitionReport);
    }
}