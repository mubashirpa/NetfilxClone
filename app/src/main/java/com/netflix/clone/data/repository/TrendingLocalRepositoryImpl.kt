package com.netflix.clone.data.repository

import com.netflix.clone.core.utils.CacheUtils
import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.UpdateTime
import com.netflix.clone.data.local.entity.trending.TrendingEntity
import com.netflix.clone.domain.repository.TrendingLocalRepository

private const val TRENDING_TYPE = "trending"

class TrendingLocalRepositoryImpl(
    database: NetflixDatabase,
) : TrendingLocalRepository {
    private val updateTimeDao = database.updateTimeDao()
    private val trendingDao = database.trendingDao()

    override suspend fun getCachedTrending(): List<TrendingEntity> = trendingDao.getAll()

    override suspend fun isCacheValid(): Boolean {
        val lastUpdated = updateTimeDao.updateTimeByType(TRENDING_TYPE)?.lastUpdated ?: 0
        return CacheUtils.isCacheValid(lastUpdated)
    }

    override suspend fun clearCache() {
        updateTimeDao.deleteByType(TRENDING_TYPE)
        trendingDao.clearAll()
    }

    override suspend fun saveTrending(trending: List<TrendingEntity>) {
        updateTimeDao.insertOrReplace(
            UpdateTime(
                type = TRENDING_TYPE,
                lastUpdated = System.currentTimeMillis(),
            ),
        )
        trendingDao.insertAll(trending)
    }
}
