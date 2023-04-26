package dev.gico.wcorreios.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.gico.wcorreios.data.domain.ApiResponse
import dev.gico.wcorreios.data.domain.CorrestData
import dev.gico.wcorreios.data.repository.CorrestRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CorrestViewModel(private val repository: CorrestRepository) : ViewModel() {

    val correstData = MutableLiveData<ApiResponse>()
    val errorMessage = MutableLiveData<ApiResponse>()

    fun getTrack(code: String) {
        val response = repository.getTrack(code)

        response.enqueue(object : Callback<CorrestData> {
            override fun onResponse(call: Call<CorrestData>, response: Response<CorrestData>) {
                if(!response.isSuccessful) {
                    var apiResponse = ApiResponse(httpCode = response.code())

                    apiResponse.errorMessage = code

                    errorMessage.postValue(apiResponse)

                    return
                }

                var apiResponse = ApiResponse(httpCode = response.code(), data = response.body())

                correstData.postValue(apiResponse)
            }

            override fun onFailure(call: Call<CorrestData>, t: Throwable) {
                var apiResponse = ApiResponse(httpCode = 500)

                apiResponse.errorMessage = code

                errorMessage.postValue(apiResponse)
            }
        })
    }
}
