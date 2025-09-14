package passwordgenerator.com.password;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import passwordgenerator.com.password.generator.PasswordNameStrategy;

import java.util.List;

public record PasswordCriteria(
    PasswordNameStrategy strategy,
    @Min(3) @Max(30) @Schema(description = "The desired length of the password", example = "8")
        int length,
    boolean uppercase,
    boolean digits,
    boolean special,
    String relatedWord) {

  private static final PasswordNameStrategy DEFAULT_STRATEGY = PasswordNameStrategy.SIMPLE;

  private static final int MIN_LENGTH = 3;
  private static final int MAX_LENGTH = 30;
  private static final int DEFAULT_LENGTH = 10;

  private static final boolean DEFAULT_VALUE_UPPERCASE = false;
  private static final boolean DEFAULT_VALUE_DIGITS = false;
  private static final boolean DEFAULT_VALUE_SPECIAL_SIGN = false;

  public static final List<Character> LOWER_CASE_STRING =
      List.of(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
          's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

  public static final List<Character> UPPER_CASE_STRING =
      List.of(
          'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
          'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

  public static final List<Character> DIGITS =
      List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

  public static final List<Character> SPECIAL_SIGNS =
      List.of('!', '@', '#', '$', '%', '&', '*', '_', '-');

  public PasswordCriteria {
    if (length < MIN_LENGTH || length > MAX_LENGTH)
      throw new IllegalArgumentException(String.format("Value %d is wrong", length));
  }

  static Builder builder() {
    return new Builder();
  }

  static class Builder {
    private PasswordNameStrategy strategy = DEFAULT_STRATEGY;
    private int length = DEFAULT_LENGTH;
    private boolean uppercase = DEFAULT_VALUE_UPPERCASE;
    private boolean digits = DEFAULT_VALUE_DIGITS;
    private boolean special = DEFAULT_VALUE_SPECIAL_SIGN;
    private String relatedWord = "";

    Builder length(int length) {
      this.length = length;
      return this;
    }

    Builder uppercase(boolean uppercase) {
      this.uppercase = uppercase;
      return this;
    }

    Builder digits(boolean digits) {
      this.digits = digits;
      return this;
    }

    Builder special(boolean special) {
      this.special = special;
      return this;
    }

    Builder relatedWord(String relatedWord) {
      this.relatedWord = relatedWord;
      return this;
    }

    PasswordCriteria build() {
      return new PasswordCriteria(strategy, length, uppercase, digits, special, relatedWord);
    }
  }
}
