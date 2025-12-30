/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - ✅ СОБЛЮДЕН
 *    - Генерирует отчет по кормлению животных
 *    - Не требуется разбивать на микроклассы
 *
 * 2. OCP (Open/Closed) - ⚠️ ПРАКТИЧЕСКИ СОБЛЮДЕН
 *    - Если изменится формат отчета - нужно править метод
 *    - Не нужно создавать сложную архитектуру ради одного отчета
 *
 * 3. LSP (Liskov Substitution) - ✅ ПРАКТИЧЕСКИ СОБЛЮДЕН
 *    - Принимает List<Animal> - абстракция
 *    - instanceof Feedable - это фильтрация, а не нарушение LSP
 *    - Отчет только для кормимых животных - логичное требование
 *
 * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *    - Зависит только от необходимых интерфейсов
 *    - Один метод - одна четкая задача
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Зависит от абстракций Animal и Feedable
 *    - Не зависит от конкретных классов животных
 */



package core.com.zoo.reports;

import core.entities.Animal;
import core.interfaces.Feedable;

import java.util.List;

public class FeedingReportGenerator {

    public String generateFeedingReport(String period, List<Animal> animals, int fedCount, int hungryCount) {
        StringBuilder report = new StringBuilder();
        report.append("=== ОТЧЕТ ПО КОРМЛЕНИЮ ЗА ").append(period.toUpperCase()).append(" ===\n");
        report.append("Всего животных: ").append(animals.size()).append("\n\n");

        for (Animal animal : animals) {
            if (animal instanceof Feedable) {
                Feedable feedable = (Feedable) animal;
                report.append(animal.getName()).append(" (").append(animal.getType().getRussianName()).append("):\n");
                report.append("  • Расписание: ").append(feedable.getFeedingSchedule()).append("\n");
                report.append("  • Рекомендуемая пища: ").append(feedable.getRecommendedFoodType()).append("\n");
                report.append("  • Голоден: ").append(feedable.isHungry() ? "Да" : "Нет").append("\n");
            }
        }

        report.append("\nИТОГО:\n");
        report.append("  • Накормлено: ").append(fedCount).append("\n");
        report.append("  • Требуют кормления: ").append(hungryCount).append("\n");

        return report.toString();
    }
}
