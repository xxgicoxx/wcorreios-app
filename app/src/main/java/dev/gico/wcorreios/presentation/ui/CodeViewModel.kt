package dev.gico.wcorreios.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gico.wcorreios.data.entity.Code
import dev.gico.wcorreios.data.repository.CodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CodeViewModel(private val codeRepository: CodeRepository) : ViewModel() {

    val codes : Flow<MutableList<Code>> = codeRepository.codes

    fun insert(code: Code) = viewModelScope.launch {
        codeRepository.insert(code)
    }

    fun delete(code: Code) = viewModelScope.launch {
        codeRepository.delete(code)
    }

    fun deleteById(code: String) = viewModelScope.launch {
        codeRepository.deleteById(code)
    }
}
