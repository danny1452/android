package com.anushka.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

// we are not following MVVM in this project so everything is in main activity
// in ideal case we has Fragment - Activity - View Model - Live Data - Repository - Retrofit
class MainActivity : AppCompatActivity() {
    private lateinit var retService: PostService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // also data binding is enabled in this project
        val text_view = findViewById<TextView>(R.id.text_view)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(PostService::class.java) // service and retrofit connection done here
        // create method creates service object
         getRequestWithQueryParameters(text_view)
        // getRequestWithPathParameters(text_view)
        //uploadAlbum(text_view)
    }

    private fun getRequestWithQueryParameters(text_view: TextView) {
        val responseLiveData: LiveData<Response<Posts>> = liveData {// liveData builder
            val response = retService.getSortedPosts(3)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = " " + "Album Title : ${albumsItem.title}" + "\n" +
                            " " + "Album id : ${albumsItem.id}" + "\n" +
                            " " + "User id : ${albumsItem.userId}" + "\n\n\n"
                    text_view.append(result)
                }
            }
        })
    }


    private fun getRequestWithPathParameters(text_view: TextView) {
        val pathResponse: LiveData<Response<PostsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)

        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbum(text_view: TextView) {
        val post = PostsItem("post body ", 0, "My title", 3)
        val postResponse: LiveData<Response<PostsItem>> = liveData {
            val response = retService.uploadAlbum(post)
            emit(response)
        }
        postResponse.observe(this, Observer {
            val receivedAlbumsItem = it.body()
            val result = " " + "Album Title : ${receivedAlbumsItem?.title}" + "\n" +
                    " " + "Album id : ${receivedAlbumsItem?.id}" + "\n" +
                    " " + "User id : ${receivedAlbumsItem?.userId}" + "\n\n\n"
            text_view.text = result
        })

    }

}