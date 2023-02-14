package guesser;

import java.util.Objects;

class ImmutablePair<T, U> {
  private final T first;
  private final U second;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImmutablePair<?, ?> pair = (ImmutablePair<?, ?>) o;
    return first.equals(pair.first) && second.equals(pair.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public String toString() {
    return "ImmutablePair{" +
        "first=" + first +
        ", second=" + second +
        '}';
  }

  ImmutablePair(T first, U second) {
    this.first = first;
    this.second = second;
  }
}
