package hidc.seorin.hidcserver.dto

import hidc.seorin.hidcserver.domain.DesignersDomain
import hidc.seorin.hidcserver.domain.ProfessorDomain
import hidc.seorin.hidcserver.domain.WorksDomain

data class SearchResult(
    val professors: List<ProfessorDomain> = emptyList(),
    val designers: List<DesignersDomain> = emptyList(),
    val works: List<WorksDomain> = emptyList(),
    val resultType: String
)

