/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за управление режимами данных
 * - Единственная ответственность: переключение между стандартными и пользовательскими данными
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций логики переключения режимов
 * - Открыт для расширения через добавление новых режимов данных
 *
 * LSP (Liskov Substitution) - НЕ ПРИМЕНЯЕТСЯ:
 * - Нет иерархии наследования
 * - Нет переопределения методов
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет только один публичный метод
 * - Минимальный интерфейс для управления режимами данных
 *
 * DIP (Dependency Inversion) - ЧАСТИЧНО СОБЛЮДЕН:
 * - Scanner передан через конструктор (соблюдено)
 * - Прямая зависимость от Database (нарушено)
 * - Прямая зависимость от System.out (нарушено)
 */

package core.customData.Service;

import core.com.zoo.Database;

import java.util.Scanner;

public class DataModeService {
    private final Scanner scanner;

    public DataModeService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void resetToDefaultData() {
        System.out.print("\nВернуться к стандартным данным? (да/нет): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("да") || confirm.equals("д")) {
            Database.setUseCustomData(false);
            System.out.println("✅ Режим переключен на стандартные данные.");
            System.out.println("   При следующем запуске программы будут загружены стандартные данные.");
        }
    }
}
