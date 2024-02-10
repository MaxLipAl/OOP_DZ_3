// Интерфейс, представляющий игру
public interface Game {
    // Метод для начала игры с указанием размера слова и количества попыток
    void start(Integer sizeWord, Integer tryCount);

    // Метод для обработки ввода пользователя и возвращения результата
    Answer inputValue(String value);

    // Метод для получения текущего состояния игры
    GameStatus getGameStatus();
}
