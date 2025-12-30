/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за отображение списка животных
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
import core.entities.Animal;

public class AnimalDisplayService {

    public void showAllAnimals() {
        System.out.println("\n--- СПИСОК ВСЕХ ЖИВОТНЫХ ---");

        if (Database.getAnimals().isEmpty()) {
            System.out.println("Нет животных в базе данных.");
            return;
        }

        for (Animal animal : Database.getAnimals()) {
            System.out.println("\n" + animal.getInfo());
            System.out.println("-".repeat(40));
        }
    }
}
