package cinema.controller

import cinema.exception.CustomErrorMessage
import cinema.exception.SeatTakenException
import cinema.exception.WrongPasswordException
import cinema.exception.WrongTokenException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalTime
import java.util.LinkedHashMap

@ControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(SeatTakenException::class)
    fun handleSeatPurchased(e: SeatTakenException, request: WebRequest): ResponseEntity<CustomErrorMessage> {
        val body = CustomErrorMessage(
                error = e.message.toString())
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest): ResponseEntity<Any>? {

        val body: MutableMap<String, Any> = LinkedHashMap()
        body["status"] = status.value()
        body["timestamp"] = LocalTime.now()
        body["exception"] = ex.javaClass
        body["error"] = "The number of a row or a column is out of bounds!"
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(WrongTokenException::class)
    fun handleWrongToken(e: WrongTokenException, request: WebRequest): ResponseEntity<CustomErrorMessage> {
        val body = CustomErrorMessage(
                error = e.message.toString())
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(WrongPasswordException::class)
    fun handleWrongPassword(e: WrongPasswordException, request: WebRequest): ResponseEntity<CustomErrorMessage> {
        val body = CustomErrorMessage(
                error = e.message.toString())
        return ResponseEntity(body,HttpStatus.UNAUTHORIZED)
    }
}