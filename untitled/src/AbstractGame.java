import java.util.List;
import java.util.Random;

// Абстрактный класс, реализующий основную логику игры
public abstract class AbstractGame implements Game {

    // Абстрактный метод для генерации списка символов (алфавита) для игры
    abstract List<String> generateCharList();

    // Свойство, содержащее загаданное слово
    public String word;

    // Количество попыток
    Integer tryCount;

    // Статус игры
    GameStatus gameStatus = GameStatus.Init;

    // Метод для начала игры
    @Override
    public void start(Integer sizeWord, Integer tryCount) {
        // Генерация слова
        word = generateWord(sizeWord);
        // Установка количества попыток
        this.tryCount = tryCount;
        // Установка статуса игры на "Начало"
        gameStatus = GameStatus.Start;
    }

    // Метод для генерации слова
    private String generateWord(Integer sizeWord) {
        // Генерация алфавита
        List<String> alphavit = generateCharList();
        // Создание объекта для генерации случайных чисел
        Random rnd = new Random();
        // Построение слова
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sizeWord; i++) {
            // Выбор случайного символа из алфавита
            int randomIndex = rnd.nextInt(alphavit.size());
            result.append(alphavit.get(randomIndex));
            // Удаление использованного символа из алфавита
            alphavit.remove(randomIndex);
        }
        return result.toString();
    }

    // Метод для обработки ввода пользователя и возврата результата
    @Override
    public Answer inputValue(String value) {
        // Проверка, что игра начата
        if (!getGameStatus().equals(GameStatus.Start)) {
            throw new RuntimeException("Игра не запущена");
        }

        // Счетчики быков и коров
        int cowCounter = 0;
        int bullCounter = 0;

        // Проверка каждой буквы в слове
        for (int i = 0; i < word.length(); i++) {
            char guessedChar = value.charAt(i);
            char targetChar = word.charAt(i);

            // Если символы совпадают, увеличиваем счетчики быков и коров
            if (guessedChar == targetChar) {
                cowCounter++;
                bullCounter++;
            } else if (word.contains(String.valueOf(guessedChar))) {
                cowCounter++;
            }
        }

        // Уменьшаем количество попыток
        tryCount--;

        // Если попытки закончились, устанавливаем статус игры на "Проигрыш"
        if (tryCount == 0) {
            gameStatus = GameStatus.Loose;
        }

        // Если отгадано все слово, устанавливаем статус игры на "Победа" и возвращаем результат
        if (bullCounter == word.length()) {
            gameStatus = GameStatus.Win;
            return new Answer(cowCounter, bullCounter, tryCount);
        }

        // Возвращаем результат
        return new Answer(cowCounter, bullCounter, tryCount);
    }

    // Метод для получения статуса игры
    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }
}