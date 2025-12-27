package core.interfaces;

import java.time.LocalDate;

/**
 * Интерфейс для объектов, которые проходят медицинские осмотры.
 * Следует принципу ISP (Interface Segregation) -
 * отвечает только за одну ответственность: медицинские проверки.
 * Реализуется классами животных, которые требуют медицинского наблюдения.
 */
public interface HealthCheckable {

    /**
     * Выполнить медицинский осмотр животного
     * @param vetName имя ветеринара, проводящего осмотр
     * @param date дата проведения осмотра
     * @param notes дополнительные заметки о состоянии здоровья
     */
    void performHealthCheck(String vetName, LocalDate date, String notes);

    /**
     * Получить дату последнего медицинского осмотра
     * @return дата последнего осмотра, или null если осмотров не было
     */
    LocalDate getLastCheckupDate();

    /**
     * Получить статус здоровья животного
     * @return true если животное здорово, false если есть проблемы
     */
    boolean isHealthy();

    /**
     * Установить статус здоровья животного
     * @param healthy true - здоров, false - болен
     */
    void setHealthy(boolean healthy);

    /**
     * Получить имя ветеринара, проводившего последний осмотр
     * @return имя ветеринара
     */
    String getLastVetName();

    /**
     * Проверить, требуется ли плановый осмотр
     * Животное должно проходить осмотр минимум раз в год
     * @return true если требуется плановый осмотр
     */
    boolean needsRoutineCheckup();

    /**
     * Получить историю медицинских осмотров в текстовом формате
     * @return строка с историей осмотров
     */
    String getMedicalHistory();
    void setMedicalHistory(String fullHistory) ;

    /**
     * Добавить запись в медицинскую карту
     * @param entry медицинская запись
     */
    void addMedicalRecord(String entry);
}