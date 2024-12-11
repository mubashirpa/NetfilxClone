package com.netflix.clone.presentation.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.netflix.clone.domain.model.Game

class GamesViewModel : ViewModel() {
    var uiState by mutableStateOf(GamesUiState())
        private set

    init {
        val games =
            listOf(
                Game(
                    id = 1,
                    image = "https://dnm.nflximg.net/api/v6/2DuQlx0fM4wd1nzqm5BFBi6ILa8/AAAAQXlHfX_1WN1bSiZv4jXieBsZbXRLNIUU9BPzv0Cohep2F2pmqrMzmWwRp7anXyBLRGhrBKMOuoAZoj9v8Si3-mJ6o2-5bc6o2YZtsJSIgpOiM4gcLAETYcGsppaorpaRaXWzgZBX5ftLz-15gi1mBNcD.jpg?r=77f",
                    name = "Dead Cells: Netflix Edition",
                    type = "Action",
                ),
                Game(
                    id = 2,
                    image = "https://dnm.nflximg.net/api/v6/2DuQlx0fM4wd1nzqm5BFBi6ILa8/AAAAQY2Arz9mdrrklzcy79U9sw03jn-otmm17o-V--a6LpLMk4VYLxp83jeB_x7YbHew2L_ReK5tWOqgO4nScpyc0_E7oUxyYCQiDnk7OrXFcbcxF8-zXno-5xx-XLOUdXj2Wrta0qG4xlbi6L8i6B31FpZc.jpg?r=86e",
                    name = "GTA III - The Definitive Edition",
                    type = "Action",
                ),
                Game(
                    id = 3,
                    image = "https://dnm.nflximg.net/api/v6/2DuQlx0fM4wd1nzqm5BFBi6ILa8/AAAAQV7liCV9SUvcspemnFMAmL1duEM7ganHJcT4St4QhZjEyCWaHoovKRlRCoPQsLbep9RJosDUwIohjaj17_6lvrtdnkeYDU3SdYNBe9oJqizLrnvqGGXVPUpDnEvFzREMicrs3OpHNKAD3wZULQ.jpg?r=7a8",
                    name = "Arranger",
                    type = "Adventure",
                ),
                Game(
                    id = 4,
                    image = "https://dnm.nflximg.net/api/v6/2DuQlx0fM4wd1nzqm5BFBi6ILa8/AAAAQc6sy118nRVlUY8Dk79yU63dtIWrte5kSTNGG8LzavGwxdX_E5BzOJJ5V2zfFwUDbColsk8kEthIgWCLpFtUYNgHrLyFtFHV_d3hDfLYTuoKzKtYNVkaK7REoqRY7qbUJ64fTKvYhVtnRLPE89SXeId5.jpg?r=365",
                    name = "Bloons TD 6",
                    type = "Arcade",
                ),
            )
        uiState = uiState.copy(popularGames = games, mobileGames = games.shuffled())
    }
}
