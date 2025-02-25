package core;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestWatcherExtension implements TestWatcher {

    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/screenshots";
    private static final int MAX_SCREENSHOTS = 5;

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String className = context.getTestClass()
                .map(Class::getSimpleName)
                .orElse("UnknownClass");
        String methodName = context.getTestMethod()
                .map(method -> method.getName())
                .orElse("UnknownMethod");

        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String screenshotName = className + "_" + methodName + "_" + timestamp;

        // Создаем папку, если ее нет
        File screenshotsFolder = new File(SCREENSHOT_DIR);
        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdirs();
        }

        // Удаляем старые файлы, если их больше 10
        cleanupOldScreenshots(screenshotsFolder);

        // Делаем скриншот
        Selenide.screenshot(screenshotName);
    }

    private void cleanupOldScreenshots(File folder) {
        File[] files = folder.listFiles();
        if (files != null && files.length > MAX_SCREENSHOTS) {
            Arrays.stream(files)
                    .sorted((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified())) // Сортируем по времени изменения
                    .limit(files.length - MAX_SCREENSHOTS) // Берем старые файлы
                    .forEach(File::delete); // Удаляем
        }
    }
}
