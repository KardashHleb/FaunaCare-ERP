package core.com.zoo;

import core.entities.Animal;
import core.entities.Employee;
import services.impl.FeedingServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Database {

        private static List<Animal> animals = new ArrayList<>();
        private static List<Employee> employees = new ArrayList<>();
        private static FeedingServiceImpl feedingService = new FeedingServiceImpl();


        public static List<Animal> getAnimals() {
            return animals;
        }

        public static List<Employee> getEmployees() {
            return employees;
        }

        public static FeedingServiceImpl getFeedingService() {
            return feedingService;
        }



        // Для совместимости с существующим кодом
        public static void addAnimal(Animal animal) {
            animals.add(animal);
        }

        public static void addEmployee(Employee employee) {
            employees.add(employee);
        }
    }

