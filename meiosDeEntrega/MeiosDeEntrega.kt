//interface que pode ser utilizada por várias classes
interface MeioDeEntrega {
    fun calcularTempo(distancia: Double): Double
    fun calcularCusto(distancia: Double): Double
    fun podeOperar(condicoesClimaticas: String): Boolean
    val nome: String
}