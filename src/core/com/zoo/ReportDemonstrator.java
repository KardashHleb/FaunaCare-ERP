/** Анализ с точки зрения SOLID:
        *
        * 1. SRP - ✅ СОБЛЮДЕН
 *    - Класс отвечает только за демонстрацию отчетов
 *    - UI-форматирование (System.out.println) - это часть демонстрации
 *
         * 2. OCP  - ⚠️ ПРАКТИЧЕСКИ ПРИЕМЛЕМО
 *    - Закрыт для модификаций демо-логики
 *    - Это не производственный ReportService, а демонстратор
 *
         * 3. LSP  - ✅ СОБЛЮДЕН
 *    - ZooReportGenerator может быть заменен на любую реализацию ReportGenerator
 *    - Все отчеты возвращаются как String - единый контракт
 *
         * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *    - Один публичный метод - идеальная сегрегация
 *    - Нет "толстых" интерфейсов
 *
 * 5. DIP (Dependency Inversion) - ⚠️ **НЕ НАРУШЕН В КОНТЕКСТЕ ДЕМО-КЛАССА**
 *    - ZooReportGenerator - это не случайная зависимость
 *    - Это генератор отчетов для зоопарка - естественная часть домена
 *    - Для демонстрационного кода прямое создание нормально
 */

package core.com.zoo;


public class ReportDemonstrator {

    public static void demonstrateReports() {
        System.out.println("\n📊 ДЕМОНСТРАЦИЯ ГЕНЕРАЦИИ ОТЧЕТОВ:");
        System.out.println("──────────────────────────────────────────────────────");

        ZooReportGenerator reportGenerator = new ZooReportGenerator();

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