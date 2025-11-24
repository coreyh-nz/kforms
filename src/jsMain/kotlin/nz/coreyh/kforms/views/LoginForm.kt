package nz.coreyh.kforms.views

import io.kvision.core.Container
import io.kvision.panel.SimplePanel
import nz.coreyh.kforms.common.extension.addCssClasses
import nz.coreyh.kforms.common.validation.Messages
import nz.coreyh.kforms.common.validation.Validators.email
import nz.coreyh.kforms.common.validation.Validators.required
import nz.coreyh.kforms.component.form.csForm
import nz.coreyh.kforms.component.form.field.csPasswordField
import nz.coreyh.kforms.component.form.field.csSelectField
import nz.coreyh.kforms.component.form.field.csTextField
import nz.coreyh.kforms.model.LoginViewModel

class LoginForm : SimplePanel() {
    init {
        addCssClasses("w-full max-w-lg mx-auto py-6")

        csForm {
            csTextField(LoginViewModel::email, "Email", true) {
                validator(required(Messages.Email.REQUIRED))
                validator(email(Messages.Email.INVALID))
            }
            csPasswordField(LoginViewModel::password, "Password", true) {
                validator(required(Messages.Password.REQUIRED))
            }
            csSelectField(
                LoginViewModel::role,
                "Role",
                true,
                options =
                    listOf(
                        "admin" to "Administrator",
                        "user" to "User",
                        "guest" to "Guest",
                    ),
            ) {
                validator(required(Messages.Role.REQUIRED))
            }
            csSubmitButton {}

            onSubmit {
                val email = getValue(LoginViewModel::email)
                val password = getValue(LoginViewModel::password)
                console.info("email=$email, password=$password")
            }
        }
    }
}

fun Container.loginForm() = LoginForm().also { add(it) }
