package core.enums;

/**
 * Перечисление статусов здоровья животных в зоопарке.
 *
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility) - ⚠️ ЧАСТИЧНО НАРУШЕН
 *    - Основная ответственность: представление статусов здоровья
 *    - Но также содержит бизнес-логику проверок (requiresUrgentCare, canEatNormalDiet)
 *    - И логику переходов между статусами (canTransitionTo, getNextImprovementStatus)
 *    - И UI-логику (getDisplayColor)
 *    - Нарушение: смешивает данные, бизнес-логику и UI-логику
 *    Для enum нарушение SRP в виде объединения данных с их поведением — это не баг, а фича
 *
 * 2. OCP (Open/Closed) - ✅ СОБЛЮДЕН
 *    - Закрыт для модификаций существующих статусов
 *    - Открыт для расширения - легко добавить новый статус здоровья
 *    - Все методы используют switch/default-case для обработки новых значений
 *
 * 3. LSP (Liskov Substitution) - 🔄 НЕ ПРИМЕНИМО
 *    - Перечисление не имеет иерархии наследования
 *    - Все значения enum обрабатываются одинаково в методах
 *    - Нет нарушений принципа подстановки
 *
 * 4. ISP (Interface Segregation) - 🔄 НЕ ПРИМЕНЯЕТСЯ
 *    - Перечисление не реализует интерфейсы
 *    - Предоставляет множество методов, но они все относятся к статусам здоровья
 *    - Клиенты используют только нужные им методы
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Абсолютно независимый enum
 *    - Не зависит от других классов или интерфейсов
 *    - Все данные и логика инкапсулированы внутри перечисления
 */
public enum HealthStatus {
    /**
     * Животное здорово, нет видимых проблем
     */
    HEALTHY("Здоров", "Животное в отличном состоянии, активное, хорошо ест",
            "Плановые осмотры", 0),

    /**
     * Животное нездорово, требуется наблюдение
     */
    SICK("Болен", "Наблюдаются симптомы заболевания, снижен аппетит",
            "Медицинское наблюдение, лечение", 1),

    /**
     * Животное на карантине (инфекционное заболевание или новый привоз)
     */
    QUARANTINED("На карантине", "Изолировано для предотвращения распространения заболеваний",
            "Изоляция, наблюдение, тестирование", 2),

    /**
     * Животное проходит лечение
     */
    UNDER_TREATMENT("На лечении", "Проходит курс лечения, регулярные процедуры",
            "Медикаментозное лечение, процедуры", 1),

    /**
     * Животное выздоравливает после болезни
     */
    RECOVERING("Выздоравливает", "Состояние улучшается, но требуется наблюдение",
            "Поддерживающая терапия, наблюдение", 1),

    /**
     * Животное хронически больно, требует постоянного ухода
     */
    CHRONIC("Хроническое заболевание", "Длительное заболевание, требующее постоянного ухода",
            "Регулярное лечение, адаптация условий", 1),

    /**
     * Животное ранено, требуется лечение травмы
     */
    INJURED("Ранен", "Имеются физические травмы, повреждения",
            "Лечение ран, обезболивание, покой", 2),

    /**
     * Животное в послеоперационном периоде
     */
    POST_OPERATIVE("После операции", "Восстановление после хирургического вмешательства",
            "Послеоперационный уход, контроль", 2),

    /**
     * Животное беременное/вынашивает потомство
     */
    PREGNANT("Беременен", "Вынашивает потомство, требует особого ухода",
            "Специальное питание, наблюдение", 1),

    /**
     * Животное старое, возрастные изменения
     */
    GERIATRIC("Пожилой возраст", "Возрастные изменения, сниженная активность",
            "Поддерживающий уход, адаптация условий", 1),

    /**
     * Животное в критическом состоянии
     */
    CRITICAL("Критическое состояние", "Тяжелое состояние, требуется срочная помощь",
            "Интенсивная терапия, постоянное наблюдение", 3),

    /**
     * Животное с особыми потребностями (инвалидность)
     */
    SPECIAL_NEEDS("Особые потребности", "Инвалидность или врожденные особенности",
            "Адаптированный уход, специальное оборудование", 1),

