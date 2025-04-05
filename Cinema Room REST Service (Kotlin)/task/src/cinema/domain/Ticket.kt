package cinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore

data class Ticket(
    val row: Int,
    val column: Int,
    val price: Int = if (row < 5) 10 else 8,
    @JsonIgnore
    var sold: Boolean = false)