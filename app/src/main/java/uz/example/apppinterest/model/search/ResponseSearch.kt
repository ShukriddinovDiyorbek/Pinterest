package uz.example.apppinterest.model.search

data class ResponseSearch(
    val results:List<Result>,
    val likes:Long
)