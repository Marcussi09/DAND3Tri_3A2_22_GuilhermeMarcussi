package com.cotemig.prova.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cotemig.prova.data.Livro
import com.cotemig.prova.data.LivroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LivroViewModel : ViewModel() {
    var livros by mutableStateOf<List<Livro>>(emptyList())
        private set

    private val _favoritoIds = MutableStateFlow<Set<Int>>(emptySet())
    val favoritoIds: StateFlow<Set<Int>> = _favoritoIds.asStateFlow()

    init {
        viewModelScope.launch {
            livros = LivroRepository.listar()
        }
    }

    suspend fun buscarLivro(id: Int): Livro? = LivroRepository.buscarPorId(id)

    fun alternarFavorito(id: Int) {
        _favoritoIds.update { if (id in it) it - id else it + id }
    }
}
