package com.example.arcanite.data.mappers

import com.example.arcanite.data.network.models.file.GitHubFile
import com.example.arcanite.domain.models.file.File

class FileMapper {
    companion object{
        fun mapListFileDataToDomain(list: List<GitHubFile>): List<File>{
            return list.map { mapFileDataToDomain(it) }
        }

        private fun mapFileDataToDomain(file: GitHubFile): File {
            return File(
                name = file.name,
                path = file.path,
                type = file.type,
                size = file.size,
                url = file.url,
                downloadUrl = file.downloadUrl
            )
        }
    }
}