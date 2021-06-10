package com.bhaktaprogram.lintrules.xml

import com.android.SdkConstants.*
import com.android.tools.lint.detector.api.LayoutDetector
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Location
import com.android.tools.lint.detector.api.XmlContext
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
            HardcodedTextIssue.ISSUE,
            context.getLocation(attribute),
            HardcodedTextIssue.DESCRIPTION,
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
}