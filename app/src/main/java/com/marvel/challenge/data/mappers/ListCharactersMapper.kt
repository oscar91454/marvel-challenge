package com.marvel.challenge.data.mappers

import com.marvel.challenge.data.api.response.characters.ServerResult
import com.marvel.challenge.data.api.response.characters.ServerThumbnail
import com.marvel.challenge.data.api.response.characters.ServerUrl
import com.marvel.challenge.domain.model.characters.*


fun List<ServerResult>.toDomain() = ListCharactersModel(
    results = this.map { it.toServerResultDomain() }
)

fun ServerResult.toServerResultDomain(): ResultCharacterModel {
    return ResultCharacterModel(
        id = id,
        name = name,
        thumbnail = thumbnail.toServerThumbnailDomain(),
        urls = urls.toServerUrlDomain()
    )
}

fun ServerThumbnail.toServerThumbnailDomain() = ThumbnailModel(extension = extension, path = path)

fun List<ServerUrl>.toServerUrlDomain() = ListUrlsModel(
    urls = this.map { it.toBDomain() }
)

fun ServerUrl.toBDomain(): UrlsModel {
    return UrlsModel(
        type = type,
        url = url
    )
}