    /**
     * Статус неизвестен (новое животное, еще не осмотрено)
     */
    UNKNOWN("Неизвестно", "Статус здоровья еще не определен",
            "Срочный медицинский осмотр", 0);

    // Поля перечисления
    private final String Name;
    private final String description;
    private final String recommendedAction;
    private final int urgencyLevel; // Уровень срочности (0-3)

    /**
     * Конструктор перечисления
     * @param Name название
     * @param description описание статуса
     * @param recommendedAction рекомендуемые действия
     * @param urgencyLevel уровень срочности (0 - нет срочности, 3 - критично)
     */
    HealthStatus(String Name, String description, String recommendedAction, int urgencyLevel) {
        this.Name = Name;
        this.description = description;
        this.recommendedAction = recommendedAction;
        this.urgencyLevel = urgencyLevel;
    }

    /**
     * Получить название
     * @return название статуса
     */
    public String getName() {
        return Name;
    }

    /**
     * Получить описание статуса
     * @return описание состояния
     */
    public String getDescription() {
        return description;
    }

    /**
     * Получить рекомендуемые действия
     * @return строку с рекомендациями
     */
    public String getRecommendedAction() {
        return recommendedAction;
    }

    /**
     * Получить уровень срочности
     * @return уровень срочности (0-3)
     */
    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    /**
     * Проверить, требуется ли срочная медицинская помощь
     * @return true если срочность высокая (уровень 2-3)
     */
    public boolean requiresUrgentCare() {
        return urgencyLevel >= 2;
    }

    /**
     * Проверить, требуется ли регулярное медицинское наблюдение
     * @return true если требуется наблюдение
     */
    public boolean requiresRegularMonitoring() {
        return this != HEALTHY && this != UNKNOWN;
    }

    /**
     * Проверить, можно ли животное показывать посетителям
     * @return true если животное доступно для показа
     */
    public boolean isExhibitionAllowed() {
        return this == HEALTHY || this == PREGNANT || this == GERIATRIC ||
                this == SPECIAL_NEEDS || this == RECOVERING;
    }

    /**
     * Проверить, можно ли животное кормить обычным рационом
     * @return true если можно кормить обычной пищей
     */
    public boolean canEatNormalDiet() {
        return this == HEALTHY || this == PREGNANT || this == GERIATRIC ||
                this == SPECIAL_NEEDS || this == RECOVERING;
    }

    /**
     * Проверить, требуется ли изоляция животного
     * @return true если требуется изоляция
     */
    public boolean requiresIsolation() {
        return this == QUARANTINED || this == SICK || this == CRITICAL;
    }

    /**
     * Проверить, является ли статус временным (лечится/восстанавливается)
     * @return true если временный статус
     */
    public boolean isTemporary() {
        return this == UNDER_TREATMENT || this == RECOVERING ||
                this == INJURED || this == POST_OPERATIVE;
    }

    /**
     * Проверить, является ли статус постоянным/хроническим
     * @return true если постоянный статус
     */
    public boolean isPermanent() {
        return this == CHRONIC || this == GERIATRIC || this == SPECIAL_NEEDS;
    }

    /**
     * Получить частоту медицинских осмотров для этого статуса
     * @return строка с рекомендуемой частотой
     */
    public String getCheckupFrequency() {
        switch (this) {
            case CRITICAL:
                return "Каждые 4-6 часов";
            case SICK:
            case UNDER_TREATMENT:
            case INJURED:
                return "Ежедневно";
            case RECOVERING:
            case POST_OPERATIVE:
                return "Каждые 2-3 дня";
            case QUARANTINED:
            case PREGNANT:
                return "2 раза в неделю";
            case CHRONIC:
            case GERIATRIC:
            case SPECIAL_NEEDS:
                return "1 раз в неделю";
            case HEALTHY:
                return "1 раз в месяц (планово)";
            default:
                return "1 раз в неделю";
        }
    }

