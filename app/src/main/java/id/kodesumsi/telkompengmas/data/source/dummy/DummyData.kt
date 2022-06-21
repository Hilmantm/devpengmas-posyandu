package id.kodesumsi.telkompengmas.data.source.dummy

import id.kodesumsi.telkompengmas.domain.model.dummy.Child
import id.kodesumsi.telkompengmas.domain.model.dummy.User

class DummyData {

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
            ROLE_USER -> {
                User(
                    email = "hilmanmuttaqin345@gmail.com",
                    name = "Hilman Taris Muttaqin",
                    role = ROLE_USER,
                    pass = "testing123",
                    phone = "081214738859"
                )
            }
            else -> {
                User(
                    email = "hilmanmuttaqin345@gmail.com",
                    name = "Hilman Taris Muttaqin",
                    role = ROLE_USER,
                    pass = "testing123",
                    phone = "081214738859"
                )
            }
        }
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
            gender = MEN
        )
        result.add(child1)

        val child2 = Child(
            id = 2,
            name = "Iwan Sujatmiko",
            surname = "Iwan",
            birthDate = "08-04-02",
            height = 80.0f,
            weight = 10.0f,
            headCircumference = 100.0f,
            gender = MEN
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
            gender = MEN
        )
        result.add(child3)

        return result
    }

    companion object {
        const val ROLE_POSYANDU = 1
        const val ROLE_USER = 2

        const val WOMAN = 1
        const val MEN = 2
    }

}