/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за очистку всех данных
 * - Единственная ответственность: подтверждение и выполнение очистки
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций логики очистки
 * - Открыт для расширения через новые стратегии очистки
 *
 * LSP (Liskov Substitution) - НЕ ПРИМЕНЯЕТСЯ:
 * - Нет иерархии наследования
 * - Нет переопределения методов
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет только один публичный метод
 * - Минимальный интерфейс для очистки данных
 *
 * DIP (Dependency Inversion) - ЧАСТИЧНО СОБЛЮДЕН:
 * - Scanner передан через конструктор (соблюдено)
 * - Прямая зависимость от Database (нарушено)
 * - Прямая зависимость от System.out (нарушено)
 */


package core.customData.Service;

import core.com.zoo.Database;

import java.util.Scanner;

public class DataCleanupService {
    private final Scanner scanner;

    public DataCleanupService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void clearAllCustomData() {
        System.out.print("\n⚠️  ВНИМАНИЕ: Вы уверены, что хотите удалить ВСЕ данные? (да/нет): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("да") || confirm.equals("д")) {
            Database.clearAllData();
            System.out.println("✅ Все данные очищены!");
        } else {
            System.out.println("Очистка отменена.");
        }
    }
}
