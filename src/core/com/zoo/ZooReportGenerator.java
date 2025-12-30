/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) -  СОБЛЮДЕН:
 *    - Класс отвечает за создание отчета, хотя и аггрегирует 3 типа отчетов,
 *    считаю допустимым
 *
 * 2. OCP (Open/Closed) - ЧАСТИЧНО СОБЛЮДЕН:
 *    - можно добавить новый тип отчета через интерфейс
 *
 * 3. LSP (Liskov Substitution) - СОБЛЮДЕН:
 *    - корректно реализует ReportGenerator
 *
 * 4. ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 *    - Минимальный интерфейс для создания сотрудников
 *    - Не навязывает клиентам ненужные зависимости
 *
 * 5. DIP (Dependency Inversion) - ЧАСТИЧНО СОБЛЮДЕН:
 *    - зависимости инжектятся, но есть жесткая связь с Database
 */

package core.com.zoo;


import core.com.zoo.reports.FeedingReportGenerator;
import core.com.zoo.reports.HealthReportGenerator;
import core.com.zoo.reports.ExhibitionReportGenerator;
import core.entities.Animal;
import core.interfaces.ReportGenerator;

import java.util.List;

public class ZooReportGenerator implements ReportGenerator {
    private final FeedingReportGenerator feedingReportGenerator;
    private final HealthReportGenerator healthReportGenerator;
    private final ExhibitionReportGenerator exhibitionReportGenerator;

    public ZooReportGenerator() {
        this.feedingReportGenerator = new FeedingReportGenerator();
        this.healthReportGenerator = new HealthReportGenerator();
        this.exhibitionReportGenerator = new ExhibitionReportGenerator();
    }

    @Override
    public String generateFeedingReport(String period) {
        List<Animal> animals = Database.getAnimals();
        int fedCount = Database.getFedCount();
        int hungryCount = Database.getHungryCount();

        return feedingReportGenerator.generateFeedingReport(period, animals, fedCount, hungryCount);
    }

    @Override
    public String generateHealthReport(String period) {
        List<Animal> animals = Database.getAnimals();
        int healthyCount = Database.getHealthyCount();
        int sickCount = Database.getSickCount();

        return healthReportGenerator.generateHealthReport(period, animals, healthyCount, sickCount);
    }

    @Override
    public String generateExhibitionReport(String period) {
        List<Animal> animals = Database.getAnimals();
        return exhibitionReportGenerator.generateExhibitionReport(period, animals);
    }


}