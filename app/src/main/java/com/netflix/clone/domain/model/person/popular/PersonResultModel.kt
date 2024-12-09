package com.netflix.clone.domain.model.person.popular

data class PersonResultModel(
    val id: Int? = null,
    val knownFor: List<KnownForModel>? = null,
    val knownForDepartment: String? = null,
    val name: String? = null,
    val profilePath: String? = null,
)
