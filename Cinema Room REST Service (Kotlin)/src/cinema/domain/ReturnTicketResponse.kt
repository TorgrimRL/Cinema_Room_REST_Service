package cinema.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class ReturnTicketResponse(
    @JsonProperty("returned_ticket")
    val returnedTicket: Ticket?
                               )