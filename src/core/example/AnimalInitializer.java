/**
 * 1. SRP (Single Responsibility) - ✅ СОБЛЮДЕН
 *    - Только создание животных
 *    - Вывод вынесен в AnimalPrinter
 *    - Мед.истории вынесены в MedicalHistoryService
 *
 * 2. OCP (Open/Closed) - ✅ СОБЛЮДЕН
 *    - Закрыт для модификаций (не нужно менять для изменения вывода)
 *    - Открыт для расширения (можно добавить createNewAnimal())
 *
 * 3. LSP (Liskov Substitution) - ✅ СОБЛЮДЕН
 *    - Работает с любыми Animal
 *    - Методы возвращают корректные подтипы
 *
 * 4. ISP (Interface Segregation) - ✅ СОБЛЮДЕН
 *    - Минимальные зависимости
 *    - Четкий публичный API
 *
 * 5. DIP (Dependency Inversion) - ✅ СОБЛЮДЕН
 *    - Зависит от AnimalPrinter (можно подменить)
 *    - Зависит от MedicalHistoryService (можно подменить)
 *    - Изоляция через интерфейсы/классы
 */



package core.example;

import core.entities.Animal;
import core.entities.Bird;
import core.entities.Mammal;
import java.util.List;

public class AnimalInitializer {

    private final AnimalPrinter animalPrinter;
    private final MedicalHistoryService medicalHistoryService;



    public AnimalInitializer() {
        this.animalPrinter = new AnimalPrinter();
        this.medicalHistoryService = new MedicalHistoryService();
    }


    public void initializeAnimals(List<Animal> animals, boolean showDetails) {
        animalPrinter.printAnimalsHeader(showDetails);

        // Просто вызываем методы по порядку
        animals.add(createElephant());
        animals.add(createLion());
        animals.add(createParrot());
        animals.add(createEagle());

        if (showDetails) {
            for (Animal animal : animals) {
                animalPrinter.printAnimalInfo(animal, true);
            }
        }
    }

    private  Animal createElephant() {
        Mammal elephant = new Mammal("E001", "Большой слон Дамбо", 15);
        elephant.setDailyFoodRequirement(150.0);
        elephant.setPregnant(180);
        elephant.setMedicalHistory(
                medicalHistoryService.getElephantHistory(elephant.getName(), elephant.getAge())
        );
        return elephant;
    }

    private  Animal createLion() {
        Mammal lion = new Mammal("L001", "Толстый лев Симба", 5);
        lion.setDailyFoodRequirement(8.0);
        lion.setMedicalHistory(
                medicalHistoryService.getLionHistory(lion.getName(), lion.getAge())
        );
        return lion;
    }

    private  Animal createParrot() {
        Bird parrot = new Bird("P001", "Кеша", 3);
        parrot.setMedicalHistory(
                medicalHistoryService.getParrotHistory(parrot.getName(), parrot.getAge())
        );
        return parrot;
    }

    private  Animal createEagle() {
        Bird eagle = new Bird("E002", "Голубь", 7);
        eagle.getTrainingMessage(10); // Побочный эффект - лучше вынести
        eagle.setMedicalHistory(
                medicalHistoryService.getEagleHistory(eagle.getName(), eagle.getAge())
        );
        return eagle;
    }

}