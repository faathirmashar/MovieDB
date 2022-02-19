# The Movie DB Task

A simple project using [The Movie DB](https://www.themoviedb.org) based on Kotlin MVVM Repository architecture<br>

![preview0](https://i.imgur.com/vMeULRt.gif)

## How to build on your environment
Add your [The Movie DB](https://www.themoviedb.org)'s API key in your `build.gradle (Module: MovieDB.app)` file. In buildTypes{} add this:
```xml
debug {
            buildConfigField("String", "API_KEY", '"?api_key=YOUR_API_KEY"')
            buildConfigField("String", "IMG_URL", '"https://www.themoviedb.org/t/p/w600_and_h900_bestv2"')
            buildConfigField("String", "BASE_URL", '"https://api.themoviedb.org"')
        }
```

## Tech stack & Open-source libraries
- Minimum SDK level 22
- 100% [Kotlin](https://kotlinlang.org/) based
- JetPack
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - ViewBinding.=
- [Retrofit2 & Gson](https://github.com/square/retrofit) - constructing the REST API
- [Glide](https://github.com/bumptech/glide) - loading images
- [AnimatedBottomBar](https://github.com/Droppers/AnimatedBottomBar) - as bottom navigation view
- [android-shape-imageview](https://github.com/siyamed/android-shape-imageview) - as shaped image view
- [android-sdp](https://github.com/intuit/sdp) - for responsive sp and dp
