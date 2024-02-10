import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LatinLetterGame extends AbstractGame {

    // Метод для генерации списка латинских букв
    @Override
    List<String> generateCharList() {
        // Создание списка латинских букв
        return new ArrayList<>(Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        ));
    }
}