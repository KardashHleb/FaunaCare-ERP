/**
 * Анализ с точки зрения SOLID (практический подход):
 *
 * 1. SRP (Single Responsibility) - ✅ СОБЛЮДЕН
 *    - Генерирует отчет по выставкам животных
 *    - Единственная ответственность: сбор данных и форматирование отчета
 *
 * 2. OCP (Open/Closed) - ⚠️ ПРАКТИЧЕСКИ СОБЛЮДЕН
 *    - Закрыт для модификаций логики подсчета
 *    - Не открыт для новых форматов отчета (только текст)
 *    - Для учебного проекта - достаточно
 *
 * 3. LSP (Liskov Substitution) - ✅ СОБЛЮДЕН
 *    - Корректная обработка разных типов животных
 *    - Есть обработка как ExhibitionParticipant, так и обычных Animal
 *
 * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *    - Использует только необходимый интерфейс ExhibitionParticipant
 *    - Нет зависимостей от ненужных интерфейсов
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Зависит от абстракций Animal и ExhibitionParticipant
 *    - Не зависит от конкретных реализаций
 *    - Форматирование внутри класса - допустимо для простого случая
 */


package core.com.zoo.reports;

import core.entities.Animal;
import core.interfaces.ExhibitionParticipant;

import java.util.List;

public class ExhibitionReportGenerator {

    public String generateExhibitionReport(String period, List<Animal> animals) {
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
}
