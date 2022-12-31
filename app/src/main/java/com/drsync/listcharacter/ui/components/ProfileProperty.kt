package com.drsync.listcharacter.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ProfileProperty(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Divider()
        Text(
            text = label,
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.caption
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.SemiBold
            ),
            overflow = TextOverflow.Visible
        )
    }
}