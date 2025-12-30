/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за отображение списка сотрудников
 * - Не содержит логики создания, валидации или изменения данных
 *
 * OCP (Open/Closed) - СОБЛЮДЕН:
 * - Закрыт для модификаций отображения
 * - Открыт для расширения через наследование или композицию
 *
 * LSP (Liskov Substitution) - НЕ ПРИМЕНЯЕТСЯ:
 * - Нет иерархии наследования
 * - Нет переопределения методов
 *
 * ISP (Interface Segregation) - СОБЛЮДЕН ФАКТИЧЕСКИ:
 * - Предоставляет минимальный интерфейс (1 публичный метод)
 * - Не заставляет клиентов зависеть от ненужных методов
 */



package core.customData.Service;

import core.com.zoo.Database;
import core.entities.Employee;

public class EmployeeDisplayService {

    public void showAllEmployees() {
        System.out.println("\n--- СПИСОК ВСЕХ СОТРУДНИКОВ ---");

        if (Database.getEmployees().isEmpty()) {
            System.out.println("Нет сотрудников в базе данных.");
            return;
        }

        for (Employee employee : Database.getEmployees()) {
            System.out.println("\n" + employee.getInfo());
            System.out.println("-".repeat(40));
        }
    }
}