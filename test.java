import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class test {
    public static File notebook = new File("Notebook.txt");
    List<String> notes = getNotes();

    public static void main(String args[]) throws IOException {

        // проверка, существует ли файл; его создание, если нет
        if (notebook.exists()) {
            System.out.println("Файл существует");
        }
        else {
            System.out.println("Файл не существует. Создание...");
            notebook.createNewFile();
            System.out.println("Файл создан");
        }

        // вывод меню
        printMenu();
    }

    public static void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========\n" +
                "Введите команду: \n" +
                "#read - вывод всех записей,\n" +
                "#write - создание новой записи,\n" +
                "#exit - выход\n" +
                "=========\n"
        );
        String command = scanner.nextLine();
        switch (command) {
            case "#read":
                read();
                return;
            case "#write":
                write();
                return;
            case "#exit":
                break;
            default:
                System.out.println("Введено что-то не то");
                return;
        }
        scanner.close();
    }

    public static void read() {
        System.out.println("# read");


        printMenu();
    };

    public List<String> getNotes() {
        return notes;
    }

    public static void write() {
        System.out.println("Введите новую запись:\n");
        Scanner scanner = new Scanner(System.in);
        String inputNote = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault());
        String date = dateFormat.format(new Date());
        String note = date + " " + inputNote;
        System.out.println(note); // заметка создана, выводится



        printMenu();
    }
}
