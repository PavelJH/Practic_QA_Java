package selenideAppleRu;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchPage {
    private final ElementsCollection articleTitle = $$x("//div//a[@class = 'gs-title']"); //тут два знака $ - так как это уже коллекция

    /**
     * Тут мы получаем первый элемент(href из первой статьи) из всей коллекции.
     * String - так как нам нужно получить атрибут, а атрибут имеет текстовый формат.
     * @return
     */
    public String getHrefFromFirstArticle(){
        return articleTitle.first().getAttribute("href");// таким образом мы берем только элемент/атрибут(пишет здесь именно, какой атрибут нам нужно получить.
        // И возвращаем строку, так как тип метода String и мы обязаны что-то вернуть, в таком же типе
    }
}
