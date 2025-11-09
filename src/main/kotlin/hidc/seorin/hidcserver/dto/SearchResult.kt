package hidc.seorin.hidcserver.dto

import hidc.seorin.hidcserver.entity.Designers
import hidc.seorin.hidcserver.entity.Professor
import hidc.seorin.hidcserver.entity.Works

data class SearchResult(
    val professors: List<Professor> = emptyList(),
    val designers: List<Designers> = emptyList(),
    val works: List<Works> = emptyList(),
    val resultType: String
)

