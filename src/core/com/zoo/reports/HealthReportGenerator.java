/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - ✅ СОБЛЮДЕН
 *    - Генерирует отчет о здоровье животных
 *    - Не стоит дробить на микроклассы
 *
 * 2. OCP (Open/Closed) - ⚠️ ПРАКТИЧЕСКИ СОБЛЮДЕН
 *    - Если формат отчета изменится - придется править метод
 *    - Не нужно создавать сложную архитектуру ради одного отчета
 *
 * 3. LSP (Liskov Substitution) - ⚠️ ПРАКТИЧЕСКИ СОБЛЮДЕН
 *    - Не нарушает работу с Animal, просто пропускает некритичные моменты
 *
 * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Зависит от Animal и HealthCheckable - это абстракции
 *    - Не зависит от конкретных реализаций
 */

package core.com.zoo.reports;

import core.entities.Animal;
import core.interfaces.HealthCheckable;

import java.util.List;

public class HealthReportGenerator {

    public String generateHealthReport(String period, List<Animal> animals, int healthyCount, int sickCount) {
        StringBuilder report = new StringBuilder();
        report.append("=== МЕДИЦИНСКИЙ ОТЧЕТ ЗА ").append(period.toUpperCase()).append(" ===\n\n");

        for (Animal animal : animals) {
            if (animal instanceof HealthCheckable) {
                HealthCheckable healthCheckable = (HealthCheckable) animal;

                report.append(animal.getName()).append(":\n");
                report.append("  • Статус: ").append(animal.getHealthStatus().getName()).append("\n");
                report.append("  • Здоров: ").append(healthCheckable.isHealthy() ? "Да" : "Нет").append("\n");
                report.append("  • Последний осмотр: ").append(
                        healthCheckable.getLastCheckupDate() != null ?
                                healthCheckable.getLastCheckupDate() : "Не проводился").append("\n");
            }
        }

        report.append("\nИТОГО:\n");
        report.append("  • Здоровых: ").append(healthyCount).append("\n");
        report.append("  • Больных: ").append(sickCount).append("\n");

        return report.toString();
    }
}
