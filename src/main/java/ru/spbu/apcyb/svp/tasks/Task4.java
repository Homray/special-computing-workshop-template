package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Задание 4.
 */
public class Task4 {

  /**
   * Стартовая функция. Cчитывает из файла вещественные числа и вычисляет их тангенс в многопоточном
   * режиме (8 потоков). Результат выполнения и количество обработанных чисел записывает в другой
   * файл.
   *
   * @param args содержит 2 строки: 1) Путь до файла ввода 2) Путь до файла вывода
   */
  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {

    checkArgs(args);

    Path inputPath = Path.of(args[0]);
    Path outputPath = Path.of(args[1]);

    List<Double> numbers = inputNumbers(inputPath);

    numbers = multithreadedTangentCalculation(numbers);

    outputTangents(numbers, outputPath);

  }

  /**
   * Проверяет, что введены правильные аргументы, указанный файл ввода существует, аргументами
   * указаны пути ФАЙЛОВ.
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

    if (!new File(args[1]).isFile()) {
      throw new FileSystemException("Файлом вывода указан не файл");
    }
  }

  /**
   * Cчитывает из файла вещественные числа и записывает из в список.
   */
  @SuppressWarnings("checkstyle:WhitespaceAround")
  public static List<Double> inputNumbers(Path pathOfInputFile) throws IOException {
    try (Scanner scanner = new Scanner(pathOfInputFile)) {
      List<Double> numbers = new ArrayList<>();
      while (scanner.hasNextLine()) {
        numbers.add(Double.valueOf(scanner.nextLine()));
      }
      return numbers;
    }
  }

  /**
   * Вычисляет тангенсы всех чисел в списке в однопоточном режиме. Возвращает список всех
   * вычисленных тангенсов.
   */
  public static List<Double> singleThreadedTangentCalculation(List<Double> numbers) {
    return numbers.stream().map(Math::tan).toList();
  }

  /**
   * Вычисляет тангенсы всех чисел в списке в многопоточном режиме (8 потоков). Возвращает список
   * всех вычисленных тангенсов.
   */
  @SuppressWarnings("checkstyle:WhitespaceAround")
  public static List<Double> multithreadedTangentCalculation(List<Double> numbers)
      throws ExecutionException, InterruptedException {
    try (ExecutorService executorService = Executors.newFixedThreadPool(8)) {
      Future<List<Double>> listFuture = executorService.submit(
          () -> numbers.parallelStream().map(Math::tan).toList());

      return listFuture.get();
    }
  }

  /**
   * Записывает все количество чисел и сами числа из указанного списка в указанный файл.
   */
  public static void outputTangents(List<Double> numbers, Path outputFile) throws IOException {
    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFile)) {
      bufferedWriter.write("Количество обработанных чисел: " + numbers.size() + "\n");
      for (Double t : numbers) {
        bufferedWriter.write(t + "\n");
      }
    }
  }
}
