package ar.edu.unlam.mobile.scaffolding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.components.BottomNavigationBar
import ar.edu.unlam.mobile.scaffolding.ui.navigation.AppNavigation
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Screen
import ar.edu.unlam.mobile.scaffolding.ui.theme.ScaffoldingV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldingV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val screenWithBottomBar =
        listOf(
            Screen.Feed.route,
            Screen.Profile.route,
            Screen.FavoriteUsers.route,
        )

    val normalizedRoute = currentRoute?.substringBefore("?")

    val showBottomBar = normalizedRoute in screenWithBottomBar

    val listState = rememberLazyListState()

    var isBottomVarVisible by remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        var lastScrollIndex = 0
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { currentIndex ->
                isBottomVarVisible = currentIndex <= lastScrollIndex
                lastScrollIndex = currentIndex
            }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                AnimatedVisibility(
                    visible = isBottomVarVisible,
                    enter = expandVertically(expandFrom = Alignment.Bottom) + fadeIn(),
                    exit = shrinkVertically(shrinkTowards = Alignment.Bottom) + fadeOut(),
                ) {
                    BottomNavigationBar(controller = navController)
                }
            }
        },
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
        ) {
            AppNavigation(navController = navController, listState = listState)
        }
    }
}
