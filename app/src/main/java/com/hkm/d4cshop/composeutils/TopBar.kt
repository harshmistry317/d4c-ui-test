package com.hkm.d4cshop.composeutils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hkm.d4cshop.R
import com.hkm.d4cshop.ui.theme.InStockGreen

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    topBarText: String,
    onBackClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    onCenterButtonClick: () -> Unit,
    onLeftButtonClick: () -> Unit,
    likeBadgeCount: Int = 0,
    cartBadgeCount: Int = 0,
    showBadge : Boolean = true,
    rightButtonIcon: ImageVector,
    centerButtonIcon: ImageVector,
    leftButtonIcon: ImageVector,
    showRightButton: Boolean,
    showLeftButton: Boolean,
    showCenterButton: Boolean
) {

    Row(modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
    verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(32.dp).clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // removes ripple effect
            ) {
                onBackClick()
            },
            painter = painterResource(R.drawable.ic_back_arrow),
            contentDescription = "Back Arrow"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = topBarText,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        if (showLeftButton){
            IconWithBadge(icon = leftButtonIcon, count = 0, modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // removes ripple effect
            ) {
                onLeftButtonClick()
            }, showBadge = showBadge)
            Spacer(Modifier.width(4.dp))
        }
        if (showCenterButton){
            IconWithBadge(icon = centerButtonIcon, count = likeBadgeCount, modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // removes ripple effect
            ) {
                onCenterButtonClick()
            }, showBadge = showBadge)
            Spacer(Modifier.width(4.dp))
        }
        if (showRightButton) {
            IconWithBadge(icon = rightButtonIcon, count = cartBadgeCount, modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // removes ripple effect
            ) {
                onRightButtonClick()
            }, showBadge = showBadge)
        }




    }

}

@Composable
fun IconWithBadge(modifier: Modifier = Modifier,icon: ImageVector, count: Int,showBadge: Boolean) {
    Box(
        modifier = modifier.size(40.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center)
        )
        if (showBadge && count > 0){
            Box(
                modifier = Modifier
                    .offset(y = (-2).dp, x = (1).dp)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(InStockGreen),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (count >= 10) "9+" else count.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun TopBarPreview() {
    MaterialTheme {
        TopBar(
            topBarText = "Top Bar",
            onBackClick = {},
            onRightButtonClick = {},
            modifier = Modifier,
            onCenterButtonClick = {},
            onLeftButtonClick = {},
            likeBadgeCount = 5,
            cartBadgeCount = 3,
            rightButtonIcon = Icons.Default.ShoppingCart,
            centerButtonIcon = Icons.Default.Search,
            leftButtonIcon = Icons.Default.FavoriteBorder,
            showRightButton = true,
            showLeftButton = true,
            showCenterButton = true,
            showBadge = true
        )

    }

}