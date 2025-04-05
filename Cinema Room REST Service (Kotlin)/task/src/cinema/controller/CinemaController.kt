package cinema.controller

import cinema.domain.*
import cinema.exception.SeatTakenException
import cinema.exception.WrongPasswordException
import cinema.exception.WrongTokenException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID.randomUUID


@RestController
class CinemaController {

    @GetMapping(path = ["/seats"])
    fun getCinemaInfo(): Cinema {
        return Cinema
    }

    @PostMapping(path = ["/purchase"])
    fun checkAndPurchase(
        @Valid @RequestBody purchaseRequest: PurchaseRequest): ResponseEntity<Any?> {
        val seatNumber = ((purchaseRequest.row - 1) * Cinema.total_columns) + (purchaseRequest.column - 1)
        if (!Cinema.tickets[seatNumber].sold) {
            Cinema.tickets[seatNumber].sold = true
            val UUID = randomUUID().toString()
            Cinema.purchases[UUID] = Cinema.tickets[seatNumber]
            val purchase = Purchase(UUID,Cinema.tickets[seatNumber])
            return ResponseEntity(
                    purchase, HttpStatus.OK)
        } else {
            throw SeatTakenException("The ticket has been already purchased!")
        }
    }
    @PostMapping(path = ["/return"])
    fun refundTicket(@RequestBody returnTicketRequest: ReturnTicketRequest):ResponseEntity<Any?>{
        if (Cinema.purchases.containsKey(returnTicketRequest.token)){
            Cinema.purchases[returnTicketRequest.token]!!.sold = false
            val response = ReturnTicketResponse(Cinema.purchases[returnTicketRequest.token])
            Cinema.purchases.remove(returnTicketRequest.token)
            return ResponseEntity(response,HttpStatus.OK)
        } else throw WrongTokenException("Wrong token!")
    }

    @GetMapping(path = ["/stats"])
    fun getStats(password: String?): ResponseEntity<Any?>{
        if (password == "super_secret"){
            val stats = Stats()
            return ResponseEntity(
                    stats, HttpStatus.OK)
        } else {
            throw WrongPasswordException("The password is wrong!")
        }
    }
}