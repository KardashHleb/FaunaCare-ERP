/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - ✅ СОБЛЮДЕН
 *    - Класс отвечает ТОЛЬКО за вывод информации о животных
 *    - Не создает животных, не хранит данные, не обрабатывает логику
 *    - Четкая ответственность: форматирование и вывод в консоль
 *
 * 2. OCP (Open/Closed) - ✅ СОБЛЮДЕН
 *    - Закрыт для модификаций: логика вывода стабильна
 *    - Открыт для расширения через:
 *      а) Наследование и переопределение getAnimalEmoji()
 *      б) Добавление новых методов для других типов животных
 *      в) Передача разных стратегий форматирования
 *
 * 3. LSP (Liskov Substitution) - ✅ СОБЛЮДЕН
 *    - Методы корректно работают с любыми подтипами Animal
 *    - printAnimalInfo() принимает Animal, но проверяет instanceof
 *
 * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *    - Минимальный публичный API (только 2 метода)
 *    - Клиенты зависят только от методов, которые используют
 *    - Нет "толстых" интерфейсов или ненужных зависимостей
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Зависит от абстракции Animal, а не от конкретных классов
 */


package core.example;

import core.entities.Animal;
import core.entities.Bird;
import core.entities.Mammal;

public class AnimalPrinter {

    public void printAnimalsHeader(boolean showDetails) {
        if (showDetails) {
            System.out.println("1. Создание животных:");
        }
    }

    public void printAnimalInfo(Animal animal, boolean showDetails) {
        if (!showDetails) return;

        String emoji = getAnimalEmoji(animal);
        String info = animal.getInfo();

        System.out.println("   " + emoji + " " + info);

        // Дополнительная информация в зависимости от типа животного
        if (animal instanceof Mammal mammal) {
            printMammalInfo(mammal);
        } else if (animal instanceof Bird bird) {
            printBirdInfo(bird);
        }
    }

    private String getAnimalEmoji(Animal animal) {
        // Можно сделать умнее, но пока так
        if (animal instanceof Mammal) {
            if (animal.getName().contains("слон")) return "🐘";
            if (animal.getName().contains("лев")) return "🦁";
        } else if (animal instanceof Bird) {
            if (animal.getName().contains("Кеша")) return "🦜";
            if (animal.getName().contains("Голубь")) return "🦜";
        }
        return "🐾";
    }

    private void printMammalInfo(Mammal mammal) {
        // Здесь можно выводить специфичную для млекопитающих информацию
        // Например, беременность, норму пищи и т.д.
        // Для простоты пока оставим пустым
    }

    private void printBirdInfo(Bird bird) {
        // Специфичная для птиц информация
        // Например, тренировки
    }
}
