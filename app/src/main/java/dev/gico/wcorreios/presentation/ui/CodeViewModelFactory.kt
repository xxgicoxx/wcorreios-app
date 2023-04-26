package dev.gico.wcorreios.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.gico.wcorreios.data.repository.CodeRepository

class CodeViewModelFactory constructor(private val repository: CodeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CodeViewModel::class.java)){
            return CodeViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel Not Found")
    }
}
