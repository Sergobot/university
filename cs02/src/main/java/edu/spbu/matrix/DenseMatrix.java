package edu.spbu.matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Плотная матрица
 */
public class DenseMatrix implements Matrix {
  private int height, width;
  private double[][] array;
  private int hashCode;

  private DenseMatrix(int height, int width, double[][] array) {
    this.height = height;
    this.width = width;
    this.array = array;

    this.hashCode = Arrays.deepHashCode(this.array);
  }

  /**
   * загружает матрицу из файла
   *
   * @param fileName - path to the text file with matrix
   */
  public DenseMatrix(String fileName) throws RuntimeException {
    this.width = 0;
    this.height = 0;
    LinkedList<double[]> temp = new LinkedList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line = br.readLine();
      double[] matrixRow = Arrays.stream(line.split("\\s+"))
          .mapToDouble(Double::parseDouble).toArray();
      this.width = matrixRow.length;
      this.height = 1;
      temp.add(matrixRow);

      while ((line = br.readLine()) != null) {
        matrixRow = Arrays.stream(line.split("\\s+"))
            .mapToDouble(Double::parseDouble).toArray();

        if (matrixRow.length != this.width) {
          throw new RuntimeException("Unable to load matrix from " + fileName + ": rows have different length!");
        }

        temp.add(matrixRow);
        ++this.height;
      }

      this.array = new double[this.height][];
      for (int i = 0; i < this.height; ++i) {
        array[i] = temp.get(i);
      }
      this.hashCode = Arrays.deepHashCode(this.array);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * однопоточное умнджение матриц
   * должно поддерживаться для всех 4-х вариантов
   *
   * @param o - another matrix to multiply by
   * @return resulting matrix of the multiplication
   */
  @Override
  public Matrix mul(Matrix o) {
    if (o instanceof DenseMatrix && this.getWidth() == o.getHeight()) {
      DenseMatrix dm = (DenseMatrix) o;
      int newHeight = this.height, newWidth = dm.width;

      double[][] out = new double[newHeight][newWidth];
      for (int i = 0; i < newHeight; ++i) {
        for (int j = 0; j < newWidth; ++j) {
          for (int k = 0; k < this.width; ++k) {
            out[i][j] += this.array[i][k] * dm.array[k][j];
          }
        }
      }
      return new DenseMatrix(newHeight, newWidth, out);
    }
    return null;
  }

  /**
   * многопоточное умножение матриц
   *
   * @param o - another matrix to multiply by
   * @return resulting matrix of the multiplication
   */
  @Override
  public Matrix dmul(Matrix o) {
    return null;
  }

  /**
   * спавнивает с обоими вариантами
   *
   * @param o - an object to compare against
   * @return true if equals
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o instanceof DenseMatrix) {
      DenseMatrix dm = (DenseMatrix) o;

      if (this.hashCode != dm.hashCode) {
        return false;
      }

      for (int i = 0; i < this.getHeight(); ++i) {
        for (int j = 0; j < this.getWidth(); ++j) {
          if (this.array[i][j] != dm.array[i][j]) {
            return false;
          }
        }
      }
      return true;
    }

    if (o instanceof SparseMatrix) {
      return false;
    }

    return false;
  }

  @Override
  public double get(int i, int j) {
    return this.array[i][j];
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }
}
