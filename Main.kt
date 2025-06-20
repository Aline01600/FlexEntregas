/*
*  Seminário 2 - Paradigmas de Programação.
*  Discentes: Aline Soares, João Vitor Ribeiro, Maria Fernanda Vicente.
* */

open class MeioTransporte() {
    open val nome: String = "Desconhecido"
    open val precoCombustivelPorKm: Float = 0f

    open fun calcularTempo(distanciaKm: Float, chovendo: Boolean): Float {
        return 0f
    }

    open fun calcularCusto(distanciaKm: Float): Float {
        return distanciaKm * precoCombustivelPorKm
    }

    open fun podeEntregar(chovendo: Boolean): Boolean {
        return true
    }
}

class Moto : MeioTransporte() {
    override val nome = "Moto"
    override val precoCombustivelPorKm = 0.178f // valor medio do litro: 6,22 | media de km/l de uma moto: 35km/l

    override fun calcularTempo(distanciaKm: Float, chovendo: Boolean): Float {
        return if (chovendo) {
            distanciaKm / 40f // velocidade média de 40Km/h com chuva
        } else {
            distanciaKm / 80f // velocidade média de 80Km/h sem chuva
        }
    } // fim de calcularTempo
}

class Bicicleta : MeioTransporte() {
    override val nome = "Bicicleta"

    override fun calcularTempo(distanciaKm: Float, chovendo: Boolean): Float {
        return if (chovendo) {
            distanciaKm / 15f // velocidade média de 15Km/h com chuva
        } else {
            distanciaKm / 25f // velocidade média de 25Km/h sem chuva
        }
    } // fim de calcularTempo
}

class Drone : MeioTransporte() {
    override val nome = "Drone"

    override fun calcularTempo(distanciaKm: Float, chovendo: Boolean): Float {
        return if (podeEntregar(chovendo)) {
            distanciaKm / 80f // velocidade média baseada nos drones da Amazon Prime Air
        } else {
            -1f
        }
    } // fim de calcularTempo

    override fun podeEntregar(chovendo: Boolean): Boolean {
        return !chovendo
    }

}

class Cavalo : MeioTransporte() {
    override val nome = "Cavalo"

    override fun calcularTempo(distanciaKm: Float, chovendo: Boolean): Float {
        return if (chovendo) {
            distanciaKm / 10f // velocidade média de 10Km/h com chuva
        } else {
            distanciaKm / 20f // velocidade média de 20Km/h sem chuva
        }
    } // fim de calcularTempo
}

fun simularEntrega(meio: MeioTransporte, distancia: Float, chovendo: Boolean, regiao: String) {

    println("=== ${meio.nome} ===")

    if (regiao == "Rural" && meio.nome != "Cavalo") {
        println("Não disponível para sua região!\n")
        return
    } else if (regiao == "Urbano" && meio.nome == "Cavalo") {
        println("Não disponível para sua região!\n")
        return
    }

    if (!meio.podeEntregar(chovendo)) {
        println("Não pode entregar na chuva!\n")
        return
    }

    val tempo = meio.calcularTempo(distancia, chovendo)
    val custo = meio.calcularCusto(distancia)

    println("Tempo estimado: %.2f horas".format(tempo))
    println("Custo de combustível estimado: R$ %.2f\n".format(custo))
}
fun main() {
    val distancia = 30f
    val chovendo = true
    val regiao = "Urbano"

    println("\nDADOS:")
    println("Distancia: $distancia Km")
    println((if (chovendo) "Está" else "Não está") + " chovendo")

    val meios = listOf(
        Moto(),
        Bicicleta(),
        Drone(),
        Cavalo()
    )

    println()
    for (meio in meios) {
        simularEntrega(meio, distancia, chovendo, regiao)
    }
}