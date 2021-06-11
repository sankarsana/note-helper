package com.bhaktaprogram.lintrules

import com.android.tools.lint.client.api.IssueRegistry
import com.bhaktaprogram.lintrules.xml.HardcodedTextDetector

@Suppress("UnstableApiUsage")
class LintRegistry : IssueRegistry() {

    override val issues = listOf(
        HardcodedTextDetector.ISSUE,
    )
}