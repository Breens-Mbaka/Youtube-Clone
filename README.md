## Youtube Android Clone 🚀
This app consumes The Youtube Api to fetch and display a list of popular videos, The app uses MVVM design pattern to allow separation of the app components like fragments & activities from the data logic, some of the benefits are:
* Make the data survive device configuration changes
* Allow easier testing of different components in the app
* Also it's easier to maintain and scale the app over time

## Prerequisite
* To run the app successfully you need to create your own Google API KEY
* Navigate to the `com.breens.youtubeclone.util.Constants.kt` file and put your own api key
``
 const val KEY = "PUT YOUR OWN GOOGLE API KEY"
``
* Here is the link to generate your [Google API KEY](https://developers.google.com/maps/documentation/javascript/cloud-setup)

## Screenshots
### Splash/Home Screens
<img src="/images/splashscreen.png" width="260">&emsp;
<img src="/images/homescreen.png" width="260">

## Demo
https://user-images.githubusercontent.com/72180010/161577746-5d8e7c6e-c107-430d-94ce-306e50ca52d5.mp4

## Libraries
* Navigation Components - To implement the Single Activity approach since fragments are lightweight than activities and allows easy passing of data between arguments in a type safe way
* Retrofit - A Type-safe HTTP client for Android which simplifies consuming RESTFul APIs.
* GsonConverter - Converts JSON to Kotlin objects.
* OkHttp-logging-interceptor - Makes it easy to log OkHttp network responses and requests
* Coil - Image loading library for android
* Dagger Hilt - Used for Dependency Injection
* View Binding - Jetpack library allowing type safe binding of views
* Livedata - Notifies Observer objects in the UI when underlying data changes.

## Author Info
* Twitter - [@BreensR](https://twitter.com/BreensR)
* Linkedin - [LinkedIn: Breens Robert](https://www.linkedin.com/in/breens-mbaka/)

## License
[MIT](https://choosealicense.com/licenses/mit/)
