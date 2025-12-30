/**
 * Анализ с точки зрения SOLID:
 *
 * 1. SRP (Single Responsibility)  ✅ СОБЛЮДЕН
 *    - Класс отвечает за представление млекопитающего как животного
 *
 * 2. OCP (Open/Closed) - ✅ СОБЛЮДЕН
 *    - Закрыт для модификаций базовой структуры Mammal
 *    - Открыт для расширения через наследование (extends Animal)
 *
 * 3. LSP (Liskov Substitution) - ✅ СОБЛЮДЕН
 *    - Корректно расширяет Animal
 *    - Корректно реализует интерфейсы Feedable и HealthCheckable
 *    - Может использоваться везде, где ожидается Animal, Feedable или HealthCheckable
 *
 * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *    - Реализует только необходимые интерфейсы (Feedable, HealthCheckable)
 *    - Не реализует ненужный интерфейс ExhibitionParticipant
 *    - Правильно разделяет обязанности между интерфейсами
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Зависит только от абстракций (интерфейсы, enum)
 *    - Не зависит от конкретных реализаций других классов
 *    - Использует интерфейсы для декомпозиции функциональности
 */
package core.entities;

import core.enums.AnimalType;
import core.enums.HealthStatus;
import core.interfaces.Feedable;
import core.interfaces.HealthCheckable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Mammal extends Animal implements Feedable, HealthCheckable {

    // Поля для Feedable
    private String feedingSchedule;
    private boolean hungry;
    private String recommendedFood;
    private double dailyFoodRequirement; // в кг

    // Поля для HealthCheckable
    private LocalDate lastCheckupDate;
    private boolean healthy;
    private String lastVetName;
    private List<String> medicalHistory;
    private boolean needsVaccination;

    // Специфичные поля для млекопитающих
    private boolean hasFur;
    private String furType;
    private int gestationPeriod; // период беременности в днях
    private boolean isPregnant;

    /**
     * Конструктор млекопитающего
     */
    public Mammal(String id, String name, int age) {
        super(id, name, AnimalType.MAMMAL, age);

        // Инициализация полей Feedable
        this.feedingSchedule = "09:00, 17:00";
        this.hungry = true;
        this.recommendedFood = "Специализированный корм";
        this.dailyFoodRequirement = 5.0; // 5 кг по умолчанию

        // Инициализация полей HealthCheckable
        this.healthy = true;
        this.medicalHistory = new ArrayList<>();
        this.needsVaccination = true;

        // Инициализация специфичных полей
        this.hasFur = true;
        this.furType = "густая";
        this.gestationPeriod = 0;
        this.isPregnant = false;
    }

    // ========== Animal методы ==========
    public void setMedicalHistory(String fullHistory) {
        // Очищаем текущую историю
        medicalHistory.clear();
        // Добавляем новую запись как одну большую запись
        medicalHistory.add(fullHistory);
    }
    @Override
    public String makeSound() {
        return "Рррр! (звук млекопитающего)";
    }

    // ========== Feedable методы ==========


    @Override
    public void feed(String foodType, double amount) {  // amount уже в кг
        System.out.println(getName() + " (млекопитающее) ест " + amount + "кг " + foodType);

        // Проверяем, достаточно ли пищи
        if (amount < dailyFoodRequirement * 0.8) {
            System.out.println("Внимание! Количество пищи меньше рекомендуемой нормы!");
        } else if (amount > dailyFoodRequirement * 1.2) {
            System.out.println("Внимание! Слишком много пищи для одного кормления!");
        }

        this.hungry = false;
    }


    @Override
    public String getFeedingSchedule() {
        return feedingSchedule;
    }

    @Override
    public void setFeedingSchedule(String schedule) {
        this.feedingSchedule = schedule;
        System.out.println("Расписание кормления " + getName() + " изменено на: " + schedule);
    }

    @Override
    public boolean isHungry() {
        return hungry;
    }

    @Override
    public String getRecommendedFoodType() {
        return recommendedFood + " (требуется: " + dailyFoodRequirement + "кг/день)";
    }

    // Метод для установки суточной нормы пищи
    public String setDailyFoodRequirement(double kg) {
        this.dailyFoodRequirement = kg;
        return "Суточная норма пищи для " + getName() + " установлена: " + kg + "кг";
    }
    public double getDailyFoodRequirement() {
        return dailyFoodRequirement;
    }

    // ========== HealthCheckable методы ==========

    @Override
    public void performHealthCheck(String vetName, LocalDate date, String notes) {
        this.lastCheckupDate = date;
        this.lastVetName = vetName;

        String record = "Осмотр млекопитающего от " + date +
                " ветеринаром " + vetName + ": " + notes;
        addMedicalRecord(record);

        // Сложная логика для млекопитающих
        if (notes.toLowerCase().contains("здоров") ||
                notes.toLowerCase().contains("отличн") ||
                notes.toLowerCase().contains("норма")) {
            setHealthy(true);
            needsVaccination = false;
            System.out.println(getName() + " полностью здоров");
        } else if (notes.toLowerCase().contains("вакцин") ||
                notes.toLowerCase().contains("привив")) {
            setHealthy(true);
            needsVaccination = true;
            System.out.println(getName() + " требует вакцинации");
        } else {
            setHealthy(false);
            System.out.println(getName() + " нуждается в лечении");
        }

        // Особые проверки для беременных млекопитающих
        if (isPregnant) {
            System.out.println("Проведен специальный осмотр беременного животного");
            addMedicalRecord("Осмотр беременности: состояние нормальное");
        }
    }

    @Override
    public LocalDate getLastCheckupDate() {
        return lastCheckupDate;
    }

    @Override
    public boolean isHealthy() {
        return healthy && !needsVaccination;
    }

    @Override
    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
        setHealthStatus(healthy ? HealthStatus.HEALTHY : HealthStatus.SICK);
    }

    @Override
    public String getLastVetName() {
        return lastVetName;
    }

    @Override
    public boolean needsRoutineCheckup() {
        if (lastCheckupDate == null) return true;

        // Млекопитающим нужен осмотр раз в 3 месяца
        // Беременным - раз в месяц
        if (isPregnant) {
            return lastCheckupDate.isBefore(LocalDate.now().minusMonths(1));
        } else {
            return lastCheckupDate.isBefore(LocalDate.now().minusMonths(3));
        }
    }

    @Override
    public String getMedicalHistory() {
        StringBuilder history = new StringBuilder();
        history.append("Медицинская история млекопитающего ").append(getName()).append(":\n");

        if (isPregnant) {
            history.append("СТАТУС: БЕРЕМЕННА, срок: ").append(gestationPeriod).append(" дней\n");
        }

        for (int i = 0; i < medicalHistory.size(); i++) {
            history.append(i + 1).append(". ").append(medicalHistory.get(i)).append("\n");
        }

        if (needsVaccination) {
            history.append("ТРЕБУЕТСЯ: ВАКЦИНАЦИЯ\n");
        }

        return history.toString();
    }

    @Override
    public void addMedicalRecord(String entry) {
        medicalHistory.add(entry);
    }

    // ========== Специфичные методы для млекопитающих ==========

    /**
     * Установить беременность
     */
    public String setPregnant(int gestationPeriodDays) {
        this.isPregnant = true;
        this.gestationPeriod = gestationPeriodDays;

        // Увеличиваем норму питания для беременных
        this.dailyFoodRequirement *= 1.5;

        // Возвращаем строку вместо вывода
        return getName() + " беременна, срок: " + gestationPeriodDays + " дней\n" +
                "Суточная норма пищи увеличена до: " + dailyFoodRequirement + "кг";
    }

     /**
     * Родить детенышей
     */
    public void giveBirth() {
        if (isPregnant && gestationPeriod > 0) {
            System.out.println(getName() + " родила детенышей!");
            this.isPregnant = false;
            this.gestationPeriod = 0;
            this.dailyFoodRequirement /= 1.5; // возвращаем обычную норму
        } else {
            System.out.println(getName() + " не беременна");
        }
    }

    /**
     * Уход за шерстью
     */
    public void groomFur() {
        if (hasFur) {
            System.out.println("Проведен уход за шерстью " + getName() +
                    " (тип: " + furType + ")");
        } else {
            System.out.println(getName() + " не имеет шерсти для ухода");
        }
    }

     /**
     * Проверить температуру тела (специфично для млекопитающих)
     */
    public void checkBodyTemperature() {
        double normalTemp = 37.5; // нормальная температура для млекопитающих
        double currentTemp = normalTemp + (isHealthy() ? 0 : 2.5);

        System.out.println("Температура тела " + getName() + ": " +
                currentTemp + "°C " +
                (Math.abs(currentTemp - normalTemp) > 1 ? "(отклонение!)" : "(норма)"));
    }

    /**
     * Выполнить вакцинацию
     */
    public void vaccinate(String vaccineType) {
        if (needsVaccination) {
            System.out.println(getName() + " вакцинирована: " + vaccineType);
            needsVaccination = false;
            addMedicalRecord("Вакцинация: " + vaccineType + " от " + LocalDate.now());
        } else {
            System.out.println(getName() + " не требует вакцинации");
        }
    }

    @Override
    public String toString() {
        String baseInfo = super.toString();
        String pregnancyInfo = isPregnant ? ", Беременна: да (" + gestationPeriod + " дней)" : "";
        String vaccinationInfo = needsVaccination ? ", Требует вакцинации" : "";

        return baseInfo + pregnancyInfo + vaccinationInfo +
                ", Ежедневная норма пищи: " + dailyFoodRequirement + "кг";
    }

     /**
     * Специальный метод для кормления детенышей (только для млекопитающих)
     */
    public void feedOffspring() {
        System.out.println(getName() + " кормит своих детенышей молоком");
    }
}