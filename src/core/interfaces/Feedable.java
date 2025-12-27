package core.interfaces;

/**
 * Интерфейс для объектов, которые можно кормить.
 * Следует принципу ISP (Interface Segregation) -
 * отвечает только за одну ответственность: кормление.
 * Реализуется классами животных, которые нуждаются в питании.
 */
public interface Feedable {

    /**
     * Метод для кормления животного
     * @param foodType тип пищи (например, "Мясо", "Зерно", "Фрукты")
     * @param amount количество пищи в граммах
     */
    void feed(String foodType, double amount);

    /**
     * Получить расписание кормления животного
     * @return строку с расписанием (например, "9:00, 15:00, 20:00")
     */
    String getFeedingSchedule();

    /**
     * Установить расписание кормления
     * @param schedule строка с расписанием
     */
    void setFeedingSchedule(String schedule);

    /**
     * Проверить, голодно ли животное
     * @return true если животное голодно и требует кормления
     */
    boolean isHungry();

    /**
     * Получить рекомендуемый тип пищи для животного
     * @return тип пищи (например, "Травоядное", "Плотоядное", "Всеядное")
     */
    String getRecommendedFoodType();
    // Абстрактный метод для получения суточной нормы в кг
    double getDailyFoodRequirement();
}