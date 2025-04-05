package cinema.domain

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class PurchaseRequest(
    @field:Min(1)
    @field:Max(9)
    val row: Int,
    @field:Min(1)
    @field:Max(9)
    val column: Int
                          )