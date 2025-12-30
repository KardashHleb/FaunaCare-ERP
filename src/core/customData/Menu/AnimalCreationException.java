/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за представление ошибок создания животных
 * - Единственная ответственность: инкапсуляция информации об ошибке
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций базовой функциональности исключения
 * - Открыт для расширения через создание подклассов для конкретных ошибок
 *
 * LSP (Liskov Substitution) - СОБЛЮДЕН:
 * - Может заменять Exception в любой иерархии обработки ошибок
 * - Сохраняет контракт родительского класса
 *
 * ISP (Interface Segregation) - НЕ ПРИМЕНЯЕТСЯ:
 * - Класс исключения, не предоставляет интерфейс
 * - Реализует стандартный контракт Exception
 *
 * DIP (Dependency Inversion) - СОБЛЮДЕН:
 * - Не имеет зависимостей от конкретных реализаций
 * - Зависит только от стандартных классов Java (Exception, Throwable)
 */
package core.customData.Menu;

public class AnimalCreationException extends Exception {
    public AnimalCreationException(String message) {
        super(message);
    }

    public AnimalCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
