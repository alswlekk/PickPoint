package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme
import com.pickpoint.pickpoint.ui.theme.robotoFontFamily

@Composable
fun ResetConfirmButton(
    modifier: Modifier = Modifier,
    reset: () -> Unit,
    apply: () -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Button(
            onClick = { reset() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier
                .width(146.dp)
                .height(48.dp)
                .shadow(4.dp, RoundedCornerShape(100.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_trash_can),
                contentDescription = "Reset",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Reset",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Button(
            onClick = { apply() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.background,
            ),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier
                .width(146.dp)
                .height(48.dp)
                .shadow(4.dp, RoundedCornerShape(100.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Apply",
                tint = MaterialTheme.colorScheme.background
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Apply",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.background
            )
        }
    }

}


@Preview
@Composable
fun ResetConfirmButtonPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        ResetConfirmButton(
            reset = { /* Handle reset click */ },
            apply = { /* Handle apply click */ })
    }
}
