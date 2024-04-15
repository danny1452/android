package com.anushka.retrofitdemo

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("/posts")
    suspend fun getPosts() : Response<Posts> // response okhttp class

    @GET("/posts") // end point
    suspend fun getSortedPosts(@Query("userId") userId:Int) : Response<Posts>

    @GET("/posts/{id}")
    suspend fun getAlbum(@Path(value = "id")postId:Int) : Response<PostsItem>

    @POST("/posts")
    suspend fun uploadAlbum(@Body post: PostsItem): Response<PostsItem>
}