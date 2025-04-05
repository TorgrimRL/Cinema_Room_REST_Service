package cinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.concurrent.ConcurrentHashMap


object Cinema {
    val total_rows: Int = 9
    val total_columns: Int = 9
    @get: JsonIgnore
    val purchases: ConcurrentHashMap<String,Ticket> = ConcurrentHashMap()

    @get: JsonIgnore
    val tickets = Array(size = total_columns * total_rows, init = { index: Int ->
        Ticket(
                row = (index / total_columns) + 1,
                column = (index % total_columns) + 1)
    })
    val available_seats = tickets.filter { ticket: Ticket -> !ticket.sold }

}

class Stats{
    val current_income:Int
        get() = Cinema.purchases.values.sumOf { it.price }
    val number_of_available_seats:Int
        get() = Cinema.tickets.count { !it.sold }
    val number_of_purchased_tickets:Int
        get() =  Cinema.tickets.count { it.sold }
}