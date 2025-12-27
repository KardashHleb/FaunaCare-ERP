package core.entities;

import core.enums.AnimalType;
import core.enums.HealthStatus;
import core.interfaces.Feedable;
import core.interfaces.HealthCheckable;
import core.interfaces.ExhibitionParticipant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий птицу в зоопарке.
 * Реализует интерфейсы Feedable, HealthCheckable и ExhibitionParticipant.
 * Следует принципам:
 * - LSP (Liskov Substitution) - может использоваться везде, где ожидается Animal
 * - ISP (Interface Segregation) - реализует только нужные интерфейсы
 */
public class Bird extends Animal implements Feedable, HealthCheckable, ExhibitionParticipant {

    // Поля для Feedable
    private String feedingSchedule;
    private boolean hungry;
    private String recommendedFood;
    private double dailyFoodRequirement;

    // Поля для HealthCheckable
    private LocalDate lastCheckupDate;
    private boolean healthy;
    private String lastVetName;
    private List<String> medicalHistory;

    // Поля для ExhibitionParticipant
    private int exhibitionCount;
    private List<String> exhibitionHistory;
    private boolean atExhibition;
    private boolean needsRest;
    private int trainingHours = 0;
    private int trainingLevel;
    /**
     * Конструктор птицы
     */
    public Bird(String id, String name, int age) {
        super(id, name, AnimalType.BIRD, age);
        this.dailyFoodRequirement = 0.2;
        // Инициализация полей Feedable
        this.feedingSchedule = "08:00, 12:00, 18:00";
        this.hungry = true;
        this.recommendedFood = "Зерно и семена";

        // Инициализация полей HealthCheckable
        this.healthy = true;
        this.medicalHistory = new ArrayList<>();

        // Инициализация полей ExhibitionParticipant
        this.exhibitionCount = 0;
        this.trainingLevel = 1;
        this.exhibitionHistory = new ArrayList<>();
        this.atExhibition = false;
        this.needsRest = false;
    }

    // ========== Animal методы ==========

    @Override
    public String makeSound() {
        return "Чирик-чирик!";
    }

    // ========== Feedable методы ==========

    @Override
    public void feed(String foodType, double amount) {
        System.out.println(getName() + " (птица) ест " + amount + "кг " + foodType);
        this.hungry = false;

        // Проверяем, подходит ли еда
        if (!foodType.toLowerCase().contains("зерн") &&
                !foodType.toLowerCase().contains("семен")) {
            System.out.println("Внимание! Птице лучше давать зерно или семена!");
        }
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
        return recommendedFood;
    }


    /**
     * Получить суточную норму пищи (в кг)
     */
    public double getDailyFoodRequirement() {
        return dailyFoodRequirement;
    }

    /**
     * Установить суточную норму пищи (в кг)
     */
    public void setDailyFoodRequirement(double kg) {
        this.dailyFoodRequirement = kg;
        System.out.println("Суточная норма пищи для " + getName() + " установлена: " + kg + "кг");
    }

    // ========== HealthCheckable методы ==========

    public void setMedicalHistory(String fullHistory) {
        // Очищаем текущую историю
        medicalHistory.clear();
        // Добавляем новую запись как одну большую запись
        medicalHistory.add(fullHistory);
    }
    @Override
    public void performHealthCheck(String vetName, LocalDate date, String notes) {
        this.lastCheckupDate = date;
        this.lastVetName = vetName;

        String record = "Осмотр от " + date + " ветеринаром " + vetName + ": " + notes;
        addMedicalRecord(record);

        // Простая логика определения здоровья
        if (notes.toLowerCase().contains("здоров") ||
                notes.toLowerCase().contains("норма") ||
                notes.toLowerCase().contains("хорош")) {
            setHealthy(true);
            System.out.println(getName() + " здоров после осмотра");
        } else if (notes.toLowerCase().contains("больн") ||
                notes.toLowerCase().contains("проблем") ||
                notes.toLowerCase().contains("лечен")) {
            setHealthy(false);
            System.out.println(getName() + " нуждается в лечении");
        }
    }

