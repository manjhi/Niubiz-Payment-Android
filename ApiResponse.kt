
class ApiResponse<T>(
    var status: Boolean = false,
    var data: T? = null,
    var message: String = "",
    var statusCode: Int? = 200) {
}
