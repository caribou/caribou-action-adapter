package com.instrument.triface.util;

public interface ITypeUtils {

  /**
   * Define a type conversion Function interface
   */
  public interface Function<F, T> {
    T apply(F from);
  }

}
