/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за валидацию данных сотрудников
 * - Единственная ответственность: проверка корректности входных данных
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций существующих правил валидации
 * - Открыт для расширения через добавление новых статических методов
 *
 * LSP (Liskov Substitution) - НЕ ПРИМЕНЯЕТСЯ:
 * - Нет иерархии наследования
 * - Только статические методы, нет переопределения
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН:
 * - Минимальный интерфейс (1 публичный метод)
 * - Клиенты зависят только от необходимой функциональности
 *
 * DIP (Dependency Inversion) - СОБЛЮДЕН:
 * - Не зависит от конкретных реализаций (только базовые типы)
 * - Не имеет скрытых зависимостей
 */

package core.customData.Menu;

import core.entities.Employee;
import java.util.List;

public class EmployeeValidator {

    public static void validateEmployeeData(String id, String fullName, List<Employee> existingEmployees) {
        // Проверка ID
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID сотрудника не может быть пустым");
        }

        // Проверка уникальности ID
        for (Employee employee : existingEmployees) {
            if (employee.getId().equals(id)) {
                throw new IllegalArgumentException("Сотрудник с ID '" + id + "' уже существует");
            }
        }

        // Проверка ФИО
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("ФИО не может быть пустым");
        }

        if (fullName.trim().length() < 2) {
            throw new IllegalArgumentException("ФИО должно быть минимум 2 символа");
        }
    }
}