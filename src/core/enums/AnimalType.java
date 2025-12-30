package core.enums;

/**
 * Перечисление типов животных в зоопарке.
 *
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - ⚠️ ЧАСТИЧНО НАРУШЕН
 *    - Основная ответственность: представление типов животных
 *    - Но также содержит логику рекомендаций (кормление, температура, вольеры)
 *    - И логику классификации (теплокровные/холоднокровные, летающие/водные)
 *    - Нарушение: смешивает данные с бизнес-логикой рекомендаций
 *
 * 2. OCP (Open/Closed) - ✅ СОБЛЮДЕН
 *    - Закрыт для модификаций существующих типов
 *    - Открыт для расширения - легко добавить новый тип животного
 *    - Все методы корректно обрабатывают новые значения через default-case
 *
 * 3. LSP (Liskov Substitution) - 🔄 НЕ ПРИМЕНИМО
 *    - Перечисление не имеет иерархии наследования
 *    - Все значения одинаково обрабатываются в методах
 *    - Нет нарушений принципа подстановки
 *
 * 4. ISP (Interface Segregation) - 🔄 НЕ ПРИМЕНЯЕТСЯ
 *    - Перечисление не реализует интерфейсы
 *    - Предоставляет множество методов, но они все относятся к типам животных
 *    - Клиенты используют только нужные им методы
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Не зависит от других классов (самостоятельное перечисление)
 *    - Использует только примитивные типы и строки
 *    - Все зависимости инкапсулированы внутри перечисления
 */
public enum AnimalType {
    /**
     * Млекопитающие - теплокровные животные, рожающие живых детенышей
     * и вскармливающие их молоком.
     */
    MAMMAL("Млекопитающее", "Теплокровные, живородящие, вскармливают молоком", "Специализированный корм"),

    /**
     * Птицы - теплокровные животные с перьями, клювами и способностью летать
     * (хотя не все летают).
     */
    BIRD("Птица", "Теплокровные, с перьями и клювом, большинство летают", "Зерно и семена"),

    /**
     * Рептилии - холоднокровные животные с чешуйчатой кожей,
     * большинство откладывают яйца.
     */
    REPTILE("Рептилия", "Холоднокровные, с чешуйчатой кожей", "Насекомые и мелкие животные"),

    /**
     * Амфибии - животные, живущие как в воде, так и на суше,
     * с влажной кожей без чешуи.
     */
    AMPHIBIAN("Амфибия", "Живут в воде и на суше, влажная кожа", "Насекомые и черви"),

    /**
     * Рыбы - водные животные с жабрами для дыхания,
     * холоднокровные, с плавниками.
     */
    FISH("Рыба", "Водные, с жабрами и плавниками", "Специальный рыбный корм"),

    /**
     * Насекомые - беспозвоночные с шестью ногами,
     * с хитиновым экзоскелетом.
     */
    INSECT("Насекомое", "Беспозвоночные, 6 ног, хитиновый экзоскелет", "Растения и нектар"),

    /**
     * Членистоногие - включает пауков, скорпионов, ракообразных.
     */
    ARTHROPOD("Членистоногое", "Беспозвоночные с сегментированным телом", "Мелкие организмы"),

    /**
     * Неизвестный тип - используется как значение по умолчанию
     * или для животных, которые еще не классифицированы.
     */
    UNKNOWN("Неизвестный тип", "Требуется классификация", "Общий корм");

    // Поля перечисления
    private final String russianName;
    private final String description;
    private final String defaultFood;

    /**
     * Конструктор перечисления
     * @param russianName название на русском языке
     * @param description описание типа
     * @param defaultFood рекомендуемый корм по умолчанию
     */
    AnimalType(String russianName, String description, String defaultFood) {
        this.russianName = russianName;
        this.description = description;
        this.defaultFood = defaultFood;
    }

    /**
     * Получить название на русском языке
     * @return русское название типа
     */
    public String getRussianName() {
        return russianName;
    }

    /**
     * Получить описание типа животного
     * @return описание характеристик
     */
    public String getDescription() {
        return description;
    }

    /**
     * Получить рекомендуемый корм по умолчанию
     * @return тип корма
     */
    public String getDefaultFood() {
        return defaultFood;
    }

