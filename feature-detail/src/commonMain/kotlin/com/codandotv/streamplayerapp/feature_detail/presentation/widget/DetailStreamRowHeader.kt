package com.codandotv.streamplayerapp.feature_detail.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import streamplayerapp_kmp.feature_detail.generated.resources.Res
import streamplayerapp_kmp.feature_detail.generated.resources.detail_movie
import streamplayerapp_kmp.feature_detail.generated.resources.netflix_detail

@Composable
fun DetailStreamRowHeader(
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(Res.drawable.netflix_detail),
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .offset(x = (-6).dp)

        )
        Text(
            text = stringResource(Res.string.detail_movie),
            modifier = Modifier.offset(x = (-6).dp),
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                letterSpacing = 0.3.em
            )
        )
    }
}