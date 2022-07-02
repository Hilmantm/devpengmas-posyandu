package id.kodesumsi.telkompengmas.utils

class Constant {

    companion object {
        const val SPLASHSCREEN_DELAY = 1000L

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "pengmas-database.db"

        const val ROLE_POSYANDU = 1
        const val ROLE_PARENT = 2

        const val MAN = "LAKI_LAKI"
        const val WOMAN = "PEREMPUAN"

        const val WEIGHT = "berat"
        const val HEIGHT = "tinggi"
        const val HEAD_CIRCUMFERENCE = "lingkar_kepala"

        // statistics status: berat
        const val OBESITAS = "obesitas"
        const val GEMUK = "gemuk"
        const val NORMAL = "normal"
        const val KURUS = "kurus"
        const val SANGAT_KURUS = "sangat kurus"

        // statistics status: tinggi
        const val TINGGI = "tinggi"
        const val PENDEK = "pendek"
        const val SANGAT_PENDEK = "sangat pendek"

        // statistics status: lingkar kepala
        const val MAKROSEFALI = "makrosefali"
        const val MIKROSEFALI = "mikrosefali"
    }

}