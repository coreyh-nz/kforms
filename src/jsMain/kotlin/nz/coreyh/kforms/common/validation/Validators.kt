package nz.coreyh.kforms.common.validation

object Validators {
    fun required(message: String = "Please enter this field") =
        ValidationRule<String>(
            validate = { it.isNotBlank() },
            errorMessage = message,
        )

    fun email(message: String = "Please enter a valid email") =
        ValidationRule<String>(
            validate = { it.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) },
            errorMessage = message,
        )

    fun minLength(
        length: Int,
        message: String = "Please enter at least $length characters",
    ) = ValidationRule<String>(
        validate = { it.length >= length },
        errorMessage = message,
    )

    fun maxLength(
        length: Int,
        message: String = "Please enter no more than $length characters",
    ) = ValidationRule<String>(
        validate = { it.length <= length },
        errorMessage = message,
    )

    fun pattern(
        regex: Regex,
        message: String,
    ) = ValidationRule<String>(
        validate = { it.matches(regex) },
        errorMessage = message,
    )
}
