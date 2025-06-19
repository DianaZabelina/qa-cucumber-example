package utils.pageResolver;

import com.codeborne.selenide.Selenide;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PageResolver {

    private static final Map<String, Class<?>> titleToClassMap = new ConcurrentHashMap<>();

    static {
        // Укажи свой корневой пакет, где лежат все страницы
        Reflections reflections = new Reflections("glueSteps.pages");

        Set<Class<?>> allPages = reflections.getTypesAnnotatedWith(Title.class);

        for (Class<?> pageClass : allPages) {
            Title title = pageClass.getAnnotation(Title.class);
            if (title != null) {
                titleToClassMap.put(title.value(), pageClass);
            }
        }
    }

    public static Page getPage(String title) {
        Class<?> clazz = titleToClassMap.get(title);
        if (clazz == null) {
            throw new IllegalArgumentException("Unknown page with title: " + title);
        }

        Page page = (Page) Selenide.page(clazz);
        page.checkRequiredElements();

        return page;
    }

    public static String getPath(String title) {
        Class<?> clazz = titleToClassMap.get(title);
        if (clazz == null) {
            throw new IllegalArgumentException("Unknown page with title: " + title);
        }

        Path path = clazz.getAnnotation(Path.class);
        if (path == null) {
            throw new IllegalStateException("Page " + title + " does not have @Path annotation");
        }

        return path.value();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getPage(String title, Class<T> expectedType) {
        Object page = getPage(title);
        if (!expectedType.isInstance(page)) {
            throw new IllegalStateException("Page " + title + " is not of type " + expectedType.getSimpleName());
        }
        return (T) page;
    }
}