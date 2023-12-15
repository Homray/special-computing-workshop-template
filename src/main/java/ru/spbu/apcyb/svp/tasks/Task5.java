package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Задание 5. */
public class Task5 {

  static Logger logger = Logger.getLogger(Task5.class.getName());

  /**
   * Стартовая функция.
   * Подсчитывает количество вхождений каждого слова в указанном файле и записывает эту информацию в
   * файл count.txt в указанной директории. Также в этой директории создает папку Words и в ней в
   * одноименные файлы записывает все слова в количестве, сколько их встречалось.
   *
   * @param args содержит 2 строки: 1) Путь до файла ввода
   *                                2) Путь до директории, в которую будет проводиться запись
   */
  public static void main(String[] args) throws IOException {

    checkArgs(args);

    Path inputFile = Path.of(args[0]);

    Map<String, Long> statistics = countingOccurrenceOfWords(inputFile);

    Path directory = Path.of(args[1]);
    if (!Files.exists(directory)) {
      Files.createDirectory(directory);
    }

    recordingWordStatistics(statistics, Path.of(args[1] + "/count.txt"));

    writeWordsToFiles(statistics, args[1] + "/Words/");
  }

  /**
   * Проверяет, что введены правильные аргументы, указанный файл ввода существует и является ФАЙЛОМ.
   */
  public static void checkArgs(String[] args) throws IOException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Неправильное количество аргументов");
    }

    if (!Files.exists(Path.of(args[0]))) {
      throw new FileNotFoundException("Такого файла ввода не найдено");
    }

    if (!new File(args[0]).isFile()) {
      throw new FileSystemException("Файлом ввода указан не файл");
    }

  }

  /** Подсчитывает количество вхождений каждого слова с указанном файле. */
  public static Map<String, Long> countingOccurrenceOfWords(Path inputPath) throws IOException {
    try (Stream<String> stream = Files.lines(inputPath)) {
      return stream.flatMap(line -> Arrays.stream(line.split(" ")))
          .map(word -> word.replaceAll("[\\p{Punct}\\d–]", ""))
          .filter(word -> !word.isEmpty())
          .map(String::toLowerCase)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    } catch (IOException e) {
      throw new IOException("Ошибка при чтении файла ввода\n" + e.getMessage());
    }
  }

  /** Записывает данную карту в указанный файл. */
  public static void recordingWordStatistics(Map<String, Long> statistics, Path outputPath)
      throws IOException {
    try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
      for (var entry : statistics.entrySet()) {
        writer.write(entry.getKey() + " " + entry.getValue() + "\n");
      }
    } catch (IOException e) {
      throw new IOException("Ошибка при записи в файл");
    }
  }

  /** Записывает все слова в указанном количестве в одноименные файлы. */
  public static void writeWordsToFiles(Map<String, Long> statistics, String outputPath)
      throws IOException {
    ExecutorService executorService = Executors.newFixedThreadPool(8);
    try {
      Path directory = Path.of(outputPath);
      if (!Files.exists(directory)) {
        Files.createDirectory(directory);
      }

      statistics.forEach((word, count) -> CompletableFuture.runAsync(
          () -> writeWordToFile(word, count, outputPath), executorService).join());
    } catch (IOException e) {
      throw new IOException(e);
    } finally {
      executorService.shutdownNow();
    }
  }

  /** Записывает указанное слово в указанном количестве в указанный файл. */
  public static void writeWordToFile(String word, Long count, String outputPath) {
    Path outputFile = Path.of(outputPath + word + ".txt");
    try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
      for (long i = 0; i < count; ++i) {
        writer.write(word + " ");
      }
    } catch (IOException e) {
      logger.info("Ошибка при записи слова \"" + word + "\" в файл");
    }
  }

}
