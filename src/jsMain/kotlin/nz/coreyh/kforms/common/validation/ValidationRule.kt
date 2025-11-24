package nz.coreyh.kforms.common.validation

data class ValidationRule<T>(
    val validate: (T) -> Boolean,
    val errorMessage: String,
)
