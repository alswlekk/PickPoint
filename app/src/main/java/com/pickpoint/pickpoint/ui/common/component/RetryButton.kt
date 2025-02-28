package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun RetryButton(
    modifier: Modifier = Modifier,
    retry: () -> Unit
) {

    Button(
        onClick = { retry() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.background,
        ),
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .shadow(4.dp, RoundedCornerShape(100.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_retry),
            contentDescription = "Retry",
            tint = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Retry",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.background
        )
    }

}

@Preview(showBackground = true)
@Composable
fun RetryButtonPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        RetryButton(retry = { /* Handle retry click */ })
    }
}