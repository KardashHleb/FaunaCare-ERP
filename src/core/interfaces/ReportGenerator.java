package core.interfaces;

/**
 * Интерфейс для генерации отчетов о деятельности зоопарка.
 * Следует принципу ISP (Interface Segregation) -
 * отвечает только за одну ответственность: генерация отчетов.
 * Реализуется сервисами или менеджерами, которые формируют отчеты
 * по различным аспектам работы зоопарка.
 */
public interface ReportGenerator {

    /**
     * Сгенерировать отчет о кормлении животных
     * @param period период отчета (например, "дневной", "недельный", "месячный")
     * @return строковое представление отчета
     */
    String generateFeedingReport(String period);

    /**
     * Сгенерировать отчет о медицинских осмотрах
     * @param period период отчета
     * @return строковое представление отчета
     */
    String generateHealthReport(String period);

    /**
     * Сгенерировать отчет об участии животных в выставках
     * @param period период отчета
     * @return строковое представление отчета
     */
    String generateExhibitionReport(String period);

}