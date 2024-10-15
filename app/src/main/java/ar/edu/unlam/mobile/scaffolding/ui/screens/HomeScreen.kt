package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitFeed

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val tuits by viewModel.tuits.collectAsState()

    TuitFeed(tuits)
}
