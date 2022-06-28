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



        return result
    }

    fun getArticles(): List<Article> {
        val result: MutableList<Article> = mutableListOf()

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