package dev.gico.wcorreios.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.gico.wcorreios.data.repository.CorrestRepository

class CorrestViewModelFactory constructor(private val repository: CorrestRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CorrestViewModel::class.java)) {
            return CorrestViewModel(this.repository) as T
        }

        throw IllegalArgumentException("ViewModel Not Found")
    }
}
