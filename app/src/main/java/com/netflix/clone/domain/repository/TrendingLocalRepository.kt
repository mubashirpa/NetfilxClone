package com.netflix.clone.domain.repository

import com.netflix.clone.data.local.entity.trending.TrendingEntity

interface TrendingLocalRepository {
    suspend fun getCachedTrending(): List<TrendingEntity>

    suspend fun isCacheValid(): Boolean

    suspend fun clearCache()

    suspend fun saveTrending(trending: List<TrendingEntity>)
}
