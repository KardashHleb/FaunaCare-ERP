package services.impl;

import core.interfaces.Feedable;

public class FeedingServiceImpl {
    // Простая реализация для демонстрации
    public void feedAnimal(Object animal, String food, int amount) {
        System.out.println("Сервис кормления: животное накормлено");
        if (animal instanceof Feedable) {
            Feedable feedable = (Feedable) animal;
            feedable.feed(food, amount);
            System.out.println(animal + " покормлен " + food +
                    " в количестве " + amount + "кг");
        }
    }
}