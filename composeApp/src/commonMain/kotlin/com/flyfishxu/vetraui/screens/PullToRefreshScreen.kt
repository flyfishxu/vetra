package com.flyfishxu.vetraui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.ExperimentalVetraApi
import com.flyfishxu.vetraui.core.VetraCard
import com.flyfishxu.vetraui.core.VetraPullToRefresh
import com.flyfishxu.vetraui.core.theme.VetraTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Pull to Refresh Demo Screen
 *
 * A full-screen pull-to-refresh example showing realistic content updates.
 * Pull down anywhere on the screen to refresh the content list.
 */
@OptIn(ExperimentalVetraApi::class)
@Preview
@Composable
fun PullToRefreshScreen() {
    val colors = VetraTheme.colors
    val scope = rememberCoroutineScope()

    var refreshing by remember { mutableStateOf(false) }
    val items = remember {
        mutableStateListOf(
            NewsItem("New Feature Released", "Vetra UI now supports pull-to-refresh", "2 min ago", Icons.Default.Star),
            NewsItem("Design Update", "Updated color schemes and shadows", "15 min ago", Icons.AutoMirrored.Filled.Article),
            NewsItem("Community", "50+ developers joined this week", "1 hour ago", Icons.Default.Person),
            NewsItem("Announcement", "Version 1.0 coming soon", "3 hours ago", Icons.Default.Notifications)
        )
    }

    VetraPullToRefresh(
        refreshing = refreshing,
        onRefresh = {
            scope.launch {
                refreshing = true
                delay(1500)

                val newItem = NewsItem(
                    title = "Breaking News ${items.size + 1}",
                    description = "Fresh content just loaded!",
                    time = "Just now",
                    icon = Icons.Default.Star
                )
                items.add(0, newItem)
                
                refreshing = false
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.canvas),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // News items
            items(items) { item ->
                NewsItemCard(item)
            }

            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

/**
 * Data class for news items
 */
private data class NewsItem(
    val title: String,
    val description: String,
    val time: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

/**
 * News item card component
 */
@Composable
private fun NewsItemCard(item: NewsItem) {
    val colors = VetraTheme.colors
    val typography = VetraTheme.typography

    VetraCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Icon
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = colors.brand
            )

            // Content
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.title,
                    style = typography.headingMd.copy(color = colors.textPrimary)
                )
                Text(
                    text = item.description,
                    style = typography.bodyMd.copy(color = colors.textSecondary)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.time,
                    style = typography.bodySm.copy(color = colors.textTertiary)
                )
            }
        }
    }
}
