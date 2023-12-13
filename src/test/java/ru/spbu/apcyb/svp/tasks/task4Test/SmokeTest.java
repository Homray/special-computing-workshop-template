package ru.spbu.apcyb.svp.tasks.task4Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.Task4;

class SmokeTest {

  @Test
  void invalidInputExceptionTest() {
    String[] args = {"..//"};
    assertThrows(IllegalArgumentException.class, () -> Task4.main(args));
  }

  @Test
  void FileNotFoundExceptionTest() {
    String[] args = {"non-existent file.txt", "recordingFile.txt"};
    assertThrows(FileNotFoundException.class, () -> Task4.main(args));
  }

  @Test
  void FileSystemExceptionTest() {
    String[] args1 = {"..//", "recordingFile.txt"};
    FileSystemException ex1 = assertThrows(FileSystemException.class, () -> Task4.main(args1));
    assertEquals("Файлом ввода указан не файл", ex1.getMessage());

    String[] args2 = {"src/test/Task4FilesForTest/inputFile.txt", "..//"};
    FileSystemException ex2 = assertThrows(FileSystemException.class, () -> Task4.main(args2));
    assertEquals("Файлом вывода указан не файл", ex2.getMessage());
  }

  @Test
  void smokeTest() throws IOException, ExecutionException, InterruptedException {

    List<String> ecpected = List.of(
        "0.46940785788192857",
        "1.4451801813927447",
        "0.962442104047159",
        "0.17222034606224004",
        "0.7184292779793834");

    String inputFile = "src/test/Task4FilesForTest/inputFile.txt";
    String outputFile = "src/test/Task4FilesForTest/outputFile.txt";
    String[] args = {inputFile, outputFile};

    Task4.main(args);

    Scanner scanner = new Scanner(Path.of(outputFile));
    assertEquals("Количество обработанных чисел: 5", scanner.nextLine());
    for (int i = 0; i < 5; ++i) {
      assertEquals(ecpected.get(i), scanner.nextLine());
    }
  }
  @Test
  void smokeTest_emptyFile() throws IOException, ExecutionException, InterruptedException {


    String inputFile = "src/test/Task4FilesForTest/emptyInputFile.txt";
    String outputFile = "src/test/Task4FilesForTest/outputFile.txt";
    String[] args = {inputFile, outputFile};

    Task4.main(args);

    Scanner scanner = new Scanner(Path.of(outputFile));
    assertEquals("Количество обработанных чисел: 0", scanner.nextLine());
    assertFalse(scanner.hasNextLine());
  }
}
