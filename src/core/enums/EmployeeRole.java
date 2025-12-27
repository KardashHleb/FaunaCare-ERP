package core.enums;

/**
 * Перечисление ролей сотрудников зоопарка.
 * Определяет должности и соответствующие им обязанности,
 * права и уровень доступа в системе ERP.
 * Следует принципу открытости/закрытости (OCP) -
 * легко добавить новую роль без изменения существующего кода.
 */
public enum EmployeeRole {
    /**
     * Смотритель/Работник по уходу за животными
     * Основная ответственность: ежедневный уход и кормление
     */
    KEEPER("Смотритель", "Уход за животными, кормление, чистка вольеров", 1),

    /**
     * Ветеринар
     * Основная ответственность: медицинское обслуживание животных
     */
    VETERINARIAN("Ветеринар", "Медосмотры, лечение, вакцинация животных", 2),

    /**
     * Ветеринарный техник/Ассистент
     * Помощник ветеринара
     */
    VET_TECHNICIAN("Ветеринарный техник", "Помощь ветеринару, базовые процедуры", 1),

    /**
     * Куратор/Заведующий отделом
     * Управление коллекцией животных
     */
    CURATOR("Куратор", "Управление коллекцией, планирование разведения", 3),

    /**
     * Дрессировщик
     * Подготовка животных к выставкам и представлениям
     */
    TRAINER("Дрессировщик", "Тренировка животных для выставок", 2),

    /**
     * Сотрудник образовательного отдела
     * Проведение экскурсий и образовательных программ
     */
    EDUCATION_OFFICER("Сотрудник образования", "Экскурсии, лекции, образовательные программы", 2),

    /**
     * Сотрудник по обогащению среды
     * Создание условий для естественного поведения животных
     */
    ENRICHMENT_OFFICER("Сотрудник по обогащению среды", "Разработка и внедрение программ обогащения", 2),

    /**
     * Директор зоопарка
     * Общее управление зоопарком
     */
    DIRECTOR("Директор", "Общее руководство зоопарком", 4),

    /**
     * Заместитель директора
     */
    DEPUTY_DIRECTOR("Заместитель директора", "Помощь директору, управление отделами", 4),

    /**
     * Сотрудник по питанию/Диетолог
     * Планирование рационов животных
     */
    NUTRITIONIST("Диетолог", "Разработка рационов, контроль питания", 2),

    /**
     * Сотрудник по закупкам
     * Закупка кормов и оборудования
     */
    PROCUREMENT_OFFICER("Сотрудник по закупкам", "Закупка кормов, оборудования, инвентаря", 2),

    /**
     * Сотрудник по техническому обслуживанию
     * Ремонт и обслуживание вольеров и оборудования
     */
    MAINTENANCE("Технический сотрудник", "Обслуживание и ремонт вольеров", 1),

    /**
     * Охранник
     * Обеспечение безопасности
     */
    SECURITY_GUARD("Охранник", "Обеспечение безопасности на территории", 1),

    /**
     * Кассир/Сотрудник билетных касс
     */
    CASHIER("Кассир", "Продажа билетов, работа с посетителями", 1),

    /**
     * Волонтер
     * Помощник без оплаты труда
     */
    VOLUNTEER("Волонтер", "Помощь в различных задачах под руководством", 0),

    /**
     * Стажер
     * Обучающийся сотрудник
     */
    INTERN("Стажер", "Обучение и помощь в различных отделах", 0),

    /**
     * Другая/Неопределенная роль
     */
    OTHER("Другая роль", "Специфические обязанности", 0);

    // Поля перечисления
    private final String Name;
    private final String responsibilities;
    private final int accessLevel; // Уровень доступа (0-4)

    /**
     * Конструктор перечисления
     * @param Name название
     * @param responsibilities основные обязанности
     * @param accessLevel уровень доступа к системе
     */
    EmployeeRole(String Name, String responsibilities, int accessLevel) {
        this.Name = Name;
        this.responsibilities = responsibilities;
        this.accessLevel = accessLevel;
    }

    /**
     * Получить название
     * @return  название должности
     */
    public String getName() {
        return Name;
    }

    /**
     * Получить описание обязанностей
     * @return строку с основными обязанностями
     */
    public String getResponsibilities() {
        return responsibilities;
    }

    /**
     * Получить уровень доступа
     * @return уровень доступа (0-4)
     */
    public int getAccessLevel() {
        return accessLevel;
    }

    /**
     * Проверить, имеет ли роль доступ к медицинским процедурам
     * @return true если может выполнять медицинские процедуры
     */
    public boolean canPerformMedicalProcedures() {
        return this == VETERINARIAN || this == VET_TECHNICIAN || this == DIRECTOR;
    }

