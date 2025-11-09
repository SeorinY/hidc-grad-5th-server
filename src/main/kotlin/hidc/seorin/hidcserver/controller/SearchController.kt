package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.service.DesignersService
import hidc.seorin.hidcserver.service.ProfessorService
import hidc.seorin.hidcserver.service.WorksService

/**
 * SearchController
 *
 * @author 스포츠_개발 (dl_sports_sweng@navercorp.com)
 */
class SearchController(
    val worksService: WorksService,
    val designersService: DesignersService,
    val professorService: ProfessorService,
) {


}