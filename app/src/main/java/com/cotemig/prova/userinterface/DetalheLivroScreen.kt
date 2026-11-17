package com.cotemig.prova.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cotemig.prova.data.Livro
import com.cotemig.prova.viewmodel.LivroViewModel

@Composable
fun DetalheLivroScreen(viewModel: LivroViewModel, livroId: Int, aoVoltar: () -> Unit) {
    var livro by remember(livroId) { mutableStateOf<Livro?>(null) }
    val favoritos by viewModel.favoritoIds.collectAsState()

    LaunchedEffect(livroId) {
        livro = viewModel.buscarLivro(livroId)
    }

    val livroCarregado = livro
    if (livroCarregado == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(livroCarregado.titulo, style = MaterialTheme.typography.headlineMedium)
        Text("Autor: ${livroCarregado.autor}", style = MaterialTheme.typography.bodyLarge)
        Text("Páginas: ${livroCarregado.paginas}", style = MaterialTheme.typography.bodyLarge)
        Text("Resumo: ${livroCarregado.resumo}", style = MaterialTheme.typography.bodyLarge)

        Button(
            onClick = { viewModel.alternarFavorito(livroCarregado.id) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (livroCarregado.id in favoritos) " ★ Favorito" else " Favoritar")
        }

        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = aoVoltar, modifier = Modifier.fillMaxWidth()) {
            Text("← Voltar")
        }
    }
}
