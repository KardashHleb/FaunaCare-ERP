/**

 * 1. SRP (Single Responsibility) -  СОБЛЮДЕН
 *    - Класс отвечает только за представление данных сотрудника
 *
 * 2. OCP (Open/Closed) -  СОБЛЮДЕН
 *    - Закрыт для модификаций базовой структуры сотрудника
 *    - Открыт для расширения через наследование
 *    - Можно создать SpecializedEmployee extends Employee без изменения этого класса
 *
 * 3. LSP (Liskov Substitution) -  СОБЛЮДЕН
 *    - Все потенциальные наследники могут корректно заменять Employee
 *    - Отсутствуют методы, которые могут нарушить контракт при наследовании
 *
 * 4. ISP (Interface Segregation) -  НЕ ПРИМЕНИМО
 *    - Класс не реализует интерфейсы
 *    - Предоставляет минимальный публичный интерфейс (геттеры + getInfo)
 *    - Клиенты зависят только от необходимых им методов
 *
 * 5. DIP (Dependency Inversion) -  СОБЛЮДЕН
 *    - Зависит только от абстракции EmployeeRole (enum)
 *    - Не зависит от конкретных реализаций других классов
 *    - Все зависимости являются простыми типами данных
 */
package core.entities;

import core.enums.EmployeeRole;

public class Employee {
    private String id;
    private String name;
    private EmployeeRole role;

    public Employee(String id, String name, EmployeeRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public EmployeeRole getRole() { return role; }

    public String getInfo() {
        return name + " - " + role.getName() + " (ID: " + id + ")";
    }
}