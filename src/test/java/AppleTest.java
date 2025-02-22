import org.junit.Assert;
import org.junit.Test;

/**
 * 1) открыть сайт iphones.ru
 * 2) нажать на кнопку поиска сверху справа
 * 3) ввести текст "Чем iPhone 13 отличается от iPhone 12" и нажать enter
 * 4) в найденных статьях получить href атрибут первой найденной статьи
 * 5) убедиться, что href ссылка содержит слово iPhone 13 (обрати внимание как это слово выглядит в Href
 */


public class AppleTest extends BaseTest { // Здесь проводится логика проверки. Наследуемся от BaseTest

    private final static String BASE_URL = "https://www.iphones.ru/";
    // private - доступна только в этом классе,
    // final - не можем переопределить переменную.
    // static - эта переменная инициализируется до инициализации класса, то есть она всегда есть.
    private final static String SEARCH_STRING = "Чем iPhone 13 отличается от iPhone 12";
    private final static String Expected_WORD = "iphone-13";
    private final static String Expected_WORD_Negative_TEST = "iphone-14";

    @Test
    public void checkHref() {
        MainPage mainPage = new MainPage(BASE_URL);  //создаем экземпляр этого класса
        mainPage.acceptCookies();
        mainPage.search(SEARCH_STRING);
        SearchPage searchPage = new SearchPage();
        String href = searchPage.getHrefFromFirstArticle();//String href - для того, что бы куда-то результат был записался
        /**
         * boolean contains = href.contains("iphone-13");// containts - возвращает true or false, в данном случае, ищет "iphone-13" в записанной выше href и выводит true or false
         *         // boolean - что бы результат был куда-то записан
         */
        Assert.assertTrue(href.contains(Expected_WORD)); // Используем библиотеку JUnit и сравниваем, есть ли в записаном href, "iphone-13"

    }

    /**
     * по иному цепочка методов/ это когда выполняется переход по страницам, не создавая новые экземпляры класса
     */
    @Test
    public void checkHrefTestChainMethode() {
        MainPageChain mainPageChain = new MainPageChain(BASE_URL);
        mainPageChain.acceptCookies();
        String href = mainPageChain.search(SEARCH_STRING).getHrefFromFirstArticle();
        // сразу приделываем href строку
        // здесь сразу через "." обращаемся к следующем классу и эго методу getHrefFromFirstArticle()/ в классе SearchPage
        Assert.assertTrue(href.contains(Expected_WORD));
    }

    @Test
    public void checkHrefTestChainMethodeWithOneLine(){
        Assert.assertTrue(new MainPageChain(BASE_URL)
                .acceptCookies()   // принимаем куки
                .search(SEARCH_STRING)
                .getHrefFromFirstArticle()
                .contains(Expected_WORD));
    }
    @Test
    public void checkHrefTestNegative(){
        Assert.assertFalse(new MainPageChain(BASE_URL)
                .acceptCookies()   // принимаем куки
                .search(SEARCH_STRING)
                .getHrefFromFirstArticle()
                .contains(Expected_WORD_Negative_TEST));
    }
}
