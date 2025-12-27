package core.example;

import core.entities.Animal;
import core.entities.Bird;
import core.entities.Employee;
import core.entities.Mammal;
import core.enums.EmployeeRole;

import java.util.List;

import static core.com.zoo.Database.getAnimals;
import static core.com.zoo.Database.getEmployees;

public class DataInitializer {

    public void initializeData(boolean showDetails) {
        if (showDetails) {
            System.out.println("\n📋 ИНИЦИАЛИЗАЦИЯ ДАННЫХ:");
            System.out.println("──────────────────────────────────────────────────────");
            System.out.println("1. Создание животных:");
        }

        // Очищаем статические списки из Datebase
        List<Animal> animals = getAnimals();
        List<Employee> employees = getEmployees();
        animals.clear();
        employees.clear();

        // СЛОН
        Mammal elephant = new Mammal("E001", "Большой слон Дамбо", 15);
        String elephantFoodMsg = elephant.setDailyFoodRequirement(150.0);
        String elephantPregnancyMsg = elephant.setPregnant(180);
        animals.add(elephant);
        // Медицинская история слона
        elephant.setMedicalHistory("""
            🩺 МЕДИЦИНСКАЯ КАРТА СЛОНА
            ==========================
            📋 Имя: Большой слон Дамбо
            🎂 Возраст: 15 лет
            
            📊 ИСТОРИЯ ОСМОТРОВ:
            --------------------
            1. 📅 10.01.2024 - Ежегодный осмотр
               👨‍⚕️ Ветеринар: Петрова М.В.
               📝 Заключение: Здоров, вес в норме
               💉 Вакцинация: Стандартный комплекс
            
            2. 📅 25.01.2024 - Проблемы с суставами
               👨‍⚕️ Ветеринар: Иванов А.П.
               💊 Лечение: Хондропротекторы, противовоспалительные
               🩺 Диагноз: Начальные признаки артрита
            
            3. 📅 15.02.2024 - Контрольный осмотр
               👨‍⚕️ Ветеринар: Сидоров К.Л.
               📝 Результат: Улучшение состояния суставов
               ✅ Рекомендация: Продолжать лечение
            
            🍎 ОСОБЫЕ УКАЗАНИЯ:
            • Контроль веса - не более 4500 кг
            • Ежедневные прогулки для суставов
            • Специальная подстилка в вольере
            
            ⚠️ ТЕКУЩЕЕ СОСТОЯНИЕ:
            • Суставы: Требует наблюдения
            • Аппетит: Хороший
            • Активность: Умеренная""");



        if (showDetails) {
            System.out.println("   🐘 " + elephant.getInfo());
            String[] elephantFoodLines = elephantFoodMsg.split("\n");
            String[] elephantPregnancyLines = elephantPregnancyMsg.split("\n");

            for (String line : elephantFoodLines) {
                System.out.println("     " + line);
            }
            for (String line : elephantPregnancyLines) {
                System.out.println("     " + line);
            }
        }

        // ЛЕВ
        Mammal lion = new Mammal("L001", "Толстый лев Симба", 5);
        String lionFoodMsg = lion.setDailyFoodRequirement(8.0);
        lion.setMedicalHistory("""
            🩺 МЕДИЦИНСКАЯ КАРТА ЛЬВА
            =========================
            📋 Имя: Толстый лев Симба
            🎂 Возраст: 5 лет
            
            📊 ИСТОРИЯ ОСМОТРОВ:
            --------------------
            1. 📅 05.01.2024 - Первичный осмотр
               👨‍⚕️ Ветеринар: Петрова М.В.
               📝 Заключение: Здоров, немного избыточный вес
               💉 Вакцинация: Против бешенства
            
            2. 📅 20.01.2024 - Проблемы с зубами
               👨‍⚕️ Ветеринар: Иванов А.П.
               🦷 Лечение: Удаление поврежденного клыка
               💊 Послеоперационный уход: Антибиотики, мягкая диета
            
            3. 📅 15.02.2024 - Контрольный осмотр
               👨‍⚕️ Ветеринар: Сидоров К.Л.
               📝 Результат: Рана зажила, аппетит восстановлен
               ✅ Рекомендация: Продолжать мягкую диету еще 1 неделю
            
            🍖 ДИЕТИЧЕСКИЕ ОГРАНИЧЕНИЯ:
            • Мягкая пища до 01.03.2024
            • Контроль порций для снижения веса
            • Добавка витаминов для заживления
            
            ⚠️ ТЕКУЩЕЕ СОСТОЯНИЕ:
            • Ротовая полость: Заживает
            • Вес: 190 кг (рекомендуется 180 кг)
            • Активность: Немного снижена""");

        animals.add(lion);

        if (showDetails) {
            System.out.println("   🦁 " + lion.getInfo());
            String[] lionFoodLines = lionFoodMsg.split("\n");
            for (String line : lionFoodLines) {
                System.out.println("     " + line);
            }
        }

        // ПОПУГАЙ
        Bird parrot = new Bird("P001", "Кеша", 3);
        parrot.setMedicalHistory("""
            🩺 МЕДИЦИНСКАЯ КАРТА ПОПУГАЯ
            ============================
            📋 Имя: Кеша
            🎂 Возраст: 3 года
            
            📊 ИСТОРИЯ ОСМОТРОВ:
            --------------------
            1. 📅 12.01.2024 - Первичный осмотр
               👨‍⚕️ Ветеринар: Петрова М.В.
               📝 Заключение: Здоров, перья в отличном состоянии
               💉 Вакцинация: Против птичьего гриппа
            
            2. 📅 01.02.2024 - Плановый контроль
               👨‍⚕️ Ветеринар: Иванов А.П.
               📝 Результат: Все показатели в норме
               💊 Профилактика: Обработка от паразитов
            
            3. 📅 20.02.2024 - Незначительная простуда
               👨‍⚕️ Ветеринар: Сидоров К.Л.
               🩺 Диагноз: Легкая респираторная инфекция
               💊 Лечение: Витамины, тепло, покой
            
            🍎 ОСОБЕННОСТИ УХОДА:
            • Температура в вольере: 22-25°C
            • Влажность: 60-70%
            • Специальный корм для попугаев
            • Ежедневные фрукты и овощи
            
            🎯 ТЕКУЩЕЕ СОСТОЯНИЕ:
            • Полностью выздоровел после простуды
            • Аппетит: Отличный
            • Пение: Активное
            • Перья: Блестящие, полные""");
        animals.add(parrot);

        if (showDetails) {
            System.out.println("   🦜 " + parrot.getInfo());
        }

        // ГОЛУБЬ
        Bird eagle = new Bird("E002", "Голубь", 7);
        String eagleTrainMsg = eagle.getTrainingMessage(10);
        eagle.setMedicalHistory("""
            🩺 МЕДИЦИНСКАЯ КАРТА ГОЛУБЯ
            ===========================
            📋 Имя: Голубь
            🎂 Возраст: 7 лет
            
            📊 ИСТОРИЯ ОСМОТРОВ:
            --------------------
            1. 📅 15.01.2024 - Плановый осмотр
               👨‍⚕️ Ветеринар: Петрова М.В.
               📝 Заключение: Здоров, возрастные изменения
               💉 Вакцинация: Стандартная для птиц
            
            2. 📅 05.02.2024 - Травма крыла
               👨‍⚕️ Ветеринар: Иванов А.П.
               🩹 Лечение: Фиксация крыла, противовоспалительные
               🩺 Диагноз: Легкое растяжение связок
            
            3. 📅 25.02.2024 - Контрольный осмотр
               👨‍⚕️ Ветеринар: Сидоров К.Л.
               📝 Результат: Крыло зажило, летные способности восстановлены
               ✅ Рекомендация: Постепенное увеличение нагрузок
            
            ⚠️ ОСОБЫЕ УКАЗАНИЯ:
            • Ограничение полетов до полного восстановления
            • Контроль веса - склонность к ожирению
            • Регулярная проверка когтей и клюва
            
            🎯 ТЕКУЩЕЕ СОСТОЯНИЕ:
            • Крыло: Полностью восстановлено
            • Вес: 0.45 кг (норма)
            • Активность: Хорошая
            • Готов к тренировкам""");
        animals.add(eagle);

        if (showDetails) {
            System.out.println("   🦜 " + eagle.getInfo());
            System.out.println("      " + eagleTrainMsg);
        }

        // Создание сотрудников
        if (showDetails) {
            System.out.println("\n2. Создание сотрудников:");
        }

        Employee emp1 = new Employee("EMP001", "Иванов Иван Иванович", EmployeeRole.KEEPER);
        employees.add(emp1);

        Employee emp2 = new Employee("EMP002", "Петрова Мария Сергеевна", EmployeeRole.VETERINARIAN);
        employees.add(emp2);

        Employee emp3 = new Employee("EMP003", "Сидоров Алексей Петрович", EmployeeRole.TRAINER);
        employees.add(emp3);

        if (showDetails) {
            System.out.println("   👨‍💼 " + emp1.getInfo());
            System.out.println("   👩‍⚕️ " + emp2.getInfo());
            System.out.println("   🎪 " + emp3.getInfo());

            System.out.println("──────────────────────────────────────────────────────");
            System.out.println("✅ Инициализация завершена: " + animals.size() + " животных, " +
                    employees.size() + " сотрудников");
        }
    }
}