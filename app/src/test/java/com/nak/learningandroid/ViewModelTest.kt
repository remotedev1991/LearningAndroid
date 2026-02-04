package com.nak.learningandroid

import app.cash.turbine.test
import com.nak.learningandroid.model.User
import com.nak.learningandroid.repository.UserRepository
import com.nak.learningandroid.viewmodel.MainViewModel
import com.nak.learningandroid.viewmodel.UiState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = mockk<UserRepository>()

    private lateinit var viewModel: MainViewModel


    @Test
    fun fetch_data_emits_succes_() = runTest {

        val mockUsers = listOf<User>(User(name = "John", address = "New York", age = 20))

        coEvery { repository.fetchData() } returns flowOf(mockUsers)

        viewModel = MainViewModel(repository)

        viewModel.uiState.test {

            viewModel.fetchData()

            assertEquals(UiState.Loading, awaitItem())

            val item = awaitItem() as UiState.Success

            assertEquals(UiState.Success(mockUsers), item)

        }
    }


    @Test
    fun fetch_data_emits_error_() = runTest {

        val mockUsers = listOf<User>()

        coEvery { repository.fetchData() } returns flowOf(mockUsers)

        viewModel = MainViewModel(repository)

        viewModel.uiState.test {

            viewModel.fetchData()

            assertEquals(UiState.Loading, awaitItem()) //

            assertEquals(UiState.Loading, awaitItem()) //

            val item = awaitItem() as UiState.Error

            assertEquals(UiState.Error("No data"), item)

        }
    }


    // 1 -> Loading
    // 2 -> Success


}