    /**
     * Проверить, является ли животное теплокровным
     * @return true для теплокровных животных
     */
    public boolean isWarmBlooded() {
        return this == MAMMAL || this == BIRD;
    }

    /**
     * Проверить, является ли животное холоднокровным
     * @return true для холоднокровных животных
     */
    public boolean isColdBlooded() {
        return this == REPTILE || this == AMPHIBIAN || this == FISH;
    }

    /**
     * Проверить, может ли животное летать (в общем смысле)
     * @return true для типов, которые обычно летают
     */
    public boolean canGenerallyFly() {
        return this == BIRD || this == INSECT;
    }

    /**
     * Проверить, является ли животное водным
     * @return true для животных, живущих в воде
     */
    public boolean isAquatic() {
        return this == FISH || this == AMPHIBIAN;
    }

    /**
     * Проверить, является ли животное наземным
     * @return true для животных, живущих на суше
     */
    public boolean isTerrestrial() {
        return this == MAMMAL || this == REPTILE || this == INSECT || this == ARTHROPOD;
    }

    /**
     * Получить рекомендуемую частоту кормления для типа животного
     * @return строка с частотой кормления
     */
    public String getRecommendedFeedingFrequency() {
        switch (this) {
            case MAMMAL:
                return "2 раза в день";
            case BIRD:
                return "3 раза в день";
            case REPTILE:
                return "1 раз в день";
            case AMPHIBIAN:
                return "1 раз в 2 дня";
            case FISH:
                return "2-3 раза в день небольшими порциями";
            case INSECT:
                return "1 раз в день";
            case ARTHROPOD:
                return "1 раз в 2-3 дня";
            default:
                return "1 раз в день";
        }
    }

    /**
     * Получить рекомендуемую температуру содержания
     * @return строка с температурным диапазоном
     */
    public String getRecommendedTemperature() {
        switch (this) {
            case MAMMAL:
                return "20-25°C";
            case BIRD:
                return "18-22°C";
            case REPTILE:
                return "25-30°C (требуется обогрев)";
            case AMPHIBIAN:
                return "18-24°C с высокой влажностью";
            case FISH:
                return "зависит от вида, обычно 22-26°C";
            case INSECT:
                return "комнатная температура 20-25°C";
            case ARTHROPOD:
                return "22-28°C";
            default:
                return "20-25°C";
        }
    }

    /**
     * Проверить, может ли животное этого типа участвовать в выставках
     * (общая рекомендация, конкретное животное может иметь свои ограничения)
     * @return true если тип подходит для выставок
     */
    public boolean isSuitableForExhibitions() {
        // Обычно птицы и некоторые млекопитающие подходят для выставок
        // Опасные или слишком крупные животные - нет
        return this == BIRD || this == MAMMAL;
    }

    /**
     * Получить требования к вольеру для этого типа
     * @return строку с требованиями
     */
    public String getEnclosureRequirements() {
        switch (this) {
            case MAMMAL:
                return "Просторный вольер с укрытием, доступ к воде";
            case BIRD:
                return "Вольер с возможностью полета, насесты";
            case REPTILE:
                return "Террариум с обогревом и УФ-лампой";
            case AMPHIBIAN:
                return "Акватеррариум с водой и сушей, высокая влажность";
            case FISH:
                return "Аквариум с фильтром и аэрацией";
            case INSECT:
                return "Инсектарий с вентиляцией";
            case ARTHROPOD:
                return "Террариум с субстратом и укрытиями";
            default:
                return "Стандартный вольер";
        }
    }

    /**
     * Получить AnimalType по русскому названию
     * @param russianName русское название типа
     * @return соответствующий AnimalType или UNKNOWN если не найден
     */
    public static AnimalType fromRussianName(String russianName) {
        for (AnimalType type : values()) {
            if (type.getRussianName().equalsIgnoreCase(russianName)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    /**
     * Получить все типы, которые являются теплокровными
     * @return массив теплокровных типов
     */
    public static AnimalType[] getWarmBloodedTypes() {
        return new AnimalType[]{MAMMAL, BIRD};
    }

    /**
     * Получить все типы, которые являются холоднокровными
     * @return массив холоднокровных типов
     */
    public static AnimalType[] getColdBloodedTypes() {
        return new AnimalType[]{REPTILE, AMPHIBIAN, FISH};
    }

    @Override
    public String toString() {
        return russianName + " - " + description;
    }
}