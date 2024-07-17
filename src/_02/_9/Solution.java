package _02._9;

/*
CRUD
CRUD - Create, Read, Update, Delete.

Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-r id
-u id name sex bd
-d id

Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-r - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
-u - обновляет данные человека с данным id
-d - производит логическое удаление человека с id, заменяет все его данные на null

id соответствует индексу в списке.
Все люди должны храниться в allPeople.
Используй Locale.ENGLISH в качестве второго параметра для SimpleDateFormat.

Пример параметров:
-c Миронов м 15/04/1990

Пример вывода для параметра -r:
Миронов м 15-Apr-1990

Если программа запущена с параметрами, то они попадают в массив args (аргумент метода main - String[] args).
Например, при запуске программы c параметрами:
-c name sex bd
получим в методе main
args[0] = "-c"
args[1] = "name"
args[2] = "sex"
args[3] = "bd"
Для запуска кода с параметрами в IDE IntellijIDEA нужно их прописать в поле
Program arguments в меню Run -> Edit Configurations.


Requirements:
1. Класс Solution должен содержать public static поле allPeople типа List<Person>.
2. Класс Solution должен содержать статический блок, в котором добавляются два человека в список allPeople.
3. При запуске программы с параметром -с программа должна добавлять человека с заданными параметрами
в конец списка allPeople, и выводить id (index) на экран.
4. При запуске программы с параметром -r программа должна выводить на экран данные о человеке
с заданным id по формату указанному в задании.
5. При запуске программы с параметром -u программа должна обновлять данные человека с заданным id в списке allPeople.
6. При запуске программы с параметром -d программа должна логически удалять человека с заданным id в списке allPeople.
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();


    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {

        DateFormat dateFormatIn = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);//для входящих
        DateFormat dateFormatOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);//для вывода
//будет всего 4 элемента. нужно в каждый элемент занести проверку
        //-c - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
        if (args[0].contains("-c")) {
            //парсим сразу дату, чтобы потом передать в метод
            Date date = null;//пришлось занулить дату.из трай кэтч её не видно
            try {
                date = dateFormatIn.parse(args[3]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            if (args[2].contains("м")) {
                Person male = Person.createMale(args[1], date);
                allPeople.add(male);
                System.out.println("Person's ID: " + allPeople.indexOf(male));
            } else {
                Person female = Person.createFemale(args[1], date);
                allPeople.add(female);
                System.out.println("Person's ID: " + allPeople.indexOf(female));
            }
        }

        //-r - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
        if (args[0].contains("-r")) {
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            StringBuilder sb = new StringBuilder();
            sb.append(person.getName()).append(" ");
            if (person.getSex() == Sex.MALE) {
                sb.append("м").append(" ");
            } else {
                sb.append("ж").append(" ");
            }
            sb.append(dateFormatOut.format(person.getBirthDate()));
            System.out.println(sb);
        }

        //-u - обновляет данные человека с данным id
        if (args[0].contains("-u")) {
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            person.setName(args[2]);

            Date date = null;
            try {
                date = dateFormatIn.parse(args[4]);
            } catch (ParseException e) {
                e.getMessage();
            }
            person.setBirthDate(date);

            //update sex
            if (args[3].startsWith("м"))
                person.setSex(Sex.MALE);
            else
                person.setSex(Sex.FEMALE);
        }

        //-d - производит логическое удаление человека с id, заменяет все его данные на null
        /*В самом простом виде soft delete — это способ удаления данных в приложении без их удаления из базы данных.*/
        if (args[0].contains("-d")) {
            int index = Integer.parseInt(args[1]);
            Person person = allPeople.get(index);

            person.setName(null);
            person.setSex(null);
            person.setBirthDate(null);
        }

        System.out.println("***********************");
        for (Person person : allPeople) {
            System.out.println(person);
        }
    }
}

