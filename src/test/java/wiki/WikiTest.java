package wiki;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import core.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        links.forEach(Selenide::open);//Открытие всех полученных ссылок через StreamApi и сравнения ссылки которую нам достоится из коллекции
    }
    @Test
    public void openAllHrefsWithStreaAPIFOR() {
        Selenide.open(URL); // Открытие браузера
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        hrefs.forEach(x->links.add(x.getAttribute("href")));
        for (int i = 0; i< links.size(); i++){
            String listUrl = links.get(i);//получения внешний ссылки, с которой можем работать, из списка
            Selenide.open(listUrl);//открываем ссылку которую достали в предыдущем шаге
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();//ссылка которая достоится из адресной строки, ссылка из Браузера
            Assert.assertEquals(currentUrl, listUrl);// Сравниваем ссылку из списка и сравниваем ее с поточной, которая открывается в браузере
        }
        }
    /**
     * Получаем случайное значение из списка
     * Достаем какие-то значения
     * И в открывшейся ссылке, убираем со списка
     * Тоесть в рандомном порядке открыть все ссылки
     */
    @Test
    public void openAllHrefsWithStreaAPIRandomValue(){
        Selenide.open(URL); // Открытие браузера
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        hrefs.forEach(x->links.add(x.getAttribute("href")));
        //Random random = new Random(); // Класс для вычисления каких-то рандомных значений// пересеяно чуть ниже
        while (links.size() > 0) { // Пока размер списка будет больше нуля, будем выполнять какие-то вычесления
            int randomNumber = new Random().nextInt(links.size());// Перенесено сюда, так как мы используем данную переменную лишь один раз, здесь
            Selenide.open(links.get(randomNumber));// открываем надомную ссылку
            links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());//получаем значения открытой ссылки и удаляем ее из списка
        }
    }

    /**
     * Получаем длину ссылок
     * В итоге получаем список с числами
     * map - промежуточный метод, который конвертирует один тип данных в другой
     */
    @Test
    public void openAllHrefsWithFullStreaAPI(){
        Selenide.open(URL); // Открытие браузера
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        hrefs.forEach(x->links.add(x.getAttribute("href")));
        List<Integer> linksLenghth = hrefs.stream().map(x->x.getAttribute("href").length()).toList();// В конце засовываем все полученные данные в лист
    int i = 0;
    }
}
