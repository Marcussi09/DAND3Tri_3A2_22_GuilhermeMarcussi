package com.cotemig.prova

import kotlinx.coroutines.delay

object LivroRepository {
    private val itens = listOf(
        Livro(1, "Dom Casmurro", "Machado de Assis", 256, "Bentinho narra a dúvida sobre a fidelidade de Capitu."),
        Livro(2, "Grande Sertão: Veredas", "Guimarães Rosa", 608, "Riobaldo conta sua vida de jagunço pelo sertão mineiro."),
        Livro(3, "Memórias Póstumas de Brás Cubas", "Machado de Assis", 368, "Um defunto autor narra sua vida com ironia."),
        Livro(4, "Vidas Secas", "Graciliano Ramos", 176, "A luta de uma família de retirantes contra a seca."),
        Livro(5, "Capitães da Areia", "Jorge Amado", 280, "Meninos de rua vivem em um trapiche em Salvador.")
    )

    // Simula uma chamada de rede: roda fora da Main e demora um pouco
    suspend fun listar(): List<Livro> {
        delay(300)
        return itens
    }

    suspend fun buscarPorId(id: Int): Livro? {
        delay(800)
        return itens.find { it.id == id }
    }
}
