// Класс, представляющий результат попытки угадать слово
public class Answer {
    private int cowCounter; // Счетчик коров (количество правильных букв, но на неправильных позициях)
    private int bullCounter; // Счетчик быков (количество правильных букв на правильных позициях)
    private Integer tryCount; // Количество оставшихся попыток

    // Переопределенный метод toString для представления результатов в виде строки
    @Override
    public String toString() {
        return cowCounter + " коровы, " + bullCounter + " быка, осталось попыток: " + tryCount;
    }

    // Конструктор класса, принимающий количество коров, быков и оставшихся попыток
    public Answer(int cowCounter, int bullCounter, Integer tryCount) {
        this.cowCounter = cowCounter;
        this.bullCounter = bullCounter;
        this.tryCount = tryCount;
    }
}