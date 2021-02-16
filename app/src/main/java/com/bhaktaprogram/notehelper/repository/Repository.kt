package com.bhaktaprogram.notehelper.repository

import com.bhaktaprogram.notehelper.ui.IconView

class Repository(private val api: IconApi) {

    suspend fun searchIcons(query: String): List<IconView> {
        val params = mapOf(
            "query" to query,
            "count" to "50"
        )

        val response = try {
            api.searchIcons(params).body()
        } catch (e: Exception) {
            return emptyList()
        } ?: return emptyList()

        return response.icons.map { it.toIconView() }
    }

    private fun IconResponse.toIconView() = IconView(
        id = iconId,
        url = rasterSizes.last().urls.first().url,
        tags = tags.toString()
    )
}