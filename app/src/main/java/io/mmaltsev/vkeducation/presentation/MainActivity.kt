package io.mmaltsev.vkeducation.presentation



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// 1. Наша модель данных
data class AppInfo(
    val name: String,
    val description: String,
    val category: String
)

// 2. Захардкоженные данные
val sampleApps = listOf(
    AppInfo("VK", "Социальная сеть", "Социальные сети"),
    AppInfo("Telegram", "Мессенджер", "Общение"),
    AppInfo("YouTube", "Видео", "Развлечения"),
    AppInfo("Яндекс", "Поиск", "Инструменты"),
    AppInfo("Spotify", "Музыка", "Развлечения"),
    AppInfo("GitHub", "Код", "Инструменты")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme { // Оборачиваем в тему
                AppListScreen(sampleApps)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(apps: List<AppInfo>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список приложений") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Иконка",
                        modifier = Modifier.padding(8.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Акцентный цвет
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(apps) { app ->
                AppListItem(app)
            }
        }
    }
}

@Composable
fun AppListItem(app: AppInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Иконка (заглушка)
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = app.name, style = MaterialTheme.typography.titleMedium)
                Text(text = app.description, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Категория: ${app.category}", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }
    }
}