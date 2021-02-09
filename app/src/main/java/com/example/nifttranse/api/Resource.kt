package  com.example.nifttranse.api

data class Resource<out T>(val status: Status,val data: T?, val statusResponse:String?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T, statusResponse : String?): Resource<T> {
            return Resource(Status.SUCCESS, data, statusResponse,null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data,null, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data,null, null)
        }
    }
}