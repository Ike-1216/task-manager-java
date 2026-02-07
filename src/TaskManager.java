import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    private static final String FILE_NAME = "tasks.txt";
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== タスク管理 ===");
            System.out.println("1: タスク追加");
            System.out.println("2: タスク一覧表示");
            System.out.println("3: タスク完了");
            System.out.println("4: 終了");
            System.out.print("選択してください: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    showTasks();
                    break;
                case 3:
                    completeTask(scanner);
                    break;
                case 4:
                    saveTasks();
                    System.out.println("終了します");
                    return;
                default:
                    System.out.println("無効な選択です");
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("タスク内容を入力: ");
        String task = scanner.nextLine();
        tasks.add("[ ] " + task);
        saveTasks();
    }

    private static void showTasks() {
        System.out.println("\n--- タスク一覧 ---");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }

    private static void completeTask(Scanner scanner) {
        showTasks();
        System.out.print("完了したタスク番号を入力: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, tasks.get(index).replace("[ ]", "[x]"));
            saveTasks();
        } else {
            System.out.println("無効な番号です");
        }
    }

    private static void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // 初回はファイルがなくてもOK
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
