package _02._3;

/*
Сад-огород
1. Создай метод public void addFruit(int index, String fruit) - который добавляет параметр fruit в
лист fruits на позицию index.
2. Создай метод public void removeFruit(int index) - который удаляет из fruits элемент с индексом index.
3. Создай метод public void addVegetable(int index, String vegetable) - который добавляет параметр vegetable
в лист vegetables на позицию index.
4. Создай метод public void removeVegetable(int index) - который удаляет из vegetables элемент с индексом index.
5. Класс Garden будет использоваться нитями. Поэтому сделай так, чтобы все методы блокировали мьютекс this.
6. Реализуй это минимальным количеством кода.


Requirements:
1. Класс Garden должен содержать метод public void addFruit(int index, String fruit).
2. Класс Garden должен содержать метод public void removeFruit(int index).
3. Класс Garden должен содержать метод public void addVegetable(int index, String vegetable).
4. Класс Garden должен содержать метод public void removeVegetable(int index).+
5. Метод addFruit(int index, String fruit) должен добавлять параметр fruit в лист fruits на позицию index.
6. Метод removeFruit(int index) должен удалять из fruits элемент с индексом index.
7. Метод addVegetable(int index, String vegetable) должен добавлять параметр vegetable
в лист vegetables на позицию index.
8. Метод removeVegetable(int index) должен удалять из vegetables элемент с индексом index.
9. Все методы класса Garden должны блокировать мьютекс this (быть синхронизированы).*/

import java.util.ArrayList;
import java.util.List;

public class Solution {


    public static void main(String[] args) {

    }

    public static class Garden {

        public final List<String> fruits = new ArrayList<String>();
        public final List<String> vegetables = new ArrayList<String>();

        //чтобы выполнить пункт 5,6 не придумал ничего кроме как все методы синхронизировать.
        public synchronized void addFruit(int index, String fruit) {
            fruits.add(index, fruit);
        }

        public synchronized void removeFruit(int index) {
            fruits.remove(index);
        }

        public synchronized void addVegetable(int index, String vegetable) {
            vegetables.add(index, vegetable);
        }

        public synchronized void removeVegetable(int index) {
            vegetables.remove(index);
        }
    }
}
/*Многие из вас скажут: «И что?». А то, что в Java нету понятия mutex.
Не найдете вы его в спецификации языка, не найдете вы его и в стандартной библиотеке Java.
Думается мне кто-то притащил это понятие в Java из С++, там действительно есть класс mutex, так же есть в Go.
Максимум что можно притащить за уши в Java, так это понятие a mutual-exclusion lock.
Упоминается в спецификации языка Java в главе 14.19. The synchronized Statement:

A synchronized statement acquires a mutual-exclusion lock (§17.1) on behalf of the executing thread, executes a block,
then releases the lock. While the executing thread owns the lock, no other thread may acquire the lock.

Базовый механизм общения между потоками в Java это синхронизация, которая основана на использования
специального встроенного в объекты замка, называемого монитором, оно же intrinsic lock, оно же monitor lock.
Никаких mutex-ов нигде не упоминается.*/

