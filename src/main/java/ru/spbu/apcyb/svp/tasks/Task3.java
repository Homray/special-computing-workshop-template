package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Задание 3.
 */
public class Task3 {

  private static BufferedWriter bufferedWriter;


  /**
   * Стартовая функция.
   * Создает целевой файл и записывает в него имена всех папок и файлов
   * директории с помощью recordListOfFilesAndFolders
   *
   * @param args содердит 2 строки: 1) Путь до доректории 2) Путь до целевого файла
   */
  public static void main(String[] args) throws IOException {
    checkArgs(args);

    /* файл, в который происходит запись */
    Path recordingFile = Files.createFile(Path.of(args[1]).toAbsolutePath());

    bufferedWriter = Files.newBufferedWriter(recordingFile);

    recordListOfFilesAndFolders(args[0]);

    bufferedWriter.close();


  }

  /**
   * Проверяет, что введены правильные аргументы, указанная директория существует, 2ым аргументом
   * указан путь ФАЙЛА и файла с указанным путем еще не существует.
   */
  public static void checkArgs(String[] args) throws IOException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Неправильное количество аргументов");
    }

    if (!Files.exists(Path.of(args[0]))) {
      throw new FileNotFoundException("Такой директории не найдено");
    }

    if (new File(args[1]).isDirectory()) {
      throw new FileSystemException("Указан не файл");
    }

    if (Files.exists(Path.of(args[1]))) {
      throw new FileAlreadyExistsException("Такой файл уже существует");
    }
  }

  /**
   * Оберточная функция для функуии recordListOfFilesAndFolders.
   *
   * @param path строка, содержащая пкть к директории
   */
  public static void recordListOfFilesAndFolders(String path)
      throws IOException {
    recordListOfFilesAndFolders(path, 0);
  }


  /**
   * Основная исполнительная функция.
   * Получает все папки и файлы из директории и записывает  их имена в целевой файл
   *
   * @param path  строка, содержащая пкть к директории
   * @param index индекс глубины рекурсии (нужен для отслеживания глубины дериктории)
   */
  public static void recordListOfFilesAndFolders(String path, int index)
      throws IOException {
    File[] files = getFilesOfFolder(path);
    for (File f : files) {
      indent(index);
      if (f.isDirectory()) {
        bufferedWriter.write(f.getName() + "\n");
        recordListOfFilesAndFolders(f.getAbsolutePath(), index + 1);
      } else {
        bufferedWriter.write(f.getName() + "\n");
      }
    }
  }

  /**
   * Получение всех файлов/папок папки на том же уровне глубины.
   */
  private static File[] getFilesOfFolder(String path) {
    File root = new File(path);
    return root.listFiles();
  }


  /**
   * Совершение отступа для структурирования папок и файлов на разных уровнях глубины.
   */
  public static void indent(int value) throws IOException {
    for (int i = 0; i < value; ++i) {
      bufferedWriter.write("  ");
    }
  }
}
