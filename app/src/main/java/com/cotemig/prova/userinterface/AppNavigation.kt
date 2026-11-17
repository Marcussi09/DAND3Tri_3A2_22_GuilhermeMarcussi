package com.cotemig.prova.userinterface

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cotemig.prova.viewmodel.LivroViewModel
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
    val livroViewModel: LivroViewModel = viewModel()

    NavHost(navController = navController, startDestination = Rota.Lista) {
        composable<Rota.Lista> {
            ListaLivroScreen(
                viewModel = livroViewModel,
                aoClicar = { id -> navController.navigate(Rota.Detalhe(id)) }
            )
        }
        composable<Rota.Detalhe> { backStackEntry ->
            val rota = backStackEntry.toRoute<Rota.Detalhe>()
            DetalheLivroScreen(
                viewModel = livroViewModel,
                livroId = rota.livroId,
                aoVoltar = { navController.popBackStack() }
            )
        }
    }
}
