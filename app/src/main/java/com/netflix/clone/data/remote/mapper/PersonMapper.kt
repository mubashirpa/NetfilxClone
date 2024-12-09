package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.remote.dto.person.credits.Cast
import com.netflix.clone.data.remote.dto.person.credits.PersonCreditsDto
import com.netflix.clone.data.remote.dto.person.details.PersonDetailsDto
import com.netflix.clone.data.remote.dto.person.images.PersonImagesDto
import com.netflix.clone.data.remote.dto.person.images.Profile
import com.netflix.clone.data.remote.dto.person.popular.KnownFor
import com.netflix.clone.data.remote.dto.person.popular.PersonResult
import com.netflix.clone.domain.model.person.PersonDetails
import com.netflix.clone.domain.model.person.credits.PersonCast
import com.netflix.clone.domain.model.person.credits.PersonCredits
import com.netflix.clone.domain.model.person.images.PersonImages
import com.netflix.clone.domain.model.person.images.PersonProfile
import com.netflix.clone.domain.model.person.popular.KnownForModel
import com.netflix.clone.domain.model.person.popular.PersonResultModel

fun PersonResult.toPersonResultModel(): PersonResultModel =
    PersonResultModel(
        id,
        knownFor?.map { it.toKnownForModel() },
        knownForDepartment,
        name,
        profilePath,
    )

fun KnownFor.toKnownForModel(): KnownForModel = KnownForModel(mediaType, name, title)

fun PersonDetailsDto.toPersonDetails(): PersonDetails =
    PersonDetails(
        biography,
        birthday,
        knownForDepartment,
        name,
        placeOfBirth,
        popularity,
        profilePath,
        credits?.toPersonCredits(),
        images?.toPersonImages(),
    )

fun PersonCreditsDto.toPersonCredits(): PersonCredits = PersonCredits(cast?.map { it.toPersonCast() })

fun Cast.toPersonCast(): PersonCast =
    PersonCast(
        firstAirDate,
        id,
        mediaType,
        name,
        posterPath,
        releaseDate,
        title,
        voteAverage,
    )

fun PersonImagesDto.toPersonImages(): PersonImages = PersonImages(profiles?.map { it.toPersonProfile() })

fun Profile.toPersonProfile(): PersonProfile = PersonProfile(filePath)
