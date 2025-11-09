package hidc.seorin.hidcserver.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("HIDC Grad 5th API")
                    .description("HIDC Grad 5th Server API Documentation")
                    .version("1.0.0")
            )
    }
}

