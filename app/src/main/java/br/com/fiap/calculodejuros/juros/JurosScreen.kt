package br.com.fiap.calculodejuros.juros

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.calculodejuros.calculos.calcularJuros
import br.com.fiap.calculodejuros.calculos.calcularMontante
import br.com.fiap.calculodejuros.components.CaixaDeEntrada
import br.com.fiap.calculodejuros.components.CardResultado

/*
Feature que representa a tela para o cálculo de juros. Função principal,
responsável por manter o estado da nossa tela.
Necessário receber um parâmetro da sua respectiva ViewModel,
responsável por fazer todas as alterações de forma centralizada para essa tela.
 */
@Composable
fun JurosScreen(jurosScreenViewModel: JurosScreenViewModel) { // Passar a instância da Classe ViewModel como parâmetro

    // Variáveis para garantir o estado dos campos de entrada para toda renderização
    //Linha de código superada e substituída => var capital by remember { mutableStateOf("") }
    val capital by jurosScreenViewModel
        .capitalState
        .observeAsState(initial = "") // Acesso à variável capital do ViewModel (público)

    //var taxa by remember { mutableStateOf("") }
    val taxa by jurosScreenViewModel
        .taxaState
        .observeAsState(initial = "")

    //var tempo by remember { mutableStateOf("") }
    val tempo by jurosScreenViewModel
        .tempoState
        .observeAsState(initial = "")

    //var juros by remember { mutableStateOf(0.0) }
    val juros by jurosScreenViewModel
        .jurosState
        .observeAsState(initial = 0.0)

    //var montante by remember { mutableStateOf(0.0) }
    val montante by jurosScreenViewModel
        .montanteState
        .observeAsState(initial = 0.0)

    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Text(
                text = "Cálculo de Juros Simples",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            // Formulário para entrada de dados
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Dados do Investimento",
                        fontWeight = FontWeight.Bold
                    )

                    // Utilizando o componente CaixaDeEntrada para o valor do investimento
                    CaixaDeEntrada(
                        value = capital, // Variável que controla o estado a partir do componente pai
                        placeholder = "Quanto deseja investir?",
                        label = "Valor do investimento",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        keyboardType = KeyboardType.Decimal,
                    ){
                        jurosScreenViewModel.onCapitalChange(it) // Atribuir o valor do capital para a variável capital do ViewModel
                    }

                    // Utilizando o componente CaixaDeEntrada para a taxa de juros
                    CaixaDeEntrada(
                        value = taxa,
                        placeholder = "Qual a taxa de juros mensal?",
                        label = "Taxa de juros mensal",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        keyboardType = KeyboardType.Decimal,
                        {
                            jurosScreenViewModel.onTaxaChange(it)
                        }
                    )
                    // Utilizando o componente CaixaDeEntrada para o período de aplicação
                    CaixaDeEntrada(
                        value = tempo,
                        placeholder = "Qual o período de aplicação em meses?",
                        label = "Período em meses",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        keyboardType = KeyboardType.Decimal,
                        {
                            jurosScreenViewModel.onTempoChange(it)
                        }
                    )

                    // Botão para calcular
                    Button(
                        onClick = {
                            jurosScreenViewModel.calcularJurosInvestimento()
                            jurosScreenViewModel.calcularMontanteInvestimento()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                    ) {
                        Text(text = "CALCULAR")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Utilizando o componente CardResultado para exibir o resultado
            CardResultado(
                juros = juros,
                montante = montante
            )

        }
    }
}