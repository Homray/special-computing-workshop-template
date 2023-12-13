package ru.spbu.apcyb.svp.tasks.task4Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.spbu.apcyb.svp.tasks.Task4;

class PerformanceTest {

  @ParameterizedTest
  @CsvSource({
      "src/test/Task4FilesForTest/inputFileForPerformanceTest1Number.txt, Вычисление тангенса 1 числа:",
      "src/test/Task4FilesForTest/inputFileForPerformanceTest100Numbers.txt, Вычисление тангенса 100 числел:",
      "src/test/Task4FilesForTest/inputFileForPerformanceTest1000000Numbers.txt, Вычисление тангенса 1000000 числел:",
  })
  void performanceComparison(String path, String message)
      throws IOException, ExecutionException, InterruptedException {
    Path inputPath = Path.of(path);
    List<Double> numbers = Task4.inputNumbers(inputPath);

    System.out.println(message);

    long startTime = System.nanoTime();
    Task4.singleThreadedTangentCalculation(numbers);
    long endTime = System.nanoTime();
    long spentTime = endTime - startTime;
    System.out.println("  На вычисление в однопоточном режиме ушло  " +
        spentTime + "  наносекунд.");

    startTime = System.nanoTime();
    Task4.multithreadedTangentCalculation(numbers);
    endTime = System.nanoTime();
    spentTime = endTime - startTime;
    System.out.println("  На вычисление в многопоточном режиме ушло " +
        spentTime + "  наносекунд.\n");
  }
}
