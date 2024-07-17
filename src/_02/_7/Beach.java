package _02._7;

/*
Comparable
Реализуй интерфейс Comparable<Beach> в классе Beach.
Пляжи(Beach) будут использоваться нитями, поэтому позаботься, чтобы все методы были синхронизированы.
Реализуй метод compareTo так, чтобы при сравнении двух пляжей он выдавал:
– положительное число, если первый пляж лучше;
– отрицательное число, если второй пляж лучше;
– ноль, если пляжи одинаковые.
Сравни каждый критерий по отдельности, чтобы победителем был пляж с лучшими показателями по большинству критериев.
Учти при сравнении, чем меньше расстояние к пляжу (distance), тем пляж лучше.


Requirements:
1. Класс Beach должен содержать три поля: String name, float distance, int quality.
2. Класс Beach должен реализовывать интерфейс Comparable<Beach>.
3. Метод compareTo класса Beach должен учитывать качество пляжа (quality) и дистанцию (distance).
4. Все методы класса Beach, кроме метода main, должны быть синхронизированы.
*/

import static java.lang.Float.compare;

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public int compareTo(Beach o) {
        // Сначала сравниваем по расстоянию (меньше расстояние - лучше)
        int distanceComparison = compare(this.distance, o.distance);
        if (distanceComparison != 0) {
            return distanceComparison;
        }

        // Если расстояние одинаково, сравниваем по качеству (больше качество - лучше)
        return Integer.compare(o.quality, this.quality);
    }

    public static void main(String[] args) {
        Beach beach1 = new Beach("Sunny Beach", 5.5f, 8);
        Beach beach2 = new Beach("Paradise Beach", 1.8f, 9);

        int comparisonResult = beach1.compareTo(beach2);
        if (comparisonResult > 0) {
            System.out.println(beach1.getName() + " лучше, чем " + beach2.getName());
        } else if (comparisonResult < 0) {
            System.out.println(beach2.getName() + " лучше, чем " + beach1.getName());
        } else {
            System.out.println("Пляжи одинаковы");
        }
    }
}

