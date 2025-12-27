package core.interfaces;

import java.util.List;

/**
 * Интерфейс для управления расписанием деятельности зоопарка.
 * Следует принципу ISP (Interface Segregation) -
 * отвечает только за одну ответственность: управление расписанием.
 *
 * <p>Может быть реализован сервисами, которые управляют расписанием кормлений,
 * медосмотров, выставок и других мероприятий.</p>
 *
 * @param <T> тип объекта, для которого составляется расписание
 *            (животное, сотрудник, вольер и т.д.)
 */
public interface ScheduleService<T> {

    /**
     * Запланировать мероприятие для объекта.
     *
     * @param entity объект (животное, сотрудник, вольер)
     * @param activityType тип мероприятия
     * @param time время проведения в формате HH:mm
     * @param date дата проведения в формате YYYY-MM-DD
     * @param location место проведения
     * @return уникальный идентификатор созданного мероприятия
     * @throws IllegalArgumentException если параметры некорректны
     */
    String scheduleActivity(T entity,
                            String activityType,
                            String time,
                            String date,
                            String location);

    /**
     * Получить дневное расписание для объекта.
     *
     * @param entity объект, для которого запрашивается расписание
     * @param date дата в формате YYYY-MM-DD
     * @return список запланированных мероприятий на указанную дату
     */
    List<String> getDailySchedule(T entity, String date);

    /**
     * Получить недельное расписание для объекта.
     *
     * @param entity объект, для которого запрашивается расписание
     * @param startDate начальная дата недели в формате YYYY-MM-DD
     * @return список запланированных мероприятий на неделю
     */
    List<String> getWeeklySchedule(T entity, String startDate);

    /**
     * Получить следующее запланированное мероприятие для объекта.
     *
     * @param entity объект, для которого запрашивается мероприятие
     * @return описание следующего мероприятия или null, если мероприятий нет
     */
    String getNextActivity(T entity);

    /**
     * Добавить повторяющееся мероприятие.
     *
     * @param entity объект
     * @param activityType тип мероприятия
     * @param time время в формате HH:mm
     * @param frequency частота повторения ("daily", "weekly", "monthly")
     * @param days дни недели для еженедельного повторения (например, "Mon,Wed,Fri")
     * @return уникальный идентификатор созданного повторяющегося мероприятия
     */
    String addRecurringActivity(T entity,
                                String activityType,
                                String time,
                                String frequency,
                                String days);
}