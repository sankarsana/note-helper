package com.bhaktaprogram.lintrules

import com.android.tools.lint.client.api.IssueRegistry
import com.bhaktaprogram.lintrules.xml.HardcodedTextIssue

@Suppress("UnstableApiUsage")
class LintRegistry : IssueRegistry() {

    override val issues = listOf(
        HardcodedTextIssue.ISSUE,
    )
}