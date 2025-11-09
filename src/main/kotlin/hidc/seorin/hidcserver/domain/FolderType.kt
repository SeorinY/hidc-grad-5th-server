package hidc.seorin.hidcserver.domain

enum class FolderType(val folderName: String) {
    WORKS("works"),
    DESIGNERS("designers"),
    PROFESSORS("professors"),
    WORKS_FILE("works_file");

    companion object {
        fun from(value: String): FolderType {
            return entries.find { it.folderName == value.lowercase() }
                ?: throw IllegalArgumentException("Invalid folder type: $value. Available: works, designers, professors, works_file")
        }
    }
}

