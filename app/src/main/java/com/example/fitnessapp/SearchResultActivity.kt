package com.example.fitnessapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.data.SearchItem
import android.widget.EditText

class SearchResultActivity : AppCompatActivity() {
    private lateinit var searchBar: EditText
    private lateinit var searchResultsRv: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    
    private val workoutItems = listOf(
        SearchItem("Iron Chest", "Chest workout program", "workout"),
        SearchItem("Wings of Steel", "Back workout program", "workout"),
        SearchItem("Ultimate Abs", "Core workout program", "workout"),
        SearchItem("Full Throttle", "Cardio workout program", "workout"),
        SearchItem("Lose Belly Fat", "Fat loss program", "plan"),
        SearchItem("Boost Your Endurance", "Endurance training program", "plan"),
        SearchItem("Build Muscle Mass", "Muscle building program", "plan")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        searchBar = findViewById(R.id.search_bar)
        searchResultsRv = findViewById(R.id.search_results_rv)

        setupRecyclerView()
        setupSearchBar()
        searchAdapter.updateList(workoutItems)
    }

    private fun setupSearchBar() {
        searchBar.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                filterResults(s.toString())
            }
        })
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter(mutableListOf()) { item ->
            when (item.type) {
                "workout" -> {
                    when (item.title) {
                        "Iron Chest" -> startActivity(android.content.Intent(this, ChestWorkoutActivity::class.java))
                        "Wings of Steel" -> startActivity(android.content.Intent(this, BackWorkoutActivity::class.java))
                        // Add other workout activities as needed
                    }
                }
                "plan" -> {
                    // Handle plan clicks
                }
            }
        }
        searchResultsRv.apply {
            layoutManager = LinearLayoutManager(this@SearchResultActivity)
            adapter = searchAdapter
        }
    }

    private fun filterResults(query: String) {
        val filteredList = if (query.isEmpty()) {
            workoutItems  // Show all items when search is empty
        } else {
            workoutItems.filter {
                it.title.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
            }
        }
        searchAdapter.updateList(filteredList)
    }
} 