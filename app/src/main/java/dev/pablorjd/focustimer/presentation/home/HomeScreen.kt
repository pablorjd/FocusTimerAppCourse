package dev.pablorjd.focustimer.presentation.home

import AutoResizedText
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.pablorjd.focustimer.R
import dev.pablorjd.focustimer.presentation.components.BorderedIcon
import dev.pablorjd.focustimer.presentation.components.CircleDot
import dev.pablorjd.focustimer.presentation.components.CustomButton
import dev.pablorjd.focustimer.presentation.components.CustomButtonPreview
import dev.pablorjd.focustimer.presentation.components.InformationItem
import dev.pablorjd.focustimer.presentation.components.TimerTypeItem
import dev.pablorjd.focustimer.presentation.theme.FocusTimerTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(FocusTimerTheme.dimens.paddingMedium)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                modifier = Modifier.size(FocusTimerTheme.dimens.iconSizeNormal),
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        AutoResizedText(
            text = "Focus Timer",
            textStyle = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        CirclesDotHome()
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        TimerSession(timer = "1:50")
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        TimerTypeSession(

        )
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomButton(
                text = "Start",
                textColor = MaterialTheme.colorScheme.surface,
                buttonColor = MaterialTheme.colorScheme.primary,
                onTap = {
                    // TODO: implement Logic
                }
            )
            CustomButton(
                text = "Reset",
                textColor = MaterialTheme.colorScheme.primary,
                buttonColor = MaterialTheme.colorScheme.surface,
                onTap = {
                    // TODO: implement Logic
                }
            )
        }
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        InformationSession(
            round = "10",
            time = "45:00"
        )
    }
}

@Composable
private fun CirclesDotHome() {
    Row {
        CircleDot()
        Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
        CircleDot()
        Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
        CircleDot()
        Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
        CircleDot(color = MaterialTheme.colorScheme.tertiary)
        Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
        CircleDot(color = MaterialTheme.colorScheme.tertiary)
    }
}

@Composable
fun TimerSession(
    modifier: Modifier = Modifier,
    timer: String,
    onIncreaseTap: () -> Unit = {},
    onDecreaseTap: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BorderedIcon(icon = R.drawable.ic_minus, onTab = onDecreaseTap)
            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        }
        AutoResizedText(
            text = timer,
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .align(Alignment.CenterVertically),
            textStyle = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BorderedIcon(icon = R.drawable.ic_plus, onTab = onIncreaseTap)
            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        }
    }
}

@Composable
fun TimerTypeSession(
    modifier: Modifier = Modifier,
    onTap: () -> Unit = {}
) {
    val count = 3
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(FocusTimerTheme.dimens.spacerLarge),
        columns = GridCells.Fixed(count),
        horizontalArrangement = Arrangement.spacedBy(FocusTimerTheme.dimens.paddingNormal),
        verticalArrangement = Arrangement.spacedBy(FocusTimerTheme.dimens.paddingNormal)
    ) {
        item(
            key = "FT"
        ) {
            TimerTypeItem(
                text = "Focus Timer",
                textColor = MaterialTheme.colorScheme.primary
            )
        }
        item(
            key = "SB"
        ) {
            TimerTypeItem(
                text = "Short Break",
                textColor = MaterialTheme.colorScheme.secondary
            )
        }
        item(
            key = "LB"
        ) {
            TimerTypeItem(
                text = "Long Break",
                textColor = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun InformationSession(
    modifier: Modifier = Modifier,
    round: String,
    time: String
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            InformationItem(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = round,
                label = "rounds"
            )
            Spacer(modifier = modifier
                .fillMaxWidth()
                .weight(1f))
            InformationItem(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = time,
                label = "time"
            )
        }
    }
}

@Preview(
    name = "HomeScreenPreview",
    showBackground = true,
)
@Composable
fun HomeScreenPreview() {
    FocusTimerTheme {
        HomeScreen()
    }
}