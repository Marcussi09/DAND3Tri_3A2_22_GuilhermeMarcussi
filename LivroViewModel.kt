package com.cotemig.prova

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LivroViewModel : ViewModel() {
    // TODO (B): a lista de livros deve ser carregada e exposta aqui

    private val _favoritoIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoritoIds: StateFlow<Set<Int>> = _favoritoIds.asStateFlow()

    suspend fun buscarLivro(id: Int): Livro? = LivroRepository.buscarPorId(id)

    fun alternarFavorito(id: Int) {
        _favoritoIds.update { if (id in it) it - id else it + id }
    }
}
