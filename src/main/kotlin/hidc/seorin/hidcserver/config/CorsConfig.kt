package hidc.seorin.hidcserver.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "https://api.hidcgs2025.com",
                "http://api.hidcgs2025.com",
                "https://hidcgs2025.com",
                "http://hidcgs2025.com",
                "https://hidc-client.pages.dev",
                "http://localhost:3000",
                "http://localhost:5173"
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .exposedHeaders("*")
            .maxAge(3600)
    }
}

