package com.elshafee.androiden.todolistapi.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androiden.databinding.ActivityTodoListApiAppBinding
import com.elshafee.androiden.todolistapi.services.RetrofiinstanceTodoApi
import com.elshafee.androiden.todolistapi.ui.utils.TodoApiAdapter
import retrofit2.HttpException
import java.io.IOException

const val TAG = "TodoListAPI"

class TodoListApiApp : AppCompatActivity() {
    private lateinit var binding: ActivityTodoListApiAppBinding
    private lateinit var todoApiAdapter: TodoApiAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListApiAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        lifecycleScope.launchWhenCreated {
            binding.progressBarTodoApi.isVisible = true

            val response = try {
                RetrofiinstanceTodoApi.api.getTodos()
            } catch (e: IOException) {
                Log.d(TAG, "IOException, You have no internet connection")
                binding.progressBarTodoApi.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.d(TAG, "HttpException, unexpected response")
                binding.progressBarTodoApi.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                todoApiAdapter.todos = response.body()!!
            } else {
                Log.d(TAG, "Response Not Successful")

            }
            binding.progressBarTodoApi.isVisible = false
        }

    }

    private fun setupRecyclerView() = binding.rvTodoApi.apply {
        todoApiAdapter = TodoApiAdapter()
        adapter = todoApiAdapter
        layoutManager = LinearLayoutManager(this@TodoListApiApp)
    }
}