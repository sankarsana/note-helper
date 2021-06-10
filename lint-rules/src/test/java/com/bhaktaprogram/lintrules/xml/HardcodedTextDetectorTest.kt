package com.bhaktaprogram.lintrules.xml

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

@Suppress("UnstableApiUsage")
class HardcodedTextDetectorTest {

    @Test
    fun `should return error`() {
        val xml = """<?xml version="1.0" encoding="utf-8"?>
                    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content">
                        <TextView
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:text="some text" />
                    </FrameLayout>"""
        runDetector(xml, 1)
    }

    @Test
    fun `should not return error on tools namespace`() {
        val xml = """<?xml version="1.0" encoding="utf-8"?>
                    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                        xmlns:tools="http://schemas.android.com/tools"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content">
                        <TextView
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            tools:text="some text" />
                    </FrameLayout>"""
        runDetector(xml, 0)

    }

    @Test
    fun `should not return error if string resource used`() {
        val xml = """<?xml version="1.0" encoding="utf-8"?>
                    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                        xmlns:tools="http://schemas.android.com/tools"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content">
                        <TextView
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:text="@string/text" />
                    </FrameLayout>"""
        runDetector(xml, 0)
    }

    @Test
    fun `should not return error if attr used`() {
        val xml = """<?xml version="1.0" encoding="utf-8"?>
                    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
                        xmlns:tools="http://schemas.android.com/tools"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content">
                        <TextView
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:text="?attr/text" />
                    </FrameLayout>"""
        runDetector(xml, 0)
    }

    private fun runDetector(xml: String, expectedErrorCount: Int) {
        TestLintTask.lint()
            .allowMissingSdk()
            .files(TestFiles.xml("res/layout/layout.xml", xml))
            .issues(HardcodedTextIssue.ISSUE)
            .run()
            .expectErrorCount(expectedErrorCount)
    }
}