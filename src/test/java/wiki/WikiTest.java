package wiki;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;


public class WikiTest extends BaseTest {
    private final static String URL = "https://ru.wikipedia.org/wiki/Java";

    @Test
    public void openAllHrefsWithForINT(){
        Selenide.open(URL); // Открытие браузера
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]"); //обозначаем коллекцию элементов/ через два знака $ и x, можно найти колекцию элементов через xpath
        // Возможно если есть List или Collection, тогда су шествует возможность перебирать колекции через StreamAPI
        List<String> links = new ArrayList<>();
        /**
         * По очереди проходим по всей колекции и записываем каждый href в лист
         */
        for(int i = 0; i< hrefs.size(); i++){
            links.add(hrefs.get(i).getAttribute("href"));
        }
    }

    @Test
    public void openAllHrefsWithForEach(){
        Selenide.open(URL); // Открытие браузера
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        /**
         * Выбираем тип элемента из колекции
         */
        for(SelenideElement element: hrefs){ // Здесь не нужно указывать индекс относительно предыдущего варианта
            // Не нужно определять размер списка и сколько итераций нужно проводить
            links.add(element.getAttribute("href"));
        }
    }
    @Test
    public void openAllHrefsWithStreaAPI() {
        Selenide.open(URL); // Открытие браузера
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        /**
         * Обрашаемся к колекции элементов hrefs/ вызываем метод Stream(не нужен, так как обнова в коде)
         * Потом вызываем метод forEach
         * Через Лямду указывается логика которую мы будем производить с каждым элементом
         */
        hrefs.forEach(x->links.add(x.getAttribute("href")));
        /**
         * Получаем значение из полученных ссылок
         */
        links.forEach(Selenide::open);//Открытие всех полученных ссылок через StreamApi
    }
}