    @Override
    public LocalDate getLastCheckupDate() {
        return lastCheckupDate;
    }

    @Override
    public boolean isHealthy() {
        return healthy;
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
        // Птицам нужен осмотр раз в 6 месяцев
        return lastCheckupDate.isBefore(LocalDate.now().minusMonths(6));
    }

    @Override
    public String getMedicalHistory() {
        StringBuilder history = new StringBuilder();
        history.append("Медицинская история ").append(getName()).append(":\n");
        for (int i = 0; i < medicalHistory.size(); i++) {
            history.append(i + 1).append(". ").append(medicalHistory.get(i)).append("\n");
        }
        return history.toString();
    }

    @Override
    public void addMedicalRecord(String entry) {
        medicalHistory.add(entry);
    }

    // ========== ExhibitionParticipant методы ==========

    @Override
    public void prepareForExhibition() {
        System.out.println("Подготовка " + getName() + " к выставке:");
        System.out.println("1. Чистка перьев");
        System.out.println("2. Проверка вокализации");
        System.out.println("3. Тренировка команд");

        // Улучшаем навыки при подготовке
        train(2); // 2 часа тренировки
    }

    @Override
    public void participateInExhibition(String exhibitionName, int durationMinutes) {
        if (!isAvailableForExhibition()) {
            System.out.println(getName() + " не может участвовать в выставке!");
            return;
        }

        atExhibition = true;
        exhibitionCount++;
        exhibitionHistory.add(exhibitionName);

        System.out.println(getName() + " участвует в выставке '" + exhibitionName +
                "' (" + durationMinutes + " минут)");
        System.out.println("Демонстрирует: пение и трюки с перьями");

        // После длительной выставки нужен отдых
        if (durationMinutes > 60) {
            needsRest = true;
        }
    }

    @Override
    public void returnFromExhibition() {
        atExhibition = false;
        System.out.println(getName() + " вернулся с выставки");
        System.out.println("Проведены посленевые процедуры: покой, вода, легкий корм");
    }

    @Override
    public boolean isAvailableForExhibition() {
        // Для выставок доступны только:
        // 1. Здоровые птицы
        // 2. Не находящиеся на другой выставке
        // 3. Не нуждающиеся в отдыхе
        // 4. С минимальным уровнем тренировки 3
        return isHealthy() && !atExhibition && !needsRest && trainingLevel >= 3;
    }

    @Override
    public int getExhibitionCount() {
        return exhibitionCount;
    }

    @Override
    public int getTrainingLevel() {
        return trainingLevel;
    }

    @Override
    public void train(int hours) {
        this.trainingHours += hours;
        this.trainingLevel = this.trainingHours / 3;

    }

    public String getTrainingMessage(int hours) {
        train(hours); // вызываем тренировку
        return getName() + " тренировался " + hours + " часов\n" +
                "      Текущий уровень тренировки: " + this.trainingLevel;
    }

    @Override
    public String[] getExhibitionHistory() {
        return exhibitionHistory.toArray(new String[0]);
    }

    @Override
    public boolean needsRest() {
        return needsRest;
    }

    @Override
    public String getExhibitionRecommendations() {
        StringBuilder recommendations = new StringBuilder();
        recommendations.append("Рекомендации для ").append(getName()).append(":\n");

        if (trainingLevel < 3) {
            recommendations.append("- Требуется больше тренировок (текущий уровень: ").append(trainingLevel).append(")\n");
        }

        if (needsRest) {
            recommendations.append("- Требуется отдых после последней выставки\n");
        }

        if (exhibitionCount > 5) {
            recommendations.append("- Участвовал в ").append(exhibitionCount).append(" выставках, возможно устал\n");
        }

        if (recommendations.toString().equals("Рекомендации для " + getName() + ":\n")) {
            recommendations.append("- Готов к участию в выставках\n");
        }

        return recommendations.toString();
    }


    @Override
    public String toString() {
        return super.toString() +
                ", Выставок: " + exhibitionCount +
                ", Уровень тренировки: " + trainingLevel +
                ", Может участвовать в выставках: " + (isAvailableForExhibition() ? "Да" : "Нет");
    }
}