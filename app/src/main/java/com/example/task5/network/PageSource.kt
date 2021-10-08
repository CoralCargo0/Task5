package com.example.task5.network

// class PageSource(
//    private val catsService: CatsAPI,
//    private val query: String
// ) : PagingSource<Int, Cat>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
//        TODO("Not yet implemented")
//    }
//
// //    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
// //        if (query.isBlank()) {
// //            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
// //        }
// //        try {
// //            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
// //            val pageSize = params.loadSize.coerceAtMost(catsService.MAX_PAGE_SIZE)
// //            val response = newsService.everything(query, pageNumber, pageSize)
// //
// //            if (response.isSuccessful) {
// //                val articles = response.body()!!.articles.map { it.toArticle() }
// //                val nextPageNumber = if (articles.isEmpty()) null else pageNumber + 1
// //                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
// //                return LoadResult.Page(articles, prevPageNumber, nextPageNumber)
// //            } else {
// //                return LoadResult.Error(HttpException(response))
// //            }
// //        } catch (e: HttpException) {
// //            return LoadResult.Error(e)
// //        } catch (e: Exception) {
// //            return LoadResult.Error(e)
// //        }
// //
// //    }
// }