    /**
     * Проверить, может ли роль кормить животных
     * @return true если может кормить животных
     */
    public boolean canFeedAnimals() {
        return this == KEEPER || this == VETERINARIAN || this == VET_TECHNICIAN ||
                this == TRAINER || this == NUTRITIONIST;
    }

    /**
     * Проверить, может ли роль проводить тренировки
     * @return true если может тренировать животных
     */
    public boolean canTrainAnimals() {
        return this == TRAINER || this == KEEPER || this == CURATOR;
    }

    /**
     * Проверить, может ли роль входить в вольеры с опасными животными
     * @return true если имеет доступ к опасным животным
     */
    public boolean canAccessDangerousAnimals() {
        return this == KEEPER || this == VETERINARIAN || this == TRAINER ||
                this == CURATOR || this == DIRECTOR;
    }

    /**
     * Проверить, является ли роль административной
     * @return true если административная должность
     */
    public boolean isAdministrative() {
        return this == DIRECTOR || this == DEPUTY_DIRECTOR || this == CURATOR;
    }

    /**
     * Проверить, является ли роль технической
     * @return true если техническая должность
     */
    public boolean isTechnical() {
        return this == MAINTENANCE || this == PROCUREMENT_OFFICER;
    }

    /**
     * Проверить, является ли роль образовательной
     * @return true если связана с образованием
     */
    public boolean isEducational() {
        return this == EDUCATION_OFFICER || this == TRAINER;
    }

    /**
     * Получить роли, которые могут работать с конкретным типом животного
     * @param animalType тип животного
     * @return массив подходящих ролей
     */
    public static EmployeeRole[] getRolesForAnimalType(AnimalType animalType) {
        switch (animalType) {
            case MAMMAL:
            case BIRD:
                return new EmployeeRole[]{KEEPER, VETERINARIAN, TRAINER, CURATOR};
            case REPTILE:
            case AMPHIBIAN:
                return new EmployeeRole[]{KEEPER, VETERINARIAN, CURATOR};
            case FISH:
                return new EmployeeRole[]{KEEPER, VETERINARIAN};
            case INSECT:
            case ARTHROPOD:
                return new EmployeeRole[]{KEEPER};
            default:
                return new EmployeeRole[]{KEEPER, VETERINARIAN};
        }
    }

    /**
     * Получить минимальную роль для выполнения действия
     * @param action действие (feeding, medical, training, etc.)
     * @return минимально требуемая роль
     */
    public static EmployeeRole getMinimumRoleForAction(String action) {
        switch (action.toLowerCase()) {
            case "feeding":
            case "кормление":
                return KEEPER;
            case "medical":
            case "медицинский":
                return VET_TECHNICIAN;
            case "training":
            case "тренировка":
                return TRAINER;
            case "enrichment":
            case "обогащение":
                return ENRICHMENT_OFFICER;
            case "administration":
            case "администрирование":
                return CURATOR;
            default:
                return KEEPER;
        }
    }

    /**
     * Проверить, достаточно ли прав у роли для действия
     * @param role роль сотрудника
     * @param requiredAction требуемое действие
     * @return true если роль имеет достаточно прав
     */
    public static boolean hasPermission(EmployeeRole role, String requiredAction) {
        EmployeeRole minimumRole = getMinimumRoleForAction(requiredAction);
        return role.getAccessLevel() >= minimumRole.getAccessLevel();
    }

    /**
     * Получить роли по уровню доступа
     * @param minLevel минимальный уровень доступа
     * @param maxLevel максимальный уровень доступа
     * @return массив ролей в указанном диапазоне
     */
    public static EmployeeRole[] getRolesByAccessLevel(int minLevel, int maxLevel) {
        return java.util.Arrays.stream(values())
                .filter(role -> role.getAccessLevel() >= minLevel && role.getAccessLevel() <= maxLevel)
                .toArray(EmployeeRole[]::new);
    }

    /**
     * Получить все медицинские роли
     * @return массив медицинских ролей
     */
    public static EmployeeRole[] getMedicalRoles() {
        return new EmployeeRole[]{VETERINARIAN, VET_TECHNICIAN};
    }

    /**
     * Получить все роли по уходу за животными
     * @return массив ролей по уходу
     */
    public static EmployeeRole[] getAnimalCareRoles() {
        return new EmployeeRole[]{KEEPER, VETERINARIAN, VET_TECHNICIAN, TRAINER, ENRICHMENT_OFFICER};
    }

    /**
     * Поиск роли по названию
     * @param Name название
     * @return соответствующая роль или OTHER если не найдена
     */
    public static EmployeeRole fromName(String Name) {
        for (EmployeeRole role : values()) {
            if (role.getName().equalsIgnoreCase(Name)) {
                return role;
            }
        }
        return OTHER;
    }

    @Override
    public String toString() {
        return Name + " (" + responsibilities + ")";
    }
}