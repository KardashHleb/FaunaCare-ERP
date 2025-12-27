package core.com.zoo;

import core.entities.Animal;
import core.example.DataInitializer;
import core.interfaces.ScheduleService;

import java.util.Scanner;

import static core.com.zoo.AnimalShelterDemonstrator.demonstrateAnimalCare;
import static core.com.zoo.ExhibitionDemonstrator.demonstrateExhibitions;
import static core.com.zoo.FeedingServiceDemonstrator.demonstrateFeedingService;
import static core.com.zoo.ZooReportGenerator.demonstrateReports;


public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean showDetails;
    private DataInitializer dataInitializer;
    private MedicalCheckupDemonstrator medicalDemonstrator;
    private ScheduleService<Animal> animalScheduleService = new AnimalScheduleService();
    private ScheduleMenuManager scheduleMenuManager;

    private static boolean askForDetailsOnStart() {

        System.out.print("=== Добро пожаловать в Zoo Management System ===\n");
        System.out.print("Показать детали инициализации при загрузке данных? (да/нет): ");
        String answer = scanner.nextLine().toLowerCase();
        return answer.equals("да") || answer.equals("д") ||
                answer.equals("yes") || answer.equals("y");
    }

    public Menu() {
        this.dataInitializer = new DataInitializer();
        this.showDetails = askForDetailsOnStart();
        this.medicalDemonstrator = new MedicalCheckupDemonstrator();
        this.scheduleMenuManager = new ScheduleMenuManager(scanner, animalScheduleService);

    }

    public void run() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║      ERP-СИСТЕМА МОСКОВСКОГО ЗООПАРКА                        ║");
        System.out.println("║      Автоматизация ухода за животными                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    dataInitializer.initializeData(showDetails);
                    demonstrateAnimalCare(); //ок
                    break;
                case 2:
                    dataInitializer.initializeData(showDetails);
                    showMedicalCheckups(); //ок
                    break;
                case 3:
                    dataInitializer.initializeData(showDetails);
                    demonstrateExhibitions();
                case 4:
                    dataInitializer.initializeData(showDetails);
                    demonstrateFeedingService();
                    break;
                case 5:
                    dataInitializer.initializeData(showDetails);
                    demonstrateReports();
                    break;
                case 6:
                    SOLIDPrinciples.demonstrateAll(Database.getAnimals());
                    break;
                case 7:
                    changeSettings();
                    break;
                case 8:
                    dataInitializer.initializeData(showDetails);
                    scheduleMenuManager.demonstrateScheduleManagement();
                    break;
                case 0:
                    exit = true;
                    System.out.println("\n=== Выход из системы ===");
                    break;
                default:
                    System.out.println("\n=== Неверный выбор. Попробуйте снова. ===");
                    break;
            }

            if (!exit) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("СИСТЕМА УПРАВЛЕНИЯ ЗООПАРКОМ");
        System.out.println("=".repeat(50));
        System.out.println("1. Уход за животными");
        System.out.println("2. Медосмотры");
        System.out.println("3. Выставки и мероприятия");
        System.out.println("4. Кормление животных");
        System.out.println("5. Отчеты и статистика");
        System.out.println("6. Демонстрация SOLID принципов");
        System.out.println("7. Переинициализировать данные");
        System.out.println("8. Показать расписание");
        System.out.println("0. Выход");
        System.out.println("=".repeat(50));
        System.out.print("Выберите пункт меню: ");
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void changeSettings() {
        System.out.print("\nПоказать детали инициализации? (да/нет): ");
        String answer = scanner.nextLine().toLowerCase();
        showDetails = answer.equals("да") || answer.equals("д") ||
                answer.equals("yes") || answer.equals("y");
        System.out.println("\n=== Настройка сохранена ===");
    }

    public void showMedicalCheckups() {
        System.out.println("=== Демонстрация медицинских осмотров ===");
        medicalDemonstrator.demonstrateMedicalCheckups();
    }

}