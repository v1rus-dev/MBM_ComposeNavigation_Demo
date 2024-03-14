package yegor.cheprasov.mbm_design.di

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val bottomSheetItemTextStyle = TextStyle(
    color = Color.Black,
    fontSize = 14.sp,
    fontWeight = FontWeight.Medium
)

@Composable
fun AppBottomSheet(
    modifier: Modifier = Modifier,
    noteIsFavorite: Boolean,
    toggleFavorite: () -> Unit,
    removeNote: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            BottomSheetItem(modifier = Modifier.clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = rememberRipple()
            ) {
                toggleFavorite()
            }) { modifier ->
                AnimatedContent(
                    modifier = modifier,
                    targetState = noteIsFavorite,
                    label = "Animate favorite action text"
                ) { isFavorite ->
                    if (isFavorite) {
                        Text(text = "Удалить из избранного", style = bottomSheetItemTextStyle)
                    } else {
                        Text(text = "Добавить в избранное", style = bottomSheetItemTextStyle)
                    }
                }
            }
            BottomSheetItem(modifier = Modifier.clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = rememberRipple()
            ) {
                removeNote()
            }
            ) { modifier ->
                Text(text = "Удалить заметку", style = bottomSheetItemTextStyle, modifier = modifier)
            }
        }
    }
}

@Composable
private fun BottomSheetItem(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.(Modifier) -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content(Modifier.padding(vertical = 16.dp, horizontal = 16.dp))
    }
}

@Preview
@Composable
fun PreviewAppBottomSheet() {
    AppBottomSheet(noteIsFavorite = true, toggleFavorite = {}, removeNote = {})
}