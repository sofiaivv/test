import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class test {
    public static File notebook = new File("Notebook.txt");

    public static List<String> notes;
    static {
        try {
            notes = getNotes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static void printMenu() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("=========\n" +
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
                System.out.println("Введено что-то не то...");
                return;
        }
        scanner.close();
    }

    public static void read() throws IOException {
        System.out.println("Записи в записной книжке:");
        for (String note : notes) {
            System.out.println(note);
        }

        printMenu();
    };

    public static List<String> getNotes() throws IOException {
        List<String> notes = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("Notebook.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            notes.add(line);
        }
        reader.close();
        return notes;
    }

    public static void write() throws IOException {
        System.out.println("Введите новую запись:");
        Scanner scanner = new Scanner(System.in);
        String inputNote = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault());
        String date = dateFormat.format(new Date());
        String note = date + " | " + inputNote;
        notes.add(note);
        putNotes(notes);
        System.out.println("Записано.");

        printMenu();
    }

    public static void putNotes(List<String> notes) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Notebook.txt"));
        for (String note : notes) {
            writer.write(note);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
