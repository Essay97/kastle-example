import io.github.essay97.kastle.dsl.game
import io.github.essay97.kastle.dto.GameConfiguration
import io.github.essay97.kastle.dto.LinkState
import io.github.essay97.kastle.model.LinkBehavior
import io.github.essay97.kastle.service.GameProvider

class ExampleGame : GameProvider {
    override fun provideConfiguration(): GameConfiguration = game("r-start") {
        room("r-start") {
            name = "Initial room"
            description = "This is the first room that the player sees"
            north("r-next") {
                behavior = LinkBehavior.CONSTANT
                state = LinkState.OPEN
            }
        }

        room("r-next") {
            name = "Another room"
            description = "This is a room that the player can move to"
        }

        winIf {
            playerEnters = "r-next"
        }

        player {
            name = "Enrico"
            description = "Enrico is the author of Kastle and also an awesome hero for this game"
        }
    }
}