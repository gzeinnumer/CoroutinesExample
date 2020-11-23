# CoroutinesExample
 
- Implementation
```gradle
implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
implementation 'com.squareup.okhttp3:logging-interceptor:3.10.0'

implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"
```

- RetroServer
```kotlin
object RetroServer {
    private const val base_url = "https://newsapi.org/v2/"
    private fun setInit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer Token")
                    .build()
                chain.proceed(request)
            }
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    val instance: ApiService
        get() = setInit().create(ApiService::class.java)
}
```

- ApiSevice
```kotlin
interface ApiService {
    //coroutines
    //?country=us&apiKey=e5430ac2a413408aaafdf60bfa27a874
    @GET("/posts")
    fun getPostFromUserCoroutines(
            @Query("country") country: String,
            @Query("apiKey") apiKey: String
    ): Deferred<Response<ResponseNews>>
}
```

- Deffered
```java
Log.d(TAG, "onCreate: Loading Show")
GlobalScope.launch(Dispatchers.Main) {
    try {
        val call = RetroServer.instance.getPostFromUserCoroutines("us", "e5430ac2a413408aaafdf60bfa27a874")
        val response = call.await()
        when(response.code()){
            200->{
                val data = response.body()
                val msg = response.message()
                Log.d(TAG, "onCreate: Loading Dismiss")
            }
            else->{
                Log.d(TAG, "onCreate: Respose")
            }
        }
    } catch (e: Exception){
        Log.d(TAG, "onCreate: On Error")
    }
}
```

---

**FullCode [AndroidManifest](https://github.com/gzeinnumer/CoroutinesExample/blob/master/app/src/main/AndroidManifest.xml) &
[MainActivity](https://github.com/gzeinnumer/CoroutinesExample/blob/master/app/src/main/java/com/gzeinnumer/coroutinesexample/MainActivity.kt) &
[RetroServer](https://github.com/gzeinnumer/CoroutinesExample/blob/master/app/src/main/java/com/gzeinnumer/coroutinesexample/network/RetroServer.kt) &
[ApiService](https://github.com/gzeinnumer/CoroutinesExample/blob/master/app/src/main/java/com/gzeinnumer/coroutinesexample/network/ApiService.kt) &
[AdapterRX](https://github.com/gzeinnumer/CoroutinesExample/blob/master/app/src/main/java/com/gzeinnumer/coroutinesexample/adapter/AdapterRX.kt) &
[ResponseNews](https://github.com/gzeinnumer/CoroutinesExample/blob/master/app/src/main/java/com/gzeinnumer/coroutinesexample/model/ResponseNews.java) &
[ArticlesItem](https://github.com/gzeinnumer/CoroutinesExample/blob/master/app/src/main/java/com/gzeinnumer/coroutinesexample/model/ArticlesItem.java) &
[Source](https://github.com/gzeinnumer/CoroutinesExample/blob/master/app/src/main/java/com/gzeinnumer/coroutinesexample/model/Source.java)**


---

```
Copyright 2020 M. Fadli Zein
```
