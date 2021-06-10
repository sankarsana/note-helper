package com.bhaktaprogram.lintrules

import com.android.SdkConstants.*
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr

/**
 *
 */
@Suppress("UnstableApiUsage")
class HardcodedTextDetector : LayoutDetector() {

    override fun getApplicableAttributes(): Collection<String>? {
        return listOf(
            ATTR_TEXT,
            ATTR_CONTENT_DESCRIPTION,
            ATTR_HINT,
            ATTR_LABEL,
            ATTR_PROMPT,
            "textOn",
            "textOff",
            ATTR_TITLE,
        )
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        if (attribute.namespaceURI != ANDROID_URI) {
            return
        }
        val value = attribute.value
        if (value.startsWith("@") || value.startsWith("?")) {
            return
        }
        context.report(
            ISSUE,
            context.getLocation(attribute),
            DESCRIPTION,
            createLint(context.getValueLocation(attribute))
        )
    }

    private fun createLint(valueLocation: Location): LintFix {
        return LintFix.create()
            .name("Use strings.xml")
            .replace()
            .range(valueLocation)
            .with("@string/value")
            .select("value")
            .build()
    }

    companion object {
        private const val DESCRIPTION = "Hardcoded text"
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
}