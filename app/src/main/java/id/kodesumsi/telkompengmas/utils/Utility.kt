package id.kodesumsi.telkompengmas.utils

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object Utility {

    fun countMonthDiff(birthDate: String, dataDate: String): Long {
        return ChronoUnit.MONTHS.between(
            LocalDate.parse(birthDate).withDayOfMonth(1),
            LocalDate.parse(dataDate).withDayOfMonth(1)
        )
    }

}