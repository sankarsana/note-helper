package com.bhaktaprogram.lintrules.xml

import com.android.tools.lint.detector.api.*

@Suppress("UnstableApiUsage")
object HardcodedTextIssue {
    const val DESCRIPTION = "Hardcoded text"
    private const val EXPLANATION = "You should use text from strings.xml to have " +
            "the correct texts on all languages"

    val ISSUE = Issue.create(
        id = "HardcodedText",
        briefDescription = DESCRIPTION,
        explanation = EXPLANATION,
        category = Category.USABILITY,
        priority = 5,
        severity = Severity.FATAL,
        implementation = Implementation(
            HardcodedTextDetector::class.java, Scope.RESOURCE_FILE_SCOPE
        )
    )
}