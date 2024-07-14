package _01._12;

/*
А без interrupt слабо?
Разберись, как работает программа.
Реализуй метод ourInterruptMethod таким образом, чтобы он прерывал нить TestThread.
Исправь остальной код программы, если это необходимо. Нельзя использовать метод interrupt.


Requirements:
1. В классе Solution должен быть публичный статический метод ourInterruptMethod без параметров.
2. Метод run должен выводить надпись "he-he" каждые пол секунды, пока не будет вызван метод ourInterruptMethod.
3. Необходимо изменить условие цикла while в методе run.
4. Метод main должен создавать объект типа Thread передавая в конструктор объект типа TestThread.
5. Метод main должен вызывать метод start у объекта типа Thread.
6. Метод main должен вызывать метод ourInterruptMethod.*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new TestThread());
        t.start();
        Thread.sleep(3000);
        ourInterruptMethod();
    }

    public static void ourInterruptMethod() {
        TestThread.isStopped = true;
    }

    public static class TestThread implements Runnable {
        public static boolean isStopped = false;//флаг для остановки цикла

        //вся суть задачи-остановить цикл внутри run без interrupt
        public void run() {
            while (!isStopped) {
                try {
                    System.out.println("he-he");
                    Thread.sleep(500);
                    if (TestThread.isStopped) {
                        System.out.println("Stopped without interrupt");
                    }
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
