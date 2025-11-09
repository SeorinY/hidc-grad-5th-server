package hidc.seorin.hidcserver.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class S3Service(
    private val s3Client: S3Client
) {
    @Value("\${aws.s3.bucket}")
    private lateinit var bucket: String

    fun uploadFile(file: MultipartFile, folder: String): String {
        val originalFilename = file.originalFilename ?: throw IllegalArgumentException("파일 이름이 없습니다")
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        val fileName = "${timestamp}_$originalFilename"
        val key = "$folder/$fileName"

        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(file.contentType)
            .build()

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.bytes))

        return "https://$bucket.s3.ap-northeast-2.amazonaws.com/$key"
    }

    fun deleteFile(fileUrl: String) {
        val key = fileUrl.substringAfter(".com/")
        
        val deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build()

        s3Client.deleteObject(deleteObjectRequest)
    }
}

