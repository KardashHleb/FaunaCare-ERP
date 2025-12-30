/**
 * SRP (Single Responsibility) - СОБЛЮДЕН:
 * - Класс отвечает только за валидацию данных животных
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

import core.entities.Animal;
import java.util.List;

public class AnimalValidator {

    public static void validateAnimalData(String id, String name, int age, List<Animal> existingAnimals) {
        // Проверка ID
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID не может быть пустым");
        }

        // Проверка уникальности ID
        for (Animal animal : existingAnimals) {
            if (animal.getId().equals(id)) {
                throw new IllegalArgumentException("Животное с ID '" + id + "' уже существует");
            }
        }

        // Проверка имени
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }

        if (name.trim().length() < 2) {
            throw new IllegalArgumentException("Имя должно быть минимум 2 символа");
        }

        // Проверка возраста
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }

        if (age > 100) {
            throw new IllegalArgumentException("Возраст слишком большой (макс 100 лет)");
        }
    }
}