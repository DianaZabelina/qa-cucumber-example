package utils.pageResolver;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$$;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Page {
    private final Map<String, Field> fields = new HashMap<>();

    public Page() {
        Class<?> clazz = this.getClass();
        Field[] declaredFields = collectDeclaredFields(clazz);
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Title.class)) {
                fields.put(field.getDeclaredAnnotation(Title.class).value(), field);
            }
        }
    }

    public boolean isLoaded() {
        Iterator var1 = this.fields.entrySet().iterator();

        while (var1.hasNext()) {
            Map.Entry<String, Field> entry = (Map.Entry) var1.next();
            if (((Field) entry.getValue()).isAnnotationPresent(Disappearing.class)) {
                this.getElement((String) entry.getKey()).shouldNotBe(Condition.visible, Duration.ofSeconds(15L));
            } else if (!((Field) entry.getValue()).isAnnotationPresent(Optional.class)) {
                this.getElement((String) entry.getKey()).shouldBe(Condition.visible, Duration.ofSeconds(15L));
            }
        }

        return true;
    }

    public void checkRequiredElements() {
        for (Map.Entry<String, Field> entry : fields.entrySet()) {
            Field field = entry.getValue();
            String elementName = entry.getKey();

            if (!field.isAnnotationPresent(Optional.class)) {
                try {
                    getElement(elementName).shouldBe(Condition.visible);
                } catch (Throwable e) {
                    throw new AssertionError("Обязательный элемент '" + elementName + "' не найден или не видим!", e);
                }
            }
        }
    }

    private static Field[] collectDeclaredFields(Class<?> clazz) {
        // собираем все поля класса и родителей до Object
        java.util.List<Field> allFields = new java.util.ArrayList<>();
        while (clazz != null && !clazz.equals(Object.class)) {
            Field[] declared = clazz.getDeclaredFields();
            for (Field f : declared) {
                allFields.add(f);
            }
            clazz = clazz.getSuperclass();
        }
        return allFields.toArray(new Field[0]);
    }

    public SelenideElement getElement(String elementName) {
        Field field = fields.get(elementName);
        if (field == null) {
            throw new IllegalArgumentException("No such element: " + elementName);
        }
        By locator = getElementLocator(elementName);
        return com.codeborne.selenide.Selenide.$(locator);
    }

    public SelenideElement getElementWithText(String text) {
        ElementsCollection elements = $$(By.xpath("//*[contains(text(),'" + text + "')]"));
        elements.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return elements.last();
    }

    public By getElementLocator(String elementName) {
        Field field = fields.get(elementName);
        if (field == null) {
            throw new IllegalArgumentException("No such element: " + elementName);
        }

        // если есть @FindBy(xpath = ...)
        FindBy findBy = field.getAnnotation(FindBy.class);
        if (findBy != null && !findBy.xpath().isEmpty()) {
            return By.xpath(findBy.xpath());
        }

        // fallback: по тексту
        return By.xpath("//*[text()='" + elementName + "']");
    }
}
