package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.dto.FileUploadResponse
import hidc.seorin.hidcserver.service.S3Service
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Tag(name = "S3", description = "S3 파일 업로드 API")
@RestController
@RequestMapping("/s3")
class S3Controller(
    private val s3Service: S3Service
) {
    @Operation(
        summary = "파일 업로드",
        description = "S3에 파일을 업로드합니다. folder: works, designers, professors, categories 등"
    )
    @PostMapping("/upload")
    fun uploadFile(
        @Parameter(description = "업로드할 파일", required = true)
        @RequestPart("file") file: MultipartFile,
        @Parameter(description = "폴더명 (works, designers, professors 등)", required = true)
        @RequestParam folder: String
    ): ResponseEntity<FileUploadResponse> {
        val fileUrl = s3Service.uploadFile(file, folder)
        val response = FileUploadResponse(
            fileUrl = fileUrl,
            fileName = file.originalFilename ?: "unknown",
            folder = folder
        )
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "파일 삭제",
        description = "S3에서 파일을 삭제합니다."
    )
    @DeleteMapping("/delete")
    fun deleteFile(
        @Parameter(description = "삭제할 파일의 전체 URL", required = true)
        @RequestParam fileUrl: String
    ): ResponseEntity<Void> {
        s3Service.deleteFile(fileUrl)
        return ResponseEntity.noContent().build()
    }
}

