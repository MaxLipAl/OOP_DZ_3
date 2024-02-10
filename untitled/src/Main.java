import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (FileWriter fileWriter = new FileWriter("game_log.log")) {
            while (true) {
                AbstractGame game = null;

                // Запуск основного цикла выбора типа игры
                while (game == null || game.getGameStatus() != GameStatus.Start) {
                    System.out.println("Выбери тип игры:");
                    System.out.println("1. Цифры");
                    System.out.println("2. Русские буквы");
                    System.out.println("3. Латинские буквы");
                    System.out.println("4. Все сразу");
                    System.out.println("5. Выйти из игры");

                    // Обработка выбора пользователя
                    int choice = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                            if (choice >= 1 && choice <= 5) {
                                validInput = true;
                            } else {
                                System.out.println("Некорректный ввод. Введите число от 1 до 5.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Некорректный ввод. Введите число от 1 до 5.");
                        }
                    }

                    // Создание экземпляра игры в соответствии с выбором пользователя
                    if (choice == 5) {
                        System.out.println("Выход из игры.");
                        return;
                    }

                    switch (choice) {
                        case 1:
                            game = new NumberGame();
                            break;
                        case 2:
                            game = new RussianLetterGame();
                            break;
                        case 3:
                            game = new LatinLetterGame();
                            break;
                        case 4:
                            game = new MixedGame();
                            break;
                        default:
                            System.out.println("Некорректный выбор. Игра с цифрами по умолчанию.");
                            game = new NumberGame();
                            break;
                    }
                    game.start(4, 3); // Запускаем новую игру
                }

                // Основной цикл игры
                while (game.getGameStatus().equals(GameStatus.Start)) {
                    System.out.println("Введи значение из четырех символов: ");
                    String input = scanner.nextLine();
                    try {
                        Answer answer = game.inputValue(input);
                        System.out.println(answer);
                        fileWriter.write("Игрок ввел значение: " + input + ". Результат: " + answer.toString() + "\n");
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                        fileWriter.write("Ошибка ввода: " + e.getMessage() + "\n");
                    }

                    // Проверка статуса игры и запрос на повторную игру
                    if (game.getGameStatus().equals(GameStatus.Win) || game.getGameStatus().equals(GameStatus.Loose)) {
                        System.out.println("Хочешь сыграть еще? (Да/Нет): ");
                        String playAgain = scanner.nextLine().toLowerCase();
                        if (playAgain.equals("да")) {
                            game.start(4, 3); // Начинаем новую игру
                        } else {
                            break; // Выход из цикла игры
                        }
                    }
                }

                // Обработка завершения игры
                switch (game.getGameStatus()) {
                    case Win:
                        System.out.println("Победа!!!");
                        break;
                    case Loose:
                        System.out.println("Проиграл :(");
                        break;
                    default:
                        System.out.println("Неизвестный статус");
                }

                // Проверка желания продолжить игру
                System.out.println("Хочешь сыграть еще раз с другим типом игры? (Да/Нет): ");
                String playAgain = scanner.nextLine().toLowerCase();
                if (!playAgain.equals("да")) {
                    break; // Выход из бесконечного цикла
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}