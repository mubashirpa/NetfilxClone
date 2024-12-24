package com.netflix.clone.core.utils

import java.util.concurrent.TimeUnit

object CacheUtils {
    fun isCacheValid(lastUpdated: Long): Boolean {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return System.currentTimeMillis() - lastUpdated <= cacheTimeout
    }
}
