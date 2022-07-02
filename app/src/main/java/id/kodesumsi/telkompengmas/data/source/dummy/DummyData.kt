package id.kodesumsi.telkompengmas.data.source.dummy

import id.kodesumsi.telkompengmas.domain.model.*
import id.kodesumsi.telkompengmas.utils.Constant.Companion.MAN
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_PARENT
import id.kodesumsi.telkompengmas.utils.Constant.Companion.ROLE_POSYANDU
import id.kodesumsi.telkompengmas.utils.Constant.Companion.WOMAN

object DummyData {

    fun getCurrentUser(role: Int): User {
        return when (role) {
            ROLE_POSYANDU -> {
                User(
                    email = "hilmanmuttaqin345@gmail.com",
                    name = "Hilman Taris Muttaqin",
                    role = ROLE_POSYANDU,
                    pass = "testing1234",
                    phone = "081214738859"
                )
            }
            ROLE_PARENT -> {
                User(
                    email = "hilmanmuttaqin345@gmail.com",
                    name = "Hilman Taris Muttaqin",
                    role = ROLE_PARENT,
                    pass = "testing123",
                    phone = "081214738859"
                )
            }
            else -> {
                User(
                    email = "hilmanmuttaqin345@gmail.com",
                    name = "Hilman Taris Muttaqin",
                    role = ROLE_PARENT,
                    pass = "testing123",
                    phone = "081214738859"
                )
            }
        }
    }

    fun getPosyandus(): List<Posyandu> {
        val result: MutableList<Posyandu> = mutableListOf()

        val posyandu1 = Posyandu(
            id = 1,
            thumbUrl = "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=1h75Uc54Clt_nLRMLiRRwg&cb_client=search.gws-prod.gps&w=408&h=240&yaw=220.32874&pitch=0&thumbfov=100",
            lat = "-6.9627092",
            lng = "107.6483161",
            name = "Posyandu Jakapurwa",
            status = true,
            address = "2JPX+W88, Jl. Jaka Utama, Kujangsari, Kec. Bandung Kidul, Kota Bandung, Jawa Barat 40287",
        )
        result.add(posyandu1)

        val posyandu2 = Posyandu(
            id = 2,
            thumbUrl = null,
            lat = "-6.952872",
            lng = "107.6789227",
            name = "Posyandu Dan Posbindu Purnawati",
            status = true,
            address = "Jl. keadilan I No.6, Derwati, Kec. Rancasari, Kota Bandung, Jawa Barat 40292",
        )
        result.add(posyandu2)

        val posyandu3 = Posyandu(
            id = 3,
            thumbUrl = "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=B5Zlg4ucNMEmA_GSyq0RSQ&cb_client=search.gws-prod.gps&w=408&h=240&yaw=60.76402&pitch=0&thumbfov=100",
            lat = "-6.9494587",
            lng = "107.6807477",
            name = "Posyandu Mawar IX",
            status = true,
            address = "Jl. keadilan I No.6, Derwati, Kec. Rancasari, Kota Bandung, Jawa Barat 40292",
        )
        result.add(posyandu3)

        val posyandu4 = Posyandu(
            id = 4,
            thumbUrl = null,
            lat = "-6.9316567",
            lng = "107.6356286",
            name = "Posyandu Melati",
            status = true,
            address = "Lkr. Sel., Kec. Lengkong, Kota Bandung, Jawa Barat",
        )
        result.add(posyandu4)

        return result
    }

    fun getArticles(): List<Article> {
        val result: MutableList<Article> = mutableListOf()

        val article0 = Article(
            id = 0,
            thumbUrl = null,
            title = "Mengenal lebih dekat dengan stunting",
            author = "Telkom Pengmas",
            tag = listOf("Stunting", "Pendidikan", "Anak"),
            url = null
        )
        result.add(article0)

        val article1 = Article(
            id = 1,
            thumbUrl = "https://pendidikan.id/news/wp-content/uploads/2019/08/01-practice-ways-get-kids-off-phones-without-bribery-37707798-gpointstudio-760x506-300x200.jpg",
            title = "Persiapkan Pendidikan Anak Usia Dini dengan Gadget, Ajak Anak Bermain Sambil Belajar",
            author = "Pendidikan.id",
            tag = listOf("Pendidikan", "Anak", "Gadget"),
            url = "https://pendidikan.id/news/persiapkan-pendidikan-anak-usia-dini-dengan-gadget-ajak-anak-bermain-sambil-belajar/"
        )
        result.add(article1)

        val article2 = Article(
            id = 2,
            thumbUrl = "https://pendidikan.id/news/wp-content/uploads/2017/10/trailer-cacat-mata-300x300.png",
            title = "Komik Edukasi Kesehatan: Tips Menjaga Mata Sehat, Jauh dari Risiko Cacat Mata",
            author = "Pendidikan.id",
            tag = listOf("Kesehatan", "Edukasi", "Mata"),
            url = "https://pendidikan.id/news/komik-edukasi-kesehatan-tips-menjaga-mata-sehat-jauh-dari-risiko-cacat-mata/"
        )
        result.add(article2)

        val article3 = Article(
            id = 3,
            thumbUrl = "https://pendidikan.id/news/wp-content/uploads/2017/08/trailer-mois-300x300.png",
            title = "Petualangan Hebat si Mois, Komik Literasi Menarik Sebagai Bahan Cerita untuk Anak Balita",
            author = "Pendidikan.id",
            tag = listOf("Literasi", "Cerita", "Balita"),
            url = "https://pendidikan.id/news/petualangan-hebat-si-mois-komik-literasi-menarik-sebagai-bahan-cerita-untuk-si-kecil/"
        )
        result.add(article3)

        return result
    }

    fun getDoctors(): List<Doctor> {
        val result: MutableList<Doctor> = mutableListOf()

        val dokter1 = Doctor(id = 1, name = "Dr.Hilman Taris M", phone = "https://api.whatsapp.com/send?phone=6281214738859")
        result.add(dokter1)

        val dokter2 = Doctor(id = 2, name = "Dr.Lasman Simbolon", phone = "https://api.whatsapp.com/send?phone=6282137735310")
        result.add(dokter2)

        return result
    }

    fun getChilds(): List<Child> {
        val result: MutableList<Child> = mutableListOf()

        val child1 = Child(
            id = 1,
            name = "Dandi Sudarman",
            surname = "Dandi",
            birthDate = "08-04-02",
            height = 80.0f,
            weight = 10.0f,
            headCircumference = 100.0f,
            gender = MAN
        )
        result.add(child1)

        val child2 = Child(
            id = 2,
            name = "Siti Nurbaya",
            surname = "Siti",
            birthDate = "08-04-02",
            height = 80.0f,
            weight = 10.0f,
            headCircumference = 100.0f,
            gender = WOMAN
        )
        result.add(child2)

        val child3 = Child(
            id = 3,
            name = "Asep Dadang",
            surname = "Asep",
            birthDate = "08-04-02",
            height = 80.0f,
            weight = 10.0f,
            headCircumference = 100.0f,
            gender = MAN
        )
        result.add(child3)

        return result
    }

}