package com.example.online_shope.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.online_shope.R

@Composable
fun RatingBarRow(rating: Double, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "select Weight",
            fontWeight = FontWeight.Bold,
            modifier = modifier.weight(1f)
        )

        Image(
            painter = painterResource(R.drawable.star),
            contentDescription = null,
            modifier = modifier.padding(end = 8.dp)
        )

        Text(text = "$rating", style = MaterialTheme.typography.bodyMedium)

    }
}