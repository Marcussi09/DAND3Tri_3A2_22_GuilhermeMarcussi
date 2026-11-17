package com.cotemig.prova

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface Rota {
    @Serializable
    data object Lista : Rota

    @Serializable
    data class Detalhe(val livroId: Int) : Rota
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: LivroViewModel = viewModel()

    NavHost(navController = navController, startDestination = Rota.Lista) {
        composable<Rota.Lista> {
            ListaLivroScreen(
                viewModel = viewModel,
                aoClicar = { id -> /* TODO (D-1): navegue para Rota.Detalhe(id) */ }
            )
        }
        // TODO (D-2): registre aqui composable<Rota.Detalhe> e chame DetalheLivroScreen
    }
}
