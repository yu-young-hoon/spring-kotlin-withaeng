package com.withaeng.external.email.template

enum class EmailTemplate(
    private val subject: String,
    private val variables: Map<String, Class<*>>,
) {
    VERIFY_EMAIL(
        "같이행 서비스 이메일 인증을 부탁드립니다.",
        mapOf(
            "email" to String::class.java,
            "redirectUrl" to String::class.java
        )
    ),
    CHANGE_PASSWORD(
        "같이행 서비스 이메일 인증을 부탁드립니다.",
        mapOf(
            "email" to String::class.java,
            "redirectUrl" to String::class.java
        )
    );

    fun templateName(): String {
        return this.name.lowercase().replace("_", "-")
    }

    fun subject(): String {
        return this.subject
    }

    fun validateVariables(variables: Map<String, Any>) {
        validateHasRequiredVariables(variables)
        validateVariableTypes(variables)
    }

    private fun validateHasRequiredVariables(variables: Map<String, Any>) {
        val requiredVariables = this.variables.keys
        val missingVariables = requiredVariables.filter { !variables.containsKey(it) }
        if (missingVariables.isNotEmpty()) {
            throw IllegalArgumentException("Missing variables: $missingVariables")
        }
    }

    private fun validateVariableTypes(variables: Map<String, Any>) {
        val invalidVariables = variables.filter { (key, value) ->
            this.variables[key]?.isInstance(value) == false
        }
        if (invalidVariables.isNotEmpty()) {
            throw IllegalArgumentException("Invalid variables: $invalidVariables")
        }
    }
}
