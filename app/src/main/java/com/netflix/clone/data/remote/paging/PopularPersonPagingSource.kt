package com.netflix.clone.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.remote.dto.person.popular.PersonResult
import retrofit2.HttpException
import java.io.IOException

class PopularPersonPagingSource(
    private val movieApi: MovieApi,
    private val language: String,
    private val page: Int,
) : PagingSource<Int, PersonResult>() {
    override fun getRefreshKey(state: PagingState<Int, PersonResult>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonResult> {
        val position = params.key ?: page

        return try {
            val response = movieApi.getPopularPerson(language, position)
            val results = response.results.orEmpty()
            val totalPages = response.totalPages ?: position

            val prevKey = if (position == page) null else position - 1
            val nextKey = if (position >= totalPages || results.isEmpty()) null else position + 1

            LoadResult.Page(
                data = results,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
