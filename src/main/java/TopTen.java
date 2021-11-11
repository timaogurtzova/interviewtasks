import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Вывести топ-10 по частоте встречаемости слов в строках
public class TopTen {
    public static void main(String[] args) {
        String[] strings = {
                "раз два три четыре пять",
                "раз два три десять пять",
                "раз два три четыре миллион",
                "два два десять",
                "о о о о о о о о о о о о о о о о о о о о о"
        };

        /*
        Каждую строку делим по " ".
        Массив массивов разворачиваем и сохраняем все слова из всех строк в List
         */
        List<String> allWordsInAllLine = Arrays.stream(strings).
                map(string -> string.split(" ")) //"раз два три" ->["раз", "два", "три"]
                .flatMap(arrayString -> Arrays.stream(arrayString)).collect(Collectors.toList());

        /*
        Подсчитываем, сколько раз встречалось слово.
        Сортируем entry по value.
        Ограничиваем 10
        Ключи записываем в лист, чтобы сохранить порядок
         */
        List<String> resultTop10 = allWordsInAllLine.stream()
                .collect(Collectors.toMap(key -> key, value -> 1, Integer::sum))
                .entrySet().stream()
                .sorted((entry1, entry2) -> {
                    int res = entry1.getValue().compareTo(entry2.getValue());
                    if (res == 0) {
                        return 0;
                    } else {
                        return res > 0 ? -1 : 1;
                    }
                })
                .limit(10)
                // .peek(System.out::println)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(resultTop10);
    }

}
