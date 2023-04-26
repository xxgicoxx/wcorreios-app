package dev.gico.wcorreios.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.gico.wcorreios.MyApplication
import dev.gico.wcorreios.R
import dev.gico.wcorreios.data.entity.Code
import dev.gico.wcorreios.data.repository.CorrestRepository
import dev.gico.wcorreios.data.service.CorrestService
import dev.gico.wcorreios.presentation.ui.CodesList
import dev.gico.wcorreios.data.domain.MenuHeader
import dev.gico.wcorreios.presentation.theme.AppTheme
import dev.gico.wcorreios.presentation.ui.CodeViewModel
import dev.gico.wcorreios.presentation.ui.CodeViewModelFactory
import dev.gico.wcorreios.presentation.ui.CorrestViewModel
import dev.gico.wcorreios.presentation.ui.CorrestViewModelFactory

class CodesActivity : ComponentActivity() {

    private lateinit var codeViewModel: CodeViewModel
    private lateinit var correstViewModel: CorrestViewModel
    private val correstService = CorrestService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        codeViewModel = ViewModelProvider(this, CodeViewModelFactory((application as MyApplication).codeRepository)).get(
            CodeViewModel::class.java
        )

        correstViewModel = ViewModelProvider(this, CorrestViewModelFactory(CorrestRepository(correstService))).get(
            CorrestViewModel::class.java
        )

        val menuHeader = MenuHeader(getString(R.string.app_name))

        setContent {
            AppTheme {
                val codes: MutableList<Code> by codeViewModel.codes.collectAsStateWithLifecycle(
                    initialValue = mutableListOf(),
                    lifecycleOwner = this
                )

                CodesList(menuHeader, codes, correstViewModel, codeViewModel)
            }
        }
    }
}