    /**
     * Получить рекомендуемый уровень ухода
     * @return строка с уровнем ухода
     */
    public String getCareLevel() {
        if (urgencyLevel == 3) return "Интенсивный уход (круглосуточно)";
        if (urgencyLevel == 2) return "Высокий уход (несколько раз в день)";
        if (urgencyLevel == 1) return "Средний уход (ежедневно)";
        return "Базовый уход (регулярные проверки)";
    }

    /**
     * Получить цвет для отображения статуса (в UI)
     * @return название цвета
     */
    public String getDisplayColor() {
        switch (this) {
            case HEALTHY:
            case PREGNANT:
                return "GREEN";
            case RECOVERING:
            case GERIATRIC:
            case SPECIAL_NEEDS:
                return "YELLOW";
            case SICK:
            case UNDER_TREATMENT:
            case INJURED:
            case POST_OPERATIVE:
            case CHRONIC:
                return "ORANGE";
            case QUARANTINED:
            case CRITICAL:
                return "RED";
            default:
                return "GRAY";
        }
    }

    /**
     * Можно ли перевести животное из текущего статуса в целевой
     * @param targetStatus целевой статус
     * @return true если переход возможен
     */
    public boolean canTransitionTo(HealthStatus targetStatus) {
        // Нельзя перейти в тот же статус
        if (this == targetStatus) return false;

        // Критическое состояние требует особых переходов
        if (this == CRITICAL) {
            return targetStatus == UNDER_TREATMENT || targetStatus == POST_OPERATIVE;
        }

        // Здоровое животное может заболеть или получить травму
        if (this == HEALTHY) {
            return targetStatus != HEALTHY && targetStatus != UNKNOWN;
        }

        // Больные животные могут выздоравливать или ухудшаться
        if (this == SICK || this == INJURED) {
            return targetStatus == RECOVERING || targetStatus == UNDER_TREATMENT ||
                    targetStatus == CRITICAL || targetStatus == HEALTHY;
        }

        // Все остальные переходы в основном разрешены
        return true;
    }

    /**
     * Получить следующий ожидаемый статус при улучшении состояния
     * @return следующий статус при улучшении
     */
    public HealthStatus getNextImprovementStatus() {
        switch (this) {
            case CRITICAL:
                return UNDER_TREATMENT;
            case UNDER_TREATMENT:
            case INJURED:
            case POST_OPERATIVE:
                return RECOVERING;
            case RECOVERING:
            case SICK:
                return HEALTHY;
            case QUARANTINED:
                return HEALTHY;
            default:
                return this; // Для остальных статусов улучшение не меняет статус
        }
    }

    /**
     * Получить все статусы, требующие срочного внимания
     * @return массив срочных статусов
     */
    public static HealthStatus[] getUrgentStatuses() {
        return new HealthStatus[]{CRITICAL, INJURED, POST_OPERATIVE, QUARANTINED};
    }

    /**
     * Получить все статусы, требующие изоляции
     * @return массив статусов с изоляцией
     */
    public static HealthStatus[] getIsolationStatuses() {
        return new HealthStatus[]{QUARANTINED, SICK, CRITICAL};
    }

    /**
     * Получить статус
     * @param Name  название
     * @return соответствующий статус или UNKNOWN если не найден
     */
    public static HealthStatus fromName(String Name) {
        for (HealthStatus status : values()) {
            if (status.getName().equalsIgnoreCase(Name)) {
                return status;
            }
        }
        return UNKNOWN;
    }

    /**
     * Получить статусы по уровню срочности
     * @param minUrgency минимальный уровень срочности
     * @param maxUrgency максимальный уровень срочности
     * @return массив статусов в диапазоне
     */
    public static HealthStatus[] getStatusesByUrgency(int minUrgency, int maxUrgency) {
        return java.util.Arrays.stream(values())
                .filter(status -> status.getUrgencyLevel() >= minUrgency &&
                        status.getUrgencyLevel() <= maxUrgency)
                .toArray(HealthStatus[]::new);
    }

    @Override
    public String toString() {
        return Name + " (" + description + ") - " + getCareLevel();
    }
}
