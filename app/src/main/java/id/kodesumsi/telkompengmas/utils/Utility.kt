package id.kodesumsi.telkompengmas.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import id.kodesumsi.telkompengmas.R
import id.kodesumsi.telkompengmas.utils.Constant.Companion.GEMUK
import id.kodesumsi.telkompengmas.utils.Constant.Companion.HEAD_CIRCUMFERENCE
import id.kodesumsi.telkompengmas.utils.Constant.Companion.HEIGHT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.KURUS
import id.kodesumsi.telkompengmas.utils.Constant.Companion.MAKROSEFALI
import id.kodesumsi.telkompengmas.utils.Constant.Companion.MIKROSEFALI
import id.kodesumsi.telkompengmas.utils.Constant.Companion.NORMAL
import id.kodesumsi.telkompengmas.utils.Constant.Companion.OBESITAS
import id.kodesumsi.telkompengmas.utils.Constant.Companion.PENDEK
import id.kodesumsi.telkompengmas.utils.Constant.Companion.SANGAT_KURUS
import id.kodesumsi.telkompengmas.utils.Constant.Companion.SANGAT_PENDEK
import id.kodesumsi.telkompengmas.utils.Constant.Companion.TINGGI
import id.kodesumsi.telkompengmas.utils.Constant.Companion.WEIGHT
import java.time.LocalDate
import java.time.temporal.ChronoUnit

object Utility {

    fun LocalDate.getAgeInMonth(): Int = ChronoUnit.MONTHS.between(this, LocalDate.now()).toInt()

    fun String.toLocalDate(): LocalDate {
        val birthDate = this.split("-")
        return LocalDate.of(birthDate[0].toInt(), birthDate[1].toInt(), birthDate[2].toInt())
    }

    fun countMonthDiff(birthDate: String, dataDate: String): Long {
        return ChronoUnit.MONTHS.between(
            LocalDate.parse(birthDate).withDayOfMonth(1),
            LocalDate.parse(dataDate).withDayOfMonth(1)
        )
    }

    fun isStunting(context: Context, attribute: String, value: String): Boolean {
        return when (attribute) {
            WEIGHT -> {
                when (value) {
                    OBESITAS -> true
                    GEMUK -> false
                    NORMAL -> false
                    KURUS -> false
                    SANGAT_KURUS -> true
                    else -> {false}
                }
            }
            HEIGHT -> {
                when (value) {
                    TINGGI -> false
                    NORMAL -> false
                    PENDEK -> false
                    SANGAT_PENDEK -> true
                    else -> {false}
                }
            }
            HEAD_CIRCUMFERENCE -> {
                when (value) {
                    MAKROSEFALI -> true
                    NORMAL -> false
                    MIKROSEFALI -> true
                    else -> {false}
                }
            }
            else -> false
        }
    }

    fun getColorFromStatus(context: Context, attribute: String, value: String): Int {
        return when (attribute) {
            WEIGHT -> {
                when (value) {
                    OBESITAS -> context.getColor(R.color.danger)
                    GEMUK -> context.getColor(R.color.danger)
                    NORMAL -> context.getColor(R.color.normal)
                    KURUS -> context.getColor(R.color.warning)
                    SANGAT_KURUS -> context.getColor(R.color.danger)
                    else -> {context.getColor(R.color.normal)}
                }
            }
            HEIGHT -> {
                when (value) {
                    TINGGI -> context.getColor(R.color.warning)
                    NORMAL -> context.getColor(R.color.normal)
                    PENDEK -> context.getColor(R.color.warning)
                    SANGAT_PENDEK -> context.getColor(R.color.danger)
                    else -> {context.getColor(R.color.normal)}
                }
            }
            HEAD_CIRCUMFERENCE -> {
                when (value) {
                    MAKROSEFALI -> context.getColor(R.color.danger)
                    NORMAL -> context.getColor(R.color.normal)
                    MIKROSEFALI -> context.getColor(R.color.danger)
                    else -> {context.getColor(R.color.normal)}
                }
            }
            else -> {context.getColor(R.color.normal)}
        }
    }

    fun getActionFromStatus(context: Context, attribute: String, value: String): String {
        return when (attribute) {
            WEIGHT -> {
                when (value) {
                    OBESITAS -> context.getString(R.string.berat_obesitas_action)
                    GEMUK -> context.getString(R.string.berat_gemuk_action)
                    NORMAL -> context.getString(R.string.berat_normal_action)
                    KURUS -> context.getString(R.string.berat_kurus_action)
                    SANGAT_KURUS -> context.getString(R.string.berat_sangat_kurus_action)
                    else -> {context.getString(R.string.berat_normal_action)}
                }
            }
            HEIGHT -> {
                when (value) {
                    TINGGI -> context.getString(R.string.tinggi_tinggi_action)
                    NORMAL -> context.getString(R.string.tinggi_normal_action)
                    PENDEK -> context.getString(R.string.tinggi_pendek_action)
                    SANGAT_PENDEK -> context.getString(R.string.tinggi_sangat_pendek_action)
                    else -> {context.getString(R.string.tinggi_normal_action)}
                }
            }
            HEAD_CIRCUMFERENCE -> {
                when (value) {
                    MAKROSEFALI -> context.getString(R.string.lingkar_kepala_makrosefali_action)
                    NORMAL -> context.getString(R.string.lingkar_kepala_normal_action)
                    MIKROSEFALI -> context.getString(R.string.lingkar_kepala_mikrosefali_action)
                    else -> {context.getString(R.string.lingkar_kepala_normal_action)}
                }
            }
            else -> {context.getString(R.string.berat_normal_action)}
        }
    }

}