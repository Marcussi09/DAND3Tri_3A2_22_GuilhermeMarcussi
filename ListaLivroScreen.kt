package com.cotemig.prova

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ListaLivroScreen(viewModel: LivroViewModel, aoClicar: (Int) -> Unit) {
    val favoritos by viewModel.favoritoIds.collectAsState()
    // Violação da Regra de Dependência: a tela fala direto com o Repository (Letra B)
    var itens by remember { mutableStateOf<List<Livro>>(emptyList()) }
    LaunchedEffect(Unit) { itens = LivroRepository.listar() }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(itens) { item ->
            Card(modifier = Modifier.fillMaxWidth().clickable { aoClicar(item.id) }) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(item.titulo, style = MaterialTheme.typography.titleMedium)
                        Text(item.autor, style = MaterialTheme.typography.bodyMedium)
                    }
                    if (item.id in favoritos) Text("★")
                }
            }
        }
    }
}
