package dev.gico.wcorreios.presentation

import android.app.Activity
import android.app.RemoteInput
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.wear.input.RemoteInputIntentHelper
import androidx.wear.input.wearableExtender
import dev.gico.wcorreios.MyApplication
import dev.gico.wcorreios.R
import dev.gico.wcorreios.data.entity.Code
import dev.gico.wcorreios.presentation.ui.MainMenu
import dev.gico.wcorreios.presentation.theme.AppTheme
import dev.gico.wcorreios.presentation.ui.CodeViewModel
import dev.gico.wcorreios.presentation.ui.CodeViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var codeViewModel: CodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        codeViewModel = ViewModelProvider(this, CodeViewModelFactory((application as MyApplication).codeRepository)).get(
            CodeViewModel::class.java
        )

        setContent {
            AppTheme {
                MainMenu(
                    onClickNewCodeActivity = {
                        openNewCodeActivity()
                    },
                    onClickCodesActivity = {
                        openCodesActivity()
                    }
                )
            }
        }
    }

    private fun openNewCodeActivity() {
        val intent: Intent = RemoteInputIntentHelper.createActionRemoteInputIntent()

        val remoteInputs: List<RemoteInput> = listOf(
            RemoteInput.Builder(RESULT_KEY)
                .wearableExtender {
                    setEmojisAllowed(false)
                    setInputActionType(EditorInfo.IME_ACTION_DONE)
                }.build()
        )

        RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)

        startForResult.launch(intent)
    }

    private fun openCodesActivity() {
        val intent = Intent(this, CodesActivity::class.java)
        startActivity(intent)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val results: Bundle = RemoteInput.getResultsFromIntent(result.data)
                val newCode: CharSequence? = results.getCharSequence(RESULT_KEY)

                codeViewModel.insert(Code(newCode.toString()))

                Toast.makeText(this, "${getString(R.string.code)} $newCode ${getString(R.string.added)}", Toast.LENGTH_SHORT).show()
            }
        }

    companion object {
        private const val RESULT_KEY = "new_code"
    }
}
