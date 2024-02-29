package br.com.fiap.calculodejuros.juros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.calculodejuros.calculos.calcularJuros
import br.com.fiap.calculodejuros.calculos.calcularMontante

// Classe que representa o ViewModel da tela de cálculo de juros
/*
LiveData é uma classe que é usada para armazenar dados observáveis.
Na função JurosScreen há os atributos observadores.
Com isso, mantêm-se os dados na ViewModel e não mais na tela (JurosScrenn).
 */
class JurosScreenViewModel : ViewModel() {
    // Variáveis que serão observadas na tela. Elas representam o estado da tela
    /// Criar um observável e um observador para o atribuito capital.
    private val _capital =
        MutableLiveData<String>() // Underscore para indicar que é uma variável privada.
    val capitalState: MutableLiveData<String> =
        _capital // Variável pública que será observada na tela.

    private val _taxa = MutableLiveData<String>()
    val taxaState: LiveData<String> = _taxa

    private val _tempo = MutableLiveData<String>()
    val tempoState: LiveData<String> = _tempo

    private val _juros = MutableLiveData<Double>()
    val jurosState: LiveData<Double> = _juros

    private val _montante = MutableLiveData<Double>()
    val montanteState: LiveData<Double> = _montante


    // Funções que serão chamadas quando os valores forem alterados
    fun onCapitalChange(novoCapital: String) {
        _capital.value = novoCapital
    }

    fun onTaxaChange(novaTaxa: String) {
        _taxa.value = novaTaxa
    }

    fun onTempoChange(novoTempo: String) {
        _tempo.value = novoTempo
    }


    fun calcularJurosInvestimento() {
        _juros.value = calcularJuros(
            capital = _capital.value!!.toDouble(),
            taxa = _taxa.value!!.toDouble(),
            tempo = _tempo.value!!.toDouble()
        )
    }

    fun calcularMontanteInvestimento() {
        _montante.value = calcularMontante(
            _capital.value!!.toDouble(),
            _juros.value!!.toDouble(),
        )
    }